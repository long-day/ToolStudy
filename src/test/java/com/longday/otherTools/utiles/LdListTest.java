package com.longday.otherTools.utiles;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 君
 * @version 1.0
 * @date 2022/9/3
 */
public class LdListTest {
    List<String> list = new ArrayList<>();

    @Test
    public void ldList(){
        list.add("你好");
        System.out.println(list);

    }
}
