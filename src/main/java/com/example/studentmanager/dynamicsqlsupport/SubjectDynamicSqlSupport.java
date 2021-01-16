package com.example.studentmanager.dynamicsqlsupport;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public class SubjectDynamicSqlSupport {
    public static final Subject subject = new Subject();
    public static final SqlColumn<Long> id = subject.id;
    public static final SqlColumn<String> name = subject.name;
    public static final class Subject extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
        public Subject() {
            super("subject");
        }
    }
}
