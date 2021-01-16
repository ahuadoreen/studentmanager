package com.example.studentmanager.dynamicsqlsupport;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public class ScoreDynamicSqlSupport {
    public static final Score scoreTable = new Score();
    public static final SqlColumn<Long> id = scoreTable.id;
    public static final SqlColumn<Integer> score = scoreTable.score;
    public static final SqlColumn<Long> studentId = scoreTable.studentId;
    public static final SqlColumn<Long> subjectId = scoreTable.subjectId;
    public static final SqlColumn<Long> teacherId = scoreTable.teacherId;
    public static final class Score extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);
        public final SqlColumn<Integer> score = column("score", JDBCType.INTEGER);
        public final SqlColumn<Long> studentId = column("student_id", JDBCType.BIGINT);
        public final SqlColumn<Long> subjectId = column("subject_id", JDBCType.BIGINT);
        public final SqlColumn<Long> teacherId = column("teacher_id", JDBCType.BIGINT);
        public Score() {
            super("score");
        }
    }
}
