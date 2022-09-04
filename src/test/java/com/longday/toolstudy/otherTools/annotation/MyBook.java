package com.longday.toolstudy.annotation;

import com.longday.toolstudy.annotation.myAnnotation.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Book(name = "《类上的Book注解》", authors = {"君", "longday"}, price = 1)
public class MyBook {
    private String name;
    private String[] authors;
    private double price;
    private String address;

    @Book(name = "《类中方法上的Book注解》", authors = {"君", "longday"}, price = 999)
    public void addBook() {
    }
}
