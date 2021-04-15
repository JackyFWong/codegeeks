package com.example.kidsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MathDatabase extends SQLiteOpenHelper {

   private static MathDatabase mMathDb;
   private static final String DATABASE_NAME = "math.db";
   private static final int VERSION = 1;
  // private Context mContext;

   public MathDatabase(Context mContext) {

      super(mContext, DATABASE_NAME, null, VERSION);
   }
   private static final class MathTable {
      private static final String TABLE = "math";
      private static final String COL_ID = "_id";
      private static final String Question = "question";
      private static final String Answer = "Answer";
      private static final String Options = "Options";
   }

   @Override
   public void onCreate (SQLiteDatabase db){
      db.execSQL("create table " + MathTable.TABLE + " (" +
              MathTable.COL_ID + " integer primary key autoincrement, " +
              MathTable.Question + " text, " +
              MathTable.Answer + "integer," +
              MathTable.Options + "text)");
      addQuestions();
    //  List<MathQuestion> mQuestions=getQuestions();
   }

   @Override
   public void onUpgrade (SQLiteDatabase db,int oldVersion,
   int newVersion){
      db.execSQL("drop table if exists " + MathTable.TABLE);
      onCreate(db);
   }

   private void addQuestions() {
      SQLiteDatabase db = getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(MathTable.Question, "5 + 5 = ?");
      values.put(MathTable.Answer, 10);
      values.put(MathTable.Options, "[12,7,10,9]");
      long quesID = db.insert(MathTable.TABLE, null, values);

      values.put(MathTable.Question, "3 * 2 = ?");
      values.put(MathTable.Answer, 6);
      values.put(MathTable.Options, "[2,3,6,9]");
      long quesID_2 = db.insert(MathTable.TABLE, null, values);

      values.put(MathTable.Question, "5 - 2 = ?");
      values.put(MathTable.Answer, 3);
      values.put(MathTable.Options, "[1,2,3,4]");
      long quesID_3 = db.insert(MathTable.TABLE, null, values);
   }


   public List<MathQuestion> getQuestions(){
      SQLiteDatabase db_2 = getReadableDatabase();
      String sql = "select * from " + MathTable.TABLE ;
      Cursor cursor = db_2.rawQuery(sql, null);

      List<MathQuestion> mQuestions = new ArrayList<MathQuestion>();

      if (cursor.moveToFirst()) {
         do {
            long id = cursor.getLong(0);
            String question = cursor.getString(1);
            int answer = cursor.getInt(2);
            String options = cursor.getString(3);
            Log.d("DATABASE QUESTION", id + "," + question + "," + answer + "," + options);

            int[] optionsArray = stringToArray(options);
            List<Integer> optionsList = new ArrayList<Integer>(optionsArray.length);
            for (int i : optionsArray) {
               optionsList.add(i);
            }
            mQuestions.add(new MathQuestion((int)id, question, optionsList,
                    optionsList.indexOf(answer)));
         } while (cursor.moveToNext());
      }
      cursor.close();
      return mQuestions;
   }

   /*
   https://stackoverflow.com/a/7646415
    */
   public int[] stringToArray(String arr) {
      String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "")
              .replaceAll("\\s", "").split(",");
      int[] results = new int[items.length];

      for (int i = 0; i < items.length; i++) {
         try {
            results[i] = Integer.parseInt(items[i]);
         } catch (NumberFormatException e) {
            Log.e("DATABASE QUESTION CREATION", e.toString());
         };
      }
      return results;
   }
}
