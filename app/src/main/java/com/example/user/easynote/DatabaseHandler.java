package com.example.user.easynote;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydb2.db";
    static final String TABLE_NAME = "mytable";

    private static final int DATABASE_VERSION = 1;

    static final String COLUMN_TITLE = "title";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_ICON = "icon";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_ICON + " INTEGER);");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TABLE_NAME, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreates the database with a new version
        onCreate(db);
    }
    public long addRecord(String title,String description,int icon){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_DESCRIPTION,description);
        values.put(COLUMN_ICON,icon);
        SQLiteDatabase db = this.getWritableDatabase();
        long row = db.insert(DatabaseHandler.TABLE_NAME,null,values);
        Log.d(TABLE_NAME,"inserted at row " + row + " " + title + description  + icon);
        db.close();
        return row;
    }
    public String getRecord(long id){
        String data = null;

        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = {"_id","title","description","icon"};
        Cursor c = db.query(TABLE_NAME,columns,"_id=?",new String[] {String.valueOf(id)},
                null,null,null,null);

        Log.d(TABLE_NAME,"recID " + id + " count " + c.getCount());

        if(c != null){
            if(c.moveToFirst()){
                int idCol = c.getColumnIndex("_id");
                int titleCol = c.getColumnIndex("title");
                int descriptionCol = c.getColumnIndex("description");
                int iconCol = c.getColumnIndex("icon");

                String strId = Integer.toString(c.getInt(idCol));
                String strTitle = c.getString(titleCol);
                String strDescription = c.getString(descriptionCol);
                String strIcon = Integer.toString(c.getInt(iconCol));

                // String strId = Integer.toString(0);
                // String strName = c.getString(1);

                data = "id : " + strId + " \nTitle : " + strTitle + " Description : " + strDescription + "\nIcon : " + strIcon + "\n";

            }
        }
        c.close();
        return data;
    }
    public int getRecordCount(){
        String countQuery = "SELECT _id FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(countQuery,null);
        return cur.getCount();
    }
    public int updateContact(long recID,String title,String description,int icon){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_DESCRIPTION,description);
        values.put(COLUMN_ICON,icon);

        return db.update(TABLE_NAME,values,"_id = ?"
                ,new String[] {String.valueOf(recID)});
    }

    public Cursor getAllRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id","title","description","icon"};
        Cursor cur = db.query(TABLE_NAME,columns,null,null,
                null,null,null);
        Log.d(TABLE_NAME," count " + cur.getCount());
        return cur;
    }
   public Cursor getSearchedRecord(String search){
        SQLiteDatabase db = this.getWritableDatabase();
       String[] columns = {"_id","title","description","icon"};
        Cursor cur = db.query(TABLE_NAME,columns,"name Like ?",new String[]{"%" + search + "%"},
        null,null,null,null);
        Log.d(TABLE_NAME, " count " + cur.getCount());
        return cur;
   }
   public void deleteRecord(long recID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"_id = ?",new String[] {String.valueOf(recID)});
        db.close();
   }
   public String getTitle(long id){
       String data = null;

       SQLiteDatabase db = this.getWritableDatabase();

       String[] columns = {"_id","title","description","icon"};
       Cursor c = db.query(TABLE_NAME,columns,"_id=?",new String[] {String.valueOf(id)},
               null,null,null,null);

       Log.d(TABLE_NAME,"recID " + id + " count " + c.getCount());

       if(c != null){
           if(c.moveToFirst()){
               int titleCol = c.getColumnIndex("title");

               String strTitle = c.getString(titleCol);

               data = strTitle;
           }
       }
       c.close();
       return data;
   }
   public String getDescription(long id){
       String data = null;

       SQLiteDatabase db = this.getWritableDatabase();

       String[] columns = {"_id","title","description","icon"};
       Cursor c = db.query(TABLE_NAME,columns,"_id=?",new String[] {String.valueOf(id)},
               null,null,null,null);

       Log.d(TABLE_NAME,"recID " + id + " count " + c.getCount());

       if(c != null){
           if(c.moveToFirst()){
               int descriptionCol = c.getColumnIndex("description");

               String strDescription = c.getString(descriptionCol);

               data = strDescription;
           }
       }
       c.close();
       return data;
   }
   public int getIcon(long id){
       int data = 0;

       SQLiteDatabase db = this.getWritableDatabase();

       String[] columns = {"_id","title","description","icon"};
       Cursor c = db.query(TABLE_NAME,columns,"_id=?",new String[] {String.valueOf(id)},
               null,null,null,null);

       Log.d(TABLE_NAME,"recID " + id + " count " + c.getCount());

       if(c != null){
           if(c.moveToFirst()){
               int iconCol = c.getColumnIndex("icon");

               int strIcon = Integer.valueOf(c.getInt(iconCol));

               data = strIcon;
           }
       }
       c.close();
       return data;
   }
}