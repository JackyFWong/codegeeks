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
      private static final String COL_TITLE = "question";
      private static final String COL_TITLE="Answer";
      private static final String COL_TITLE="MultipleChoice"

   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table " + MathTable.TABLE + " (" +
         MathTable.COL_ID + " integer primary key autoincrement, " +
         MathTable.COL_TITLE + " text, "+
         MathTable.COL_TITLE + "integer,"+
         MathTable.COL_TITLE+")");
   }

    @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion,
                         int newVersion) {
      db.execSQL("drop table if exists " + MathTable.TABLE);
      onCreate(db);
   }

   public long addMathques() {
   SQLiteDatabase db = getWritableDatabase();
   ContentValues values = new ContentValues();
   values.put(MathTable.COL_TITLE, "5+5=?");
   long quesID = db.insert(MathTable.TABLE, null, values);
   return quesID;
}

}
