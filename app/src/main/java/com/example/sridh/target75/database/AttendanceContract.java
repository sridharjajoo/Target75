package com.example.sridh.target75.database;

import android.provider.BaseColumns;

/**
 * Created by sridh on 24-12-2016.
 */

public class AttendanceContract  {

    public static final class AttendanceEntry implements BaseColumns{

        public static final String TABLE_NAME = "tracker";
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_PRESENT = "present";
        public static final String COLUMN_ABSENT = "absent";
        public static final String COLUMN_MONDAY="monday";
        public static final String COLUMN_TUESDAY="tuesday";
        public static final String COLUMN_WEDNESDAY="wednesday";
        public static final String COLUMN_THURSDAY="thursday";
        public static final String COLUMN_FRIDAY="friday";
    }
}
