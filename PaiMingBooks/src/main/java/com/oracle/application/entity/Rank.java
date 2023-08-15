package com.oracle.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @Author 王飞龙
 * @Date 2023/8/11 14:44
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rank implements Serializable {

    private Integer bookId;

    private String title;
    private Integer currNo;
    private Integer oldNo;

    private String tag;

    public static String getMx(Integer oldNo,Integer currNo){
        if (oldNo>currNo){
            return "up";
        }else if (oldNo<currNo){
            return "down";
        }else {
            return "-";
        }
    }


}
