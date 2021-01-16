package com.example.studentmanager.dynamicsqlsupport;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public class StudentDynamicSqlSupport {
    public static final Student student = new Student();
    public static final SqlColumn<Long> id = student.id;
    public static final SqlColumn<String> sno = student.sno;
    public static final SqlColumn<String> name = student.name;
    public static final SqlColumn<Integer> gender = student.gender;
    public static final SqlColumn<Integer> age = student.age;
    public static final SqlColumn<Long> classId = student.classId;
    public static final class Student extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
        public final SqlColumn<String> sno = column("sno", JDBCType.VARCHAR);
        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
        public final SqlColumn<Integer> gender = column("gender", JDBCType.INTEGER);
        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);
        public final SqlColumn<Long> classId = column("class", JDBCType.BIGINT);
        public Student() {
            super("student");
        }
    }
}
