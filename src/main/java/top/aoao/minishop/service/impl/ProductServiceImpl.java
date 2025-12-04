package top.aoao.minishop.service.impl;

import lombok.extern.slf4j.Slf4j;
import top.aoao.minishop.pojo.entity.Product;
import top.aoao.minishop.mapper.ProductMapper;
import top.aoao.minishop.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public boolean deductStock(Long productId, Integer count) {
//        if (!checkStock(productId, count)) {
//            return false;
//        }
        //TODO, 扣减库存，考虑并发问题
        Product product = baseMapper.selectById(productId);
        product.setStock(product.getStock() - count);
        log.info("扣减商品库存，商品ID：{}，扣减数量：{}，剩余库存：{}", productId, count, product.getStock());
        return baseMapper.updateById(product) > 0;
    }
    public boolean checkStock(Long productId, Integer count) {
        //TODO,查询单个字段，避免查询整个对象
        Product product = baseMapper.selectById(productId);
        if (product == null) {
            log.error("商品ID：{}不存在", productId);
            return false;
        }
        return product.getStock() >= count;
    }
}
