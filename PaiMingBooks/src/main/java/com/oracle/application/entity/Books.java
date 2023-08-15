package com.oracle.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wfl
 * @since 2023-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String author;

    private String description;

    private String isbn;

    private LocalDate publicationDate;

    private String publisher;

    private String language;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
