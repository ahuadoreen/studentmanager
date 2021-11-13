package com.example.studentmanager.mapper;

import com.example.studentmanager.entity.GradeCourse;
import org.apache.ibatis.annotations.Param;

public interface GradeCourseMapper {
    GradeCourse findGradeCourse(@Param("grade")Integer grade);
    void insertGradeSubjectRelation(@Param("grade")Integer grade, @Param("subjectId")Long subjectId);
    void insertGradeSubjectRelations(@Param("grade")Integer grade, @Param("subjectIds")Long[] subjectIds);
    void deleteGradeSubjectRelation(@Param("grade")Integer grade);
}
