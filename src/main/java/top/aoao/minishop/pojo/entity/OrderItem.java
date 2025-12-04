package top.aoao.minishop.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单_商品表（订单明细表）
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Getter
@Setter
@ToString
@TableName("order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单项ID（主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联订单ID（外键）
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 关联商品ID（外键）
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 购买时的商品名称（冗余存储，避免商品改名后订单显示异常）
     */
    @TableField("product_name")
    private String productName;

    /**
     * 购买时的商品单价（冗余存储，避免价格变动影响订单）
     */
    @TableField("product_price")
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    @TableField("buy_num")
    private Integer buyNum;

    /**
     * 订单项小计金额（单价×数量）
     */
    @TableField("sub_amount")
    private BigDecimal subAmount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
