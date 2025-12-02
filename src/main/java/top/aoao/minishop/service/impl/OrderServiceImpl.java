package top.aoao.minishop.service.impl;

import top.aoao.minishop.pojo.Order;
import top.aoao.minishop.mapper.OrderMapper;
import top.aoao.minishop.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
