package top.aoao.minishop.service.impl;

import top.aoao.minishop.pojo.User;
import top.aoao.minishop.mapper.UserMapper;
import top.aoao.minishop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author aoao
 * @since 2025-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
