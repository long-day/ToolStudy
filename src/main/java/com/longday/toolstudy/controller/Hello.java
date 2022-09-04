package com.longday.toolstudy.controller;

import com.longday.toolstudy.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 君
 * @version 1.0
 * @date 2022/7/27 13:53
 */
@Api(tags = "测试")
@RestController
public class Hello {

    @ApiOperation("你好")
    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('create')")
    public ResponseResult hello(){
        return ResponseResult.okResult("hello");
    }
}
