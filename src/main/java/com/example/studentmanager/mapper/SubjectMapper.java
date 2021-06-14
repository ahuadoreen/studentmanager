package com.example.studentmanager.mapper;

import com.example.studentmanager.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper {
    List<Subject> findSubjects(@Param("name")String name);
    void insertSubject(@Param("name")String name);
    void updateSubject(@Param("id")Long id, @Param("name")String name);
    void deleteSubject(@Param("id")Long id);
    void insertSubjects(@Param("subjectList")List<Subject> subjectList);
    void updateSubjects(@Param("subjectList")List<Subject> subjectList);
    void deleteSubjects(@Param("ids")Long[] ids);
}
