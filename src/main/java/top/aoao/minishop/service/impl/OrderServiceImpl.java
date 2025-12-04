package top.aoao.minishop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.aoao.minishop.pojo.dto.OrderDto;
import top.aoao.minishop.pojo.entity.Order;
import top.aoao.minishop.mapper.OrderMapper;
import top.aoao.minishop.pojo.entity.OrderItem;
import top.aoao.minishop.service.OrderItemService;
import top.aoao.minishop.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.aoao.minishop.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(OrderDto orderDto) {
        log.info("线程{}创建订单", Thread.currentThread().getName());
        //TODO, 校验订单商品列表是否为空
        //减少库存
        //校验库存是否充足
        boolean stockEnough = orderDto.getOrderItems().stream().allMatch(orderItem -> productService.checkStock(orderItem.getProductId(), orderItem.getBuyNum()));
        if (!stockEnough) {
            log.error("商品库存不足，订单创建失败");
            return null;
        }
        //减少库存
        orderDto.getOrderItems().stream().forEach(orderItem -> productService.deductStock(orderItem.getProductId(), orderItem.getBuyNum()));
        //设置订单总金额
        BigDecimal totalAmount = orderDto.getOrderItems().stream().map(t -> {
            BigDecimal subAmount = t.getProductPrice().multiply(BigDecimal.valueOf(t.getBuyNum()));
            return subAmount;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        orderDto.setTotalAmount(totalAmount);
        //保存订单
        UUID uuid = UUID.randomUUID();
        Order order = new Order();
        order.setOrderNo(uuid.toString());
        order.setUserId(orderDto.getUserId());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setStatus(Byte.valueOf("0"));
        this.save(order);
        log.info("线程{}创建订单，订单编号：{}", Thread.currentThread().getName(), order.getOrderNo());
        //保存订单商品详情
        try {
            orderDto.getOrderItems().stream().forEach(orderItem -> {
                OrderItem orderItemEntity = new OrderItem();
                orderItemEntity.setOrderId(order.getId());
                orderItemEntity.setProductId(orderItem.getProductId());
                orderItemEntity.setProductName(orderItem.getProductName());
                orderItemEntity.setProductPrice(orderItem.getProductPrice());
                orderItemEntity.setBuyNum(orderItem.getBuyNum());
                orderItemEntity.setSubAmount(orderItem.getProductPrice().multiply(BigDecimal.valueOf(orderItem.getBuyNum())));
                orderItemEntity.setCreateTime(LocalDateTime.now());
                orderItemService.save(orderItemEntity);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("线程{}创建订单成功，订单编号：{}", Thread.currentThread().getName(), order.getOrderNo());
        return order.getOrderNo();
    }

    @Override
    public Boolean pay(String orderNo, BigDecimal payMoney) {
        //校验订单是否存在
        if (!checkIfOrderExist(orderNo)) {
            return false;
        }
        Order order = this.getOrder(orderNo);
        //校验支付金额是否足够
        if (order.getTotalAmount().compareTo(payMoney) < 0) {
            return false;
        }
        //更新订单状态为已支付
        order.setStatus(Byte.valueOf("1"));
        order.setPayAmount(payMoney);
        order.setPayTime(LocalDateTime.now());
        this.updateById(order);
        return true;
    }

    @Override
    public Order getOrder(String orderNo) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderNo, orderNo);
        return baseMapper.selectOne(queryWrapper);
    }

    private boolean checkIfOrderExist(String orderNo) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderNo, orderNo);
        return baseMapper.selectCount(queryWrapper) > 0;
    }
}
