package com.maxpicca.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maxpicca.common.Result;
import com.maxpicca.dto.TodoAddDto;
import com.maxpicca.dto.TodoGetDto;
import com.maxpicca.entity.Todo;
import com.maxpicca.service.TodoService;
import com.maxpicca.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Maxpicca
 * @Date 2021-02-24
 */
@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

  /*  @GetMapping("/getTodo")
    public Result getTodo(String useridStr){  // Integer currentPage
        System.out.println(useridStr+"进入后端获取todo列表");
        long userid = Long.parseLong(useridStr);
        // orderByAsc 顺序
        // orderByDesc 倒序
        List<Todo> todos = todoService.list(new QueryWrapper<Todo>().eq("userid", userid).orderByAsc("end_time") );
        *//*if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5); // 一页五条记录
        IPage pageData = todoService.page(page, new QueryWrapper<Todo>().eq("userid",userid).orderByDesc("end_time"));*//*
        return Result.ok(todos);
    }*/

    @GetMapping("/getTodo")
    public Result getTodo(@Param("userid")int userid){  // Integer currentPage
//    @PostMapping("/getTodo")
//    public Result getTodo(@RequestBody TodoGetDto todoGetDto){  // Integer currentPage
//        int userid = todoGetDto.getUserid();
        System.out.println("=====用户"+userid+"获取待办====");
        // orderByAsc 顺序
        // orderByDesc 倒序
        List<Todo> todos = todoService.list(new QueryWrapper<Todo>().eq("userid", userid).orderByDesc("priority","end_time"));
        return Result.ok(todos);
    }


    @RequestMapping("/todoDone")
    public Result todoDone(@RequestBody TodoAddDto tad){
        System.out.println("====处理待办=====");
        // Todo todo = todoService.getOne(new QueryWrapper<Todo>().eq("id", tad.getId()));
        Todo todo = todoService.getById(tad.getId());
        todo.setStatus(tad.getStatus());
        todoService.saveOrUpdate(todo);
        return Result.ok("处理任务成功",null);
    }

    @RequestMapping("/todoDelete")
    public Result todoDelete(@RequestBody TodoAddDto tad){
        System.out.println("====删除待办=====");
        boolean b = todoService.removeById(tad.getId());
        if(b){
            return Result.ok("删除成功",null);
        }else{
            return Result.error("删除失败");
        }
    }

    @RequestMapping("/todoadd")
    public Result todoadd(@RequestBody TodoAddDto todoAddDto){
        // System.out.println(todoAddDto);
        System.out.println("====增加待办=====");
        System.out.println(todoAddDto);
        Todo todo = addDto2todo(todoAddDto);
        todoService.saveOrUpdate(todo);
        System.out.println("====添加成功=====");
        return Result.ok(MapUtil.builder()
                .put("id", todo.getId())
                .put("userid", todo.getUserid())
                .put("title",todo.getTitle())
                .map()
        );
    }

    private Todo addDto2todo(TodoAddDto tad){
        Todo todo = new Todo();
//         BeanUtil.copyProperties(tad, todo, "username", "email", "password");
        todo.setId(tad.getId());
        todo.setUserid(tad.getUserid()); // TODO 如何获取当前网页用户的id信息
        todo.setTitle(tad.getTitle());
        todo.setStartTime(tad.getStartTime());
        todo.setEndTime(tad.getEndTime());
        todo.setPriority(tad.getPriority());
        todo.setStatus(tad.getStatus());
        todo.setRemark(tad.getRemark());
        return todo;
    }
}
