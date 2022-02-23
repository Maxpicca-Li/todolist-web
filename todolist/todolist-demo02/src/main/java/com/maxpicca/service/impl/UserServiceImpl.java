package com.maxpicca.service.impl;

import com.maxpicca.entity.User;
import com.maxpicca.mapper.UserMapper;
import com.maxpicca.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Maxpicca
 * @since 2021-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
