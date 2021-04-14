package com.example.kidsapp;

public class MathDatabase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "math.db";
   private static final int VERSION = 1;

   public MathDatabase(Context context) {
      super(context, DATABASE_NAME, null, VERSION);
   }

   private static final class MathTable {
      private static final String TABLE = "math";
      private static final String COL_ID = "_id";
      private static final String Question = "question";
      private static final String Answer="Answer";
      private static final String Options="MultipleChoice"

   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table " + MathTable.TABLE + " (" +
         MathTable.COL_ID + " integer primary key autoincrement, " +
         MathTable.Question + " text, "+
         MathTable.Answer + "integer,"+
         MathTable.Options+"text"+);
   }

    @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion,
                         int newVersion) {
      db.execSQL("drop table if exists " + MathTable.TABLE);
      onCreate(db);
   }


   SQLiteDatabase db = getWritableDatabase();
   ContentValues values = new ContentValues();
   values.put(MathTable.Question, "5+5=?");
   values.put(MathTable.Answer,10);
   values.put(MathTable.Options,[12,7,10,9]);
   long quesID = db.insert(MathTable.TABLE, null, values);

   values.put(MathTable.Question, "3*2=?");
   values.put(MathTable.Answer,6);
   values.put(MathTable.Options,[2,3,6,9]);
   long quesID_2 = db.insert(MathTable.TABLE, null, values);

   values.put(MathTable.Question, "5-2=?");
   values.put(MathTable.Answer,3);
   values.put(MathTable.Options,[1,2,3,4]);
   long quesID_3 = db.insert(MathTable.TABLE, null, values);

   public Map<long l,String s> getQuestions(){

   SQLiteDatabase db = getReadableDatabase();

   String sql = "select * from " + MathTable.TABLE ;
   Cursor cursor = db.rawQuery(sql, new String[] { rating });
   Map<long l, String s> qMap=new Map<long l, String s>();
   if (cursor.moveToFirst()) {
      do {
         long id = cursor.String(0);
         String question = cursor.getString(1);
          qMap.put(id,question);
      } while (cursor.moveToNext());
   }
   cursor.close();
   return qMap;
   }


   }







}
