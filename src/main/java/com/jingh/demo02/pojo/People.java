package com.jingh.demo02.pojo;


import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People {


    private String name;
    private String sex;
    private int age;
    private String comment;
    private String hobby;

}
