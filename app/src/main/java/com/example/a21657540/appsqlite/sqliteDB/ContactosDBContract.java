package com.example.a21657540.appsqlite.sqliteDB;

import android.provider.BaseColumns;

/**
 * Created by 21657540 on 12/02/2018.
 */

public class ContactosDBContract {

    public static abstract class ContactoEntry implements BaseColumns {

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";
        public static final String COLUMN_MAIL = "EMAIL";
        public static final String TABLE_NAME = "CONTACTOS";
    }


}
