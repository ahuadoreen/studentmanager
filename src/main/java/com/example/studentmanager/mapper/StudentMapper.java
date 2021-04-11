package com.example.studentmanager.mapper;

import com.example.studentmanager.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> findStudents(@Param("name")String name);
    void insertStudent(@Param("student") Student student);
    void updateStudent(@Param("student") Student student);
    void deleteStudent(@Param("id")Long id);
}
