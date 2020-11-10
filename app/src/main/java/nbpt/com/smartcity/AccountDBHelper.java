package nbpt.com.smartcity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by Administrator on 2020/9/23.
 */

public class AccountDBHelper extends SQLiteOpenHelper{
//    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db,oldVersion, newVersion);
    }
    private Context mContext;
    static  final String DB_Name = "account.db";
    static  final String TABLE_Name = "tb_users";
    static  final int VERSION_1 = 1;
    public AccountDBHelper(Context context, int version) {
        super(context,DB_Name,null ,version);
        this.mContext = mContext;
    }
    public Cursor Select(String where,String order)
    {
        StringBuilder stringBuilder = new StringBuilder("select * from "+ TABLE_Name);
        if(where != null)
        {
            stringBuilder.append("WHERE");
            stringBuilder.append(where);
        }
        if(order != null)
        {
            stringBuilder.append("ORDER BY");
            stringBuilder.append(order);
        }
        return getWritableDatabase().rawQuery(stringBuilder.toString(),null);
    }
//    public Cursor selects(String where1,String order1)
//    {
//        StringBuilder stringBuilder1 = new StringBuilder("select * from "+ TABLE_Name +" where gender='女' ");
//        if(where1 != null)
//        {
//            stringBuilder1.append("WHERE");
//            stringBuilder1.append(where1);
//        }
//        if(order1 != null)
//        {
//            stringBuilder1.append("ORDER BY");
//            stringBuilder1.append(order1);
//        }
//        return getWritableDatabase().rawQuery(stringBuilder1.toString(),null);
//    }
    public long insertUser(String username,String password,int gender,String phone)
    {
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("password",password);
        values.put("gender",gender);
        values.put("phone",phone);
        long retValue=getReadableDatabase().insert(TABLE_Name,null,values);
        return retValue;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}