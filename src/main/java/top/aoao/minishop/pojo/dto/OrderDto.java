package top.aoao.minishop.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aoao.minishop.pojo.entity.OrderItem;
import top.aoao.minishop.pojo.entity.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: AoAo
 * @Date: 2025/12/2
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto implements Serializable {

    /**
     * 订单ID（主键）
     */
    private Long id;

    /**
     * 订单编号（唯一，如：2024052010000001）
     */
    private String orderNo;

    /**
     * 关联用户ID（外键）
     */
    private Long userId;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal payAmount;

    /**
     * 订单状态：0-待支付，1-已支付，2-已取消，3-已完成
     */
    private Byte status;

    /**
     * 支付时间（未支付则为NULL）
     */
    private LocalDateTime payTime;

    /**
     * 创建时间（下单时间）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
     /**
     * 订单商品列表
     */
    private List<OrderItem> orderItems;
}
