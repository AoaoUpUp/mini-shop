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
 * 商品表
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Getter
@Setter
@ToString
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID（主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品单价（保留2位小数）
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 库存数量（并发扣减的核心字段）
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 乐观锁版本号（解决超卖用）
     */
    @TableField("version")
    private Integer version;

    /**
     * 商品描述（可选）
     */
    @TableField("description")
    private String description;

    /**
     * 状态：1-上架，0-下架
     */
    @TableField("status")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
