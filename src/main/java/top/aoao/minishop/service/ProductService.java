package top.aoao.minishop.service;

import top.aoao.minishop.pojo.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
public interface ProductService extends IService<Product> {
     /**
     * 扣减商品库存
     * @param productId 商品ID
     * @param count 扣减数量
     * @return 是否扣减成功
     */
    boolean deductStock(Long productId, Integer count);

    /**
     * 校验商品库存是否充足
     * @param productId 商品ID
     * @param count 校验数量
     * @return 是否充足
     */
    boolean checkStock(Long productId, Integer count);



}
