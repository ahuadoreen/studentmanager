package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.entity.Teacher;
import com.example.studentmanager.mapper.TeacherMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;

    @RequestMapping(value = "/teachers")
    public ResponseData findTeachers(final String name, final Integer index, final Integer size){
        Page<Teacher> page = PageHelper.startPage(index + 1, size);
        List<Teacher> teachers = teacherMapper.findTeachers(name);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("pageCount", page.getPages());
        responseData.putDataValue("total", page.getTotal());
        responseData.putDataValue("teachers", teachers);
        return responseData;
    }

    @PostMapping(value = "/addTeacher")
    @ResponseBody
    public ResponseData addTeacher(final String name, final String[] subjectIds, final String age, Integer gender)
    {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setAge(Integer.valueOf(age));
        teacher.setGender(gender);
        teacherMapper.insertTeacher(teacher);
        for(int i=0;i<subjectIds.length;i++){
            teacherMapper.insertTeacherSubjectRelation(teacher.getId(), Long.parseLong(subjectIds[i]));
        }
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editTeacher")
    @ResponseBody
    public ResponseData editTeacher(Long id, final String name, final String[] subjectIds, final String age, Integer gender)
    {
        teacherMapper.deleteTeacherSubjectRelation(id);
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setAge(Integer.valueOf(age));
        teacher.setGender(gender);
        teacherMapper.updateTeacher(teacher);
        for(int i=0;i<subjectIds.length;i++){
            teacherMapper.insertTeacherSubjectRelation(id, Long.parseLong(subjectIds[i]));
        }
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteTeacher")
    @ResponseBody
    public ResponseData deleteTeacher(Long id)
    {
        teacherMapper.deleteTeacherSubjectRelation(id);
        teacherMapper.deleteTeacher(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}
