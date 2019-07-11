package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签控制器
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}" ,method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{labelId}" ,method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId ,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @RequestMapping(value = "/{labelId}" ,method = RequestMethod.DELETE)
    public Result delete(@PathVariable("labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @RequestMapping(value = "/search/{page}/{size}" ,method = RequestMethod.POST)
    public Result findQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }

}
