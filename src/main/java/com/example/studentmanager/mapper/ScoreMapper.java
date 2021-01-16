package com.example.studentmanager.mapper;

import com.example.studentmanager.entity.Score;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.util.List;
import java.util.Objects;

import static com.example.studentmanager.dynamicsqlsupport.ScoreDynamicSqlSupport.*;
import static com.example.studentmanager.dynamicsqlsupport.StudentDynamicSqlSupport.*;
import static com.example.studentmanager.dynamicsqlsupport.SubjectDynamicSqlSupport.*;
import static com.example.studentmanager.dynamicsqlsupport.TeacherDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

public interface ScoreMapper {
    @InsertProvider(type= SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Score> insertStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ScoreResult", value= {
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="score", property="score", jdbcType= JdbcType.INTEGER),
            @Result(column="studentId", property="student.id", jdbcType=JdbcType.BIGINT),
            @Result(column="subjectId", property="subject.id", jdbcType=JdbcType.BIGINT),
            @Result(column="teacherId", property="teacher.id", jdbcType=JdbcType.BIGINT),
            @Result(column="studentName", property="student.name", jdbcType=JdbcType.VARCHAR),
            @Result(column="subjectName", property="subject.name", jdbcType=JdbcType.VARCHAR),
            @Result(column="teacherName", property="teacher.name", jdbcType=JdbcType.VARCHAR)
    })
    List<Score> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ScoreResult")
    Score selectOne(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, scoreTable, completer);
    }

    BasicColumn[] selectList =
            BasicColumn.columnList(scoreTable.id, score, student.id.as("studentId"), student.name.as("studentName"),
                    subject.id.as("subjectId"), subject.name.as("subjectName"), teacher.id.as("teacherId"),
                    teacher.name.as("teacherName"));

    default int insert(Score scoreO) {
        return MyBatis3Utils.insert(this::insert, scoreO, scoreTable, c ->
                c.map(scoreTable.id).toProperty("id")
                        .map(score).toProperty("score")
                        .map(studentId).toProperty("studentId")
                        .map(subjectId).toProperty("subjectId")
                        .map(teacherId).toProperty("teacherId")
        );
    }

    default int updateByPrimaryKey(Score record) {
        return update(c ->
                c.set(score).equalTo(record::getScore)
                        .set(studentId).equalTo(record::getStudentId)
                        .set(subjectId).equalTo(record::getSubjectId)
                        .set(teacherId).equalTo(record::getTeacherId)
                        .where(scoreTable.id, isEqualTo(record::getId))
        );
    }

    default List<Score> select(SelectDSLCompleter completer) {
        QueryExpressionDSL<SelectModel> start = SqlBuilder.select(selectList).from(scoreTable)
                .join(student, on(scoreTable.studentId, equalTo(student.id)))
                .join(subject, on(scoreTable.subjectId, equalTo(subject.id)))
                .join(teacher, on(scoreTable.teacherId, equalTo(teacher.id)));
        return MyBatis3Utils.selectList(this::selectMany, start, completer);
    }

    default List<Score> selectByStudent(String studentName) {
        return select(c ->
                c.where(student.name, isLike(studentName).when(Objects::nonNull).then(s -> "%" + s + "%"))
                .orderBy(scoreTable.studentId,scoreTable.subjectId)
        );
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, scoreTable, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(scoreTable.id, isEqualTo(id_))
        );
    }
}
