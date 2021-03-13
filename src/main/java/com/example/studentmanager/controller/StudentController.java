package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Student;
import com.example.studentmanager.mapper.StudentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    @GetMapping(value = "/students")
    public ResponseData findStudents(final String name, final Integer index, final Integer size){
        Page<Student> page = PageHelper.startPage(index + 1, size);
        List<Student> students = studentMapper.findStudents(name);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("pageCount", page.getPages());
        responseData.putDataValue("total", page.getTotal());
        responseData.putDataValue("students", students);
        return responseData;
    }

    @PostMapping(value = "/addStudent", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData addStudent(final String sno, final String name, final String age, Integer gender, Long classId)
    {
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setAge(Integer.valueOf(age));
        student.setGender(gender);
        studentMapper.insertStudent(student, classId);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editStudent", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData editStudent(Long id, final String sno, final String name, final String age, Integer gender, Long classId)
    {
        Student student = new Student();
        student.setId(id);
        student.setSno(sno);
        student.setName(name);
        student.setAge(Integer.valueOf(age));
        student.setGender(gender);
        studentMapper.updateStudent(student, classId);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteStudent", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData deleteStudent(Long id)
    {
        studentMapper.deleteStudent(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}
