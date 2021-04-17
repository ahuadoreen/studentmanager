package com.example.studentmanager.controller;

import com.example.studentmanager.entity.Insert;
import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Student;
import com.example.studentmanager.entity.Update;
import com.example.studentmanager.mapper.StudentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    @Parameters({
            @Parameter(name = "name", description = "学生姓名", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY,
                    allowEmptyValue = true)})
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
    public ResponseData addStudent(@Validated(value = Insert.class) Student student)
    {
        studentMapper.insertStudent(student);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editStudent", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData editStudent(@Validated(value = Update.class) Student student)
    {
        studentMapper.updateStudent(student);
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
