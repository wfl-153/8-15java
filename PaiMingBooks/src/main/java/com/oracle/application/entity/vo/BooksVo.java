package com.oracle.application.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author 王飞龙
 * @Date 2023/8/12 11:24
 * @Version 1.0
 */
@Data
public class BooksVo implements Serializable {

    private Integer id;

    private String title;

    private String author;

    private String description;

    private String isbn;

    private LocalDate publicationDate;

    private String publisher;

    private String language;
}
