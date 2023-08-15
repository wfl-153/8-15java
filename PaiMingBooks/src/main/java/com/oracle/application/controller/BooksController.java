package com.oracle.application.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oracle.application.entity.Books;
import com.oracle.application.entity.vo.BooksVo;
import com.oracle.application.entity.vo.ResultVo;
import com.oracle.application.entity.vo.Result;
import com.oracle.application.service.IBooksService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wfl
 * @since 2023-08-11
 */
@RestController
@RequestMapping("/books")
@CrossOrigin
@Api
public class BooksController {

    @Autowired
    private IBooksService iBooksService;

    @GetMapping("/list")
    public Result list() {
        List<Books> booksList = iBooksService.list();
        List<BooksVo> booksVoList= booksList.stream().map(books -> {
            BooksVo booksVo=new BooksVo();
            BeanUtils.copyProperties(books,booksVo);
            return booksVo;
        }).collect(Collectors.toList());
        return Result.success(booksVoList);
    }

    @GetMapping("/list2")
    public Page list2(
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",required = false,defaultValue = "2") Integer pageSize
            ){
        Page<Books> page = iBooksService.page(new Page<>(pageNum, pageSize));
        return  page;
    }

    @GetMapping("/getBooksDetails")
    public Result getBooksDetails(Integer id) {
        iBooksService.addScore(id);
        Books books = iBooksService.finById(id);
        BooksVo booksVo=new BooksVo();
        BeanUtils.copyProperties(books,booksVo);
        return  Result.success(booksVo);
    }

    @GetMapping("/getBooksRank")
    public Result getBooksRank() {
        return  Result.success(iBooksService.getRankData());
    }





}
