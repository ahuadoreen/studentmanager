package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Teacher;
import com.example.studentmanager.mapper.TeacherMapper;
import com.example.studentmanager.utils.FileUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;
    @Value("${web.upload-path}")
    private String path;
    @Value("${web.image-path}")
    private String imagePath;

    @GetMapping(value = "/teachers")
    public ResponseData findTeachers(final String name, final Integer index, final Integer size){
        Page<Teacher> page = PageHelper.startPage(index + 1, size);
        List<Teacher> teachers = teacherMapper.findTeachers(name, imagePath);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("pageCount", page.getPages());
        responseData.putDataValue("total", page.getTotal());
        responseData.putDataValue("teachers", teachers);
        return responseData;
    }

    @PostMapping(value = "/addTeacher", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseData addTeacher(final String name, final Long[] subjectIds, final String age, Integer gender,
                                   @RequestParam(value = "file", required = false) MultipartFile file)
    {
        Teacher teacher = new Teacher();
        if(file != null){
            String fileName = FileUtil.upload(file, path, file.getOriginalFilename());
            if ( fileName!= null){
                teacher.setImageUrl(fileName);
            }
        }
        teacher.setName(name);
        teacher.setAge(Integer.valueOf(age));
        teacher.setGender(gender);
        teacherMapper.insertTeacher(teacher);
//        for(int i=0;i<subjectIds.length;i++){
//            teacherMapper.insertTeacherSubjectRelation(teacher.getId(), subjectIds[i]);
//        }
        teacherMapper.insertTeacherSubjectRelations(teacher.getId(), subjectIds);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editTeacher", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseData editTeacher(Long id, final String name, final Long[] subjectIds, final String age, Integer gender,
                                    @RequestParam(value = "file", required = false) MultipartFile file)
    {
        teacherMapper.deleteTeacherSubjectRelation(id);
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setAge(Integer.valueOf(age));
        teacher.setGender(gender);
        if(file != null){
            String fileName = FileUtil.upload(file, path, file.getOriginalFilename());
            if ( fileName!= null){
                teacher.setImageUrl(fileName);
            }
        }
        teacherMapper.updateTeacher(teacher);
//        for(int i=0;i<subjectIds.length;i++){
//            teacherMapper.insertTeacherSubjectRelation(id, subjectIds[i]);
//        }
        teacherMapper.insertTeacherSubjectRelations(teacher.getId(), subjectIds);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteTeacher", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    // todo 此处Swagger映射有bug，暂时只能用postman测试，待解决
    public ResponseData deleteTeacher(Long id)
    {
        teacherMapper.deleteTeacherSubjectRelation(id);
        teacherMapper.deleteTeacher(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @GetMapping(value = "/teacherDetail")
    public ResponseData findTeacherDetail(Long id){
        Teacher teacher = teacherMapper.findTeacherById(id, imagePath);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("teacher", teacher);
        return responseData;
    }
}
