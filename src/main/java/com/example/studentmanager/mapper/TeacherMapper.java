package com.example.studentmanager.mapper;

import com.example.studentmanager.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    List<Teacher> findTeachers(@Param("name")String name, @Param("path")String path);
    void insertTeacher(@Param("teacher") Teacher teacher);
    void insertTeacherSubjectRelation(@Param("teacherId")Long teacherId, @Param("subjectId")Long subjectId);
    void insertTeacherSubjectRelations(@Param("teacherId")Long teacherId, @Param("subjectIds")Long[] subjectIds);
    void deleteTeacherSubjectRelation(@Param("teacherId")Long teacherId);
    void updateTeacher(@Param("teacher") Teacher teacher);
    void deleteTeacher(@Param("id")Long id);
    Teacher findTeacherById(@Param("id")Long id, @Param("path")String path);
}
