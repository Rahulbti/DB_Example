package androiddevguru.com.db_example.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androiddevguru.com.db_example.DataModel.Users;

/**
 * Created by John wick on 2/9/2018.
 */

public class DBhelper extends SQLiteOpenHelper {


    private static final String DBName = "DBname";
    private static final int DBVersion = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_PASSWORD = "password";

    public DBhelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table users( id INTEGER PRIMARY KEY, name TEXT, email TEXT, mobile TEXT, password TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    //save user info to database
    public boolean userRegistration(Users users) {
        boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,users.getName());
        values.put(COLUMN_EMAIL,users.getEmail());
        values.put(COLUMN_MOBILE,users.getMobile());
        values.put(COLUMN_PASSWORD,users.getPassword());
        flag = db.insert(TABLE_NAME,null,values)>0;
        db.close();
        return flag;
    }

    //get the user details for login

    /**
     * return 1 if credentials matched, 0 if password is incorrect, 2 if email is incorrect
     * @param email
     * @param password
     * @return
     */
    public int loginDetails(String email, String password) {
        int result = 0;
        String selectQuery = "SELECT * FROM users";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {
                String getEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                if(getEmail.equals(email)){

                    String getPasword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    if(getPasword.equals(password)) {
                        result = 1; // credentials matched successfully
                    }
                    else {
                        result = 0; // password is incorrect
                    }
                }
                else {
                    result = 2; // email is incorrect, can't find in db
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;

    }


}
