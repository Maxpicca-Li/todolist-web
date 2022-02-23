package com.maxpicca.service.impl;

import com.maxpicca.entity.Todo;
import com.maxpicca.mapper.TodoMapper;
import com.maxpicca.service.TodoService;
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
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {

}
