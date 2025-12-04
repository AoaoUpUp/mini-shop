package top.aoao.minishop.service;

import org.springframework.stereotype.Service;
import top.aoao.minishop.pojo.dto.OrderDto;
import top.aoao.minishop.pojo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */

public interface OrderService extends IService<Order> {

    String createOrder(OrderDto orderDto);
    Boolean pay(String orderNo, BigDecimal payMoney);
    Order getOrder(String orderNo);

}
