package com.longday.toolstudy.service;

import com.longday.toolstudy.domain.FdFileList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longday.toolstudy.vo.ResponseResult;

/**
* @author 君
* @description 针对表【fd_file_list(文件列表)】的数据库操作Service
* @createDate 2022-08-02 14:11:48
*/
public interface FdFileListService extends IService<FdFileList> {

    /**
     * 上传文件
     * @return 信息
     */
    ResponseResult upload();

    /**
     * 查询文件是否已存在
     * @return boolean
     */
    boolean isAlreadyExists();

    /**
     * 获取文件列表
     * @return 数据
     */
    ResponseResult getFilesList();
}
