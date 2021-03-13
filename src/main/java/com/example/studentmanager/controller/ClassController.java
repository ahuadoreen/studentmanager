package com.example.studentmanager.controller;

import com.example.studentmanager.entity.*;
import com.example.studentmanager.mapper.ClassMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/class")
public class ClassController {
	@Autowired
	ClassMapper classMapper;

	@PostMapping(value = "/addClass", consumes = { "application/x-www-form-urlencoded" })
	@ResponseBody
    public ResponseData addClass(
			final String className, final Integer grade, final String mainTeacherId)
    {
		Classes c = new Classes();
		c.setClassName(className);
		c.setGrade(grade);
		classMapper.insertClass(c, Long.parseLong(mainTeacherId));
		ResponseData responseData = ResponseData.ok();
		return responseData;
    }

	@Parameters({
			@Parameter(name = "name", description = "班主任姓名", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY,
					allowEmptyValue = true)})
	@GetMapping(value = "/classes")
	@ResponseBody
    public ResponseData getClasses(final String name, final Integer index, final Integer size)
    {
		Page<Subject> page = PageHelper.startPage(index + 1, size);
		List<Classes> classes = classMapper.findClasses(name);
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("pageCount", page.getPages());
		responseData.putDataValue("total", page.getTotal());
		responseData.putDataValue("classes", classes);
		return responseData;
    }

	@PostMapping(value = "/editClass", consumes = { "application/x-www-form-urlencoded" })
	@ResponseBody
	public ResponseData editClass(Long id, final String className, final Integer grade, final String mainTeacherId)
	{
		Classes c = new Classes();
		c.setId(id);
		c.setClassName(className);
		c.setGrade(grade);
		classMapper.updateClass(c, Long.parseLong(mainTeacherId));
		ResponseData responseData = ResponseData.ok();
		return responseData;
	}

	@PostMapping(value = "/deleteClass", consumes = { "application/x-www-form-urlencoded" })
	@ResponseBody
	public ResponseData deleteClass(Long id)
	{
		classMapper.deleteClass(id);
		ResponseData responseData = ResponseData.ok();
		return responseData;
	}

	@GetMapping(value = "/courseTeachers")
	@ResponseBody
	public ResponseData getCourseTeacher(Long id, final Integer grade)
	{
		List<Subject> gradeCoursesList = classMapper.findCoursesByGrade(grade);
		List<Map> mapList = new ArrayList<>();
		for(int i = 0;i<gradeCoursesList.size();i++){
			Subject gradeCourse = gradeCoursesList.get(i);
			Map map = new HashMap();
			Map subject = new HashMap();
			subject.put("subject", gradeCourse);
			map.put("course", subject);
			Teacher classCourseTeacher = classMapper.findClassCourseTeacher(id, gradeCourse.getId());
			if(classCourseTeacher != null){
				map.put("teacher", classCourseTeacher);
			}
			mapList.add(map);
		}
		ResponseData responseData = ResponseData.ok();
		responseData.putDataValue("courseTeachers", mapList);
		return responseData;
	}

	@PostMapping(value = "/editCourseTeacher", consumes = { "application/x-www-form-urlencoded" })
	@ResponseBody
	public ResponseData editCourseTeacher(Long id, final Long subjectId, final String teacherId)
	{
		classMapper.deleteClassCourseTeacher(id, subjectId);
		classMapper.insertClassCourseTeacher(id, subjectId, Long.parseLong(teacherId));
		ResponseData responseData = ResponseData.ok();
		return responseData;
	}
}
