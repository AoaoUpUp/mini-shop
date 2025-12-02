package top.aoao.minishop.service.impl;

import top.aoao.minishop.pojo.Product;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
