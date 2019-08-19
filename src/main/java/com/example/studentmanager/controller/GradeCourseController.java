package com.example.studentmanager.controller;

import com.example.studentmanager.entity.GradeCourse;
import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.mapper.GradeCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/gradeCourse")
public class GradeCourseController {
    @Autowired
    private GradeCourseMapper gradeCourseMapper;

    @RequestMapping(value = "/addGradeCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addGradeCourse(final Integer grade, final String[] subjectIds){
        for(int i=0;i<subjectIds.length;i++){
            gradeCourseMapper.insertGradeSubjectRelation(grade, Long.parseLong(subjectIds[i]));
        }
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @RequestMapping(value = "/gradeCourses", method = RequestMethod.GET)
    @ResponseBody
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

    @RequestMapping(value = "/editGradeCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editGradeCourse(final Integer grade, final String[] subjectIds)
    {
        gradeCourseMapper.deleteGradeSubjectRelation(grade);
        for(int i=0;i<subjectIds.length;i++){
            gradeCourseMapper.insertGradeSubjectRelation(grade, Long.parseLong(subjectIds[i]));
        }
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @RequestMapping(value = "/deleteGradeCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteGradeCourse(final Integer grade)
    {
        gradeCourseMapper.deleteGradeSubjectRelation(grade);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}
