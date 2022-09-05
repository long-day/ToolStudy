package com.longday.otherTools.annotation;

import com.longday.otherTools.annotation.myAnnotation.Book;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/3
 */
public class AnnotationTest {
    private Class<?> myBookClass = MyBook.class;
    @Test
    public void analysisClassAnnotation(){
        if(myBookClass.isAnnotationPresent(Book.class)){
            Book bookAnnotation = myBookClass.getDeclaredAnnotation(Book.class);
            System.out.println(bookAnnotation.name());
            System.out.println(Arrays.toString(bookAnnotation.authors()));
            System.out.println(bookAnnotation.address());
            System.out.println(bookAnnotation.price());
        }
    }

    @Test
    public void analysisMethodAnnotation() throws Exception {
        Method addBook = myBookClass.getDeclaredMethod("addBook");
        if(addBook.isAnnotationPresent(Book.class)){
            Book bookAnnotation = addBook.getDeclaredAnnotation(Book.class);
            System.out.println(bookAnnotation.name());
            System.out.println(Arrays.toString(bookAnnotation.authors()));
            System.out.println(bookAnnotation.address());
            System.out.println(bookAnnotation.price());
        }
    }
}
