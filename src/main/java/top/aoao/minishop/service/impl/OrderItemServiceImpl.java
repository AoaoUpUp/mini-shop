package top.aoao.minishop.service.impl;

import top.aoao.minishop.pojo.entity.OrderItem;
import top.aoao.minishop.mapper.OrderItemMapper;
import top.aoao.minishop.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单_商品表（订单明细表） 服务实现类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
