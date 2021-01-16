package com.example.studentmanager.dynamicsqlsupport;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public class TeacherDynamicSqlSupport {
    public static final Teacher teacher = new Teacher();
    public static final SqlColumn<Long> id = teacher.id;
    public static final SqlColumn<String> name = teacher.name;
    public static final SqlColumn<Integer> gender = teacher.gender;
    public static final SqlColumn<Integer> age = teacher.age;
    public static final class Teacher extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
        public final SqlColumn<Integer> gender = column("gender", JDBCType.INTEGER);
        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);
        public Teacher() {
            super("teacher");
        }
    }
}
