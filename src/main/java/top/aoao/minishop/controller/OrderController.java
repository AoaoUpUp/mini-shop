package top.aoao.minishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.aoao.minishop.pojo.dto.OrderDto;
import top.aoao.minishop.service.OrderService;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private Executor taskExecutor;
    @Async
    @PostMapping("/create")
    public CompletableFuture<String> createOrder(@RequestBody OrderDto orderDto) {
        CompletableFuture<String> orderNo  = CompletableFuture.supplyAsync(() -> orderService.createOrder(orderDto), taskExecutor);
        return orderNo;
    }
    /**
     * 支付订单
     * @return 是否支付成功
     */
    @PostMapping("/pay")
    public Boolean pay(@RequestBody OrderDto orderDto) {
        String OrderNo = orderDto.getOrderNo();
        BigDecimal payMoney = orderDto.getPayAmount();
        return orderService.pay(OrderNo, payMoney);
    }
}
