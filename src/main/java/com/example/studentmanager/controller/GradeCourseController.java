package com.example.studentmanager.controller;

import com.example.studentmanager.entity.GradeCourse;
import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.mapper.GradeCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/gradeCourse")
public class GradeCourseController {
    @Autowired
    private GradeCourseMapper gradeCourseMapper;

    @PostMapping(value = "/addGradeCourse", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData addGradeCourse(final Integer grade, final Long[] subjectIds){
//        for(int i=0;i<subjectIds.length;i++){
//            gradeCourseMapper.insertGradeSubjectRelation(grade, subjectIds[i]);
//        }
        gradeCourseMapper.insertGradeSubjectRelations(grade, subjectIds);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @GetMapping(value = "/gradeCourses")
    public ResponseData getGradeCourses()
    {
        List<Map> mapList = new ArrayList<>();
        for(int i = 1;i<3;i++){
            GradeCourse gradeCourse = gradeCourseMapper.findGradeCourse(i);
            Map map = new HashMap();
            map.put("grade", i);
            map.put("courses", gradeCourse);
            mapList.add(map);
        }
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("gradeCourses", mapList);
        return responseData;
    }

    @PostMapping(value = "/editGradeCourse", consumes = { "application/x-www-form-urlencoded" })
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseData editGradeCourse(final Integer grade, final Long[] subjectIds)
    {
        gradeCourseMapper.deleteGradeSubjectRelation(grade);
//        for(int i=0;i<subjectIds.length;i++){
//            gradeCourseMapper.insertGradeSubjectRelation(grade, subjectIds[i]);
//        }
        gradeCourseMapper.insertGradeSubjectRelations(grade, subjectIds);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteGradeCourse", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData deleteGradeCourse(final Integer grade)
    {
        gradeCourseMapper.deleteGradeSubjectRelation(grade);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}
