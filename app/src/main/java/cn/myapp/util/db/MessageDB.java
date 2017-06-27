package cn.myapp.util.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.myapp.entity.MessageItem;


public class MessageDB {
	public static final String MSG_DBNAME = "message.db";
	public static final String group = "groupmsg";
	private SQLiteDatabase db;

	public MessageDB(Context context) {
		db = context.openOrCreateDatabase(MSG_DBNAME, Context.MODE_PRIVATE,
				null);
	}

	/**
	 * 以聊天group建立聊天信息表
	 * 
	 * @param group
	 * @param entity
	 */
	public void saveMsg(MessageItem entity) {
		db.execSQL("CREATE table IF NOT EXISTS _"
				+ group
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, img TEXT,date TEXT,isCome TEXT,message TEXT,isNew TEXT,groupid TEXT,msgType TEXT)");
		int isCome = 0;
		if (entity.isComMeg()) {// 濡傛灉鏄敹鍒扮殑娑堟伅锛屼繚瀛樺湪鏁版嵁搴撶殑鍊间负1
			isCome = 1;
		}
		db.execSQL(
				"insert into _"
						+ group
						+ " (name,img,date,isCome,message,isNew,groupid,msgType) values(?,?,?,?,?,?,?,?)",
				new Object[] { entity.getName(), entity.getHeadImg(),
						entity.getDate(), isCome, entity.getMessage(),
						entity.getIsNew(), entity.getGroup(),
						entity.getMsgType() });
	}

	public List<MessageItem> getMsg(int pager) {
		List<MessageItem> list = new LinkedList<MessageItem>();
		int num = 10 * (pager + 1);// 鏈潵鏄噯澶囧仛婊氬姩鍒伴《绔嚜鍔ㄥ姞杞芥暟鎹?
		db.execSQL("CREATE table IF NOT EXISTS _"
				+ group
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, img TEXT,date TEXT,isCome TEXT,message TEXT,isNew TEXT,groupid TEXT,msgType TEXT)");
		Cursor c = db.rawQuery("SELECT * from _" + group
				+ " ORDER BY _id DESC LIMIT " + num, null);
		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("name"));
			String img = c.getString(c.getColumnIndex("img"));
			long date = c.getLong(c.getColumnIndex("date"));
			int isCome = c.getInt(c.getColumnIndex("isCome"));
			String message = c.getString(c.getColumnIndex("message"));
			int isNew = c.getInt(c.getColumnIndex("isNew"));
			String groupid = c.getString(c.getColumnIndex("groupid"));
			String msgType = c.getString(c.getColumnIndex("msgType"));
			boolean isComMsg = false;
			if (isCome == 1) {
				isComMsg = true;
			}
			MessageItem entity = new MessageItem(msgType, name, date, message,
					img, isComMsg, isNew);
			entity.setGroup(groupid);
			list.add(entity);
		}
		c.close();
		Collections.reverse(list);// 鍓嶅悗鍙嶈浆涓?涓嬫秷鎭褰?
		return list;
	}

	public List<MessageItem> getMsgGroupByMsgType() {
		List<MessageItem> list = new LinkedList<MessageItem>();
		db.execSQL("CREATE table IF NOT EXISTS _"
				+ group
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, img TEXT,date TEXT,isCome TEXT,message TEXT,isNew TEXT,groupid TEXT,msgType TEXT)");
		Cursor c = db.rawQuery("SELECT * from _" + group
				+ " group by msgType ORDER BY msgType DESC", null);
		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("name"));
			String img = c.getString(c.getColumnIndex("img"));
			long date = c.getLong(c.getColumnIndex("date"));
			int isCome = c.getInt(c.getColumnIndex("isCome"));
			String message = c.getString(c.getColumnIndex("message"));
			int isNew = c.getInt(c.getColumnIndex("isNew"));
			String groupid = c.getString(c.getColumnIndex("groupid"));
			String msgType = c.getString(c.getColumnIndex("msgType"));
			boolean isComMsg = false;
			if (isCome == 1) {
				isComMsg = true;
			}
			MessageItem entity = new MessageItem(msgType, name, date, message,
					img, isComMsg, isNew);
			entity.setGroup(groupid);
			list.add(entity);
		}
		c.close();
		Collections.reverse(list);// 鍓嶅悗鍙嶈浆涓?涓嬫秷鎭褰?
		return list;
	}

	public MessageItem getLastMsg() {
		MessageItem entity = null;
		db.execSQL("CREATE table IF NOT EXISTS _"
				+ group
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, img TEXT,date TEXT,isCome TEXT,message TEXT,isNew TEXT,groupid TEXT,msgType TEXT)");
		Cursor c = db.rawQuery(
				"SELECT max(_id) as id,name,img,date,isCome,message,isNew,groupid from _"
						+ group, null);

		while (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("name"));
			String img = c.getString(c.getColumnIndex("img"));
			long date = c.getLong(c.getColumnIndex("date"));
			int isCome = c.getInt(c.getColumnIndex("isCome"));
			String message = c.getString(c.getColumnIndex("message"));
			int isNew = c.getInt(c.getColumnIndex("isNew"));
			String groupid = c.getString(c.getColumnIndex("groupid"));
			String msgType = c.getString(c.getColumnIndex("msgType"));
			boolean isComMsg = false;
			if (isCome == 1) {
				isComMsg = true;
			}
			entity = new MessageItem(msgType, name, date, message, img,
					isComMsg, isNew);
			entity.setGroup(groupid);
		}
		c.close();

		return entity;
	}

	public int getNewCount() {
		db.execSQL("CREATE table IF NOT EXISTS _"
				+ group
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, img TEXT,date TEXT,isCome TEXT,message TEXT,isNew TEXT,groupid TEXT,msgType TEXT)");
		Cursor c = db.rawQuery(
				"SELECT isNew from _" + group + " where isNew=1", null);
		int count = c.getCount();
		// L.i("new message num = " + count);
		c.close();
		return count;
	}

	public void clearNewCount() {
		db.execSQL("CREATE table IF NOT EXISTS _"
				+ group
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, img TEXT,date TEXT,isCome TEXT,message TEXT,isNew TEXT,groupid TEXT,msgType TEXT)");
		db.execSQL("update _" + group + " set isNew=0 where isNew=1");
	}

	public void deleteMessage(String groupid) {
		db.execSQL("DELETE FROM _" + group + " where groupid=" + groupid);
	}

	public void close() {
		if (db != null)
			db.close();
	}
}
