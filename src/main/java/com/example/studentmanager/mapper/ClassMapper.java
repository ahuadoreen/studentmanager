package com.example.studentmanager.mapper;

import com.example.studentmanager.entity.Classes;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    List<Classes> findClasses(@Param("name")String name);
    void insertClass(@Param("class")Classes classes, @Param("mainTeacherId")Long mainTeacherId);
    void updateClass(@Param("class")Classes classes, @Param("mainTeacherId")Long mainTeacherId);
    void deleteClass(@Param("id")Long id);
    List<Subject> findCoursesByGrade(@Param("grade")Integer grade);
    Teacher findClassCourseTeacher(@Param("classId")Long classId, @Param("subjectId")Long subjectId);
    void deleteClassCourseTeacher(@Param("classId")Long classId, @Param("subjectId")Long subjectId);
    void insertClassCourseTeacher(@Param("classId")Long classId, @Param("subjectId")Long subjectId, @Param("teacherId")Long teacherId);
}
