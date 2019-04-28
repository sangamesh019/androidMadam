package com.randroid.madam;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconnectors extends SQLiteOpenHelper {

	public DBconnectors(Context context) {
		super(context, "delta", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, userid TEXT, pass TEXT, mob TEXT)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE user IF EXITS");
		onCreate(db);
	}
	
	public void inser_row(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("name",  map.get("name"));
		cv.put("userid",map.get("userid"));
		cv.put("pass",map.get("pass"));
		cv.put("mob",map.get("mob"));
		
		sb.insert("user", null, cv);
	}
	
	public ArrayList<HashMap<String, String>> getvalues(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<HashMap<String, String>> l = new ArrayList<HashMap<String,String>>();
		Cursor c = db.rawQuery("select * from user", null);
		
		if(c.getCount()>0){
			
			c.moveToFirst();
			do{
				HashMap<String, String> m = new HashMap<String, String>();
				m.put("k0", c.getString(0));
				m.put("k1", c.getString(1));
				m.put("k2",c.getString(2));
				l.add(m);
			}while(c.moveToNext());
		}
		return l;
		
		
	}
	
	public HashMap<String, String> singlerow(String id){
		SQLiteDatabase db = this.getReadableDatabase();
		HashMap<String, String> m = new HashMap<String, String>();
		Cursor c = db.rawQuery("select * from user where id = "+id, null);

if(c.getCount()>0){
			
			c.moveToFirst();
			
				m.put("k1", c.getString(1));
				m.put("k2", c.getString(2));
			
			
		}
		return m;} 
	public void update(String id, HashMap<String, String> map){
		
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("name", map.get("kp1"));
		cv.put("price",map.get("kp2"));
		sb.update("user", cv, "id=?", new String []{id});
			}
public void delete(String id){
	SQLiteDatabase sb = this.getWritableDatabase();
	sb.delete("user", "id=?",new String[]{id});
}
}
