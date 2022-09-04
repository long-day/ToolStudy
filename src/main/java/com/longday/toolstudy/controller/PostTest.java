package com.longday.toolstudy.controller;

import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/28 17:25
 */
@RestController
public class PostTest {
    @PostMapping("/postTest")
    @PreAuthorize("hasAnyAuthority('postTest')")
    public ResponseResult hello2(){
        return new ResponseResult<>(200,"PostTest Is Ok!");
    }
}
