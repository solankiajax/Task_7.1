package com.example.lostandfound.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.lostandfound.MainActivity;
import com.example.lostandfound.NewAdvertActivity;
import com.example.lostandfound.model.Item;
import com.example.lostandfound.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Util.DATABASE_NAME, factory, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Util.POST_TYPE + " TEXT , " + Util.NAME + " TEXT , " + Util.PHONE + " TEXT , " + Util.DESCRIPTION + " TEXT , " +  Util.DATE + " TEXT , " + Util.LOCATION + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS '" + Util.TABLE_NAME + "'";
        sqLiteDatabase.execSQL(DROP_ITEM_TABLE);

        onCreate(sqLiteDatabase);
    }

    public long insertItem (Item item)
    {

        try{SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(Util.POST_TYPE, item.getPost_type());

            contentValues.put(Util.NAME, item.getName());

            contentValues.put(Util.PHONE,Integer.toString(item.getPhone()));

            contentValues.put(Util.DESCRIPTION,item.getDescription());

            contentValues.put(Util.DATE,item.getDate());

            contentValues.put(Util.LOCATION,item.getLocation());

            long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);

            db.close();

            return newRowId;}catch(Exception e){Log.e("REACHED",e.getMessage()); return 0;}


    }


    public List<Item> fetchAllItems (){
            List<Item> itemList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();


            String selectAll = "select * from " + Util.TABLE_NAME;
            Cursor cursor1 = db.rawQuery(selectAll,null);

            if (cursor1.moveToFirst()) {
                do {

                    Item item = new Item();

                    item.setItem_id(cursor1.getInt(0));

                    item.setPost_type(cursor1.getString(1));
                    item.setName(cursor1.getString(2));
                    item.setPhone(Integer.parseInt(cursor1.getString(3)));
                    item.setDescription(cursor1.getString(4));
                    item.setDate(cursor1.getString(5));
                    item.setLocation(cursor1.getString(6));

                    itemList.add(item);


                    } while (cursor1.moveToNext());
            }
            return itemList;
    }


   public String fetchItem(int Item_id)
    {
        String postType, name, phone,description,date,location;
        String result = "0";
        Log.d("REACHED","in fetch item");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("REACHED","db created");
        String query = "SELECT * FROM " + Util.TABLE_NAME + " where " + Util.ITEM_ID + " = '" + Integer.toString(Item_id) + "'";
        Log.d("REACHED","query created");
        Cursor cursor = db.rawQuery(query,null);
        Log.d("REACHED","query ran");

        if(cursor.moveToFirst()){
            do{
                postType = cursor.getString(1);
                name = cursor.getString(2);
                phone = Integer.toString(cursor.getInt(3));
                description = cursor.getString(4);
                date = cursor.getString(5);
                location = cursor.getString(6);
                result = postType + "\n Name: " + name + "\n Phone No. " + phone + "\n Description: " + description + "\n Date: " + date + "\n Location: " + location;
            }while(cursor.moveToNext());
        }


        Log.d("REACHED","result created");
        db.close();
        return result;
    }

    // method to delete a Record of UserName
    public void deleteEntry(String ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "DELETE FROM items where  ITEM_ID= '" + ID + "'";
        Cursor cursor = db.rawQuery(query,null);
        db.close();
    }

}
