package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.mapper.SubjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectMapper subjectMapper;

    @RequestMapping(value = "/subjects")
    public ResponseData findSubjects(final String name, final Integer index, final Integer size){
        Page<Subject> page = PageHelper.startPage(index + 1, size);
        List<Subject> subjectList = subjectMapper.findSubjects(name);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("pageCount", page.getPages());
        responseData.putDataValue("total", page.getTotal());
        responseData.putDataValue("subjects", subjectList);
        return responseData;
    }

    @PostMapping(value = "/addSubject")
    public ResponseData addSubject(final String name){
        subjectMapper.insertSubject(name);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editSubject")
    @ResponseBody
    public ResponseData editSubject(Long id, final String name)
    {
        subjectMapper.updateSubject(id, name);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteSubject")
    @ResponseBody
    public ResponseData deleteSubject(Long id)
    {
        subjectMapper.deleteSubject(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}
