package com.longday.toolstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longday.toolstudy.domain.FdFileList;
import com.longday.toolstudy.mapper.FdFileListMapper;
import com.longday.toolstudy.service.FdFileListService;
import com.longday.toolstudy.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author 君
* @description 针对表【fd_file_list(文件列表)】的数据库操作Service实现
* @createDate 2022-08-02 14:11:48
*/
@Service
public class FdFileListServiceImpl extends ServiceImpl<FdFileListMapper, FdFileList>
    implements FdFileListService{

    @Autowired
    private FdFileListMapper fdFileListMapper;
    @Override
    public ResponseResult upload() {
        //数据库插入操作

        return null;
    }

    @Override
    public boolean isAlreadyExists() {

        //TODO 这里判断一下文件的唯一hash标准  这个字段忘了预留了  暂时不写了
        return false;
    }

    @Override
    public ResponseResult getFilesList() {

        List<FdFileList> fileLists = fdFileListMapper.selectList(null);
        System.out.println(fileLists);
        HashMap<String, Object> map = new HashMap<>(5);
        map.put("fileList",fileLists);
        return new ResponseResult<>(200, "请求成功", map);
    }
}




