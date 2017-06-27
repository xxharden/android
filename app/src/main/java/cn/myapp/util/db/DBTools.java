package cn.myapp.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 封装SQLite数据库操作工具类，单实例类
 * 
 * @author sy
 * 
 */
public final class DBTools {

	/**
	 * 单实例对象
	 */
	private static DBTools databaseTool = null;
	private SQLiteDatabase newsDB;
	private DBHelper dbHelper;

	/**
	 * 私有构造方法
	 * 
	 * @param context
	 *            上下文对象
	 */
	private DBTools(Context context) {
		dbHelper = new DBHelper(context);

		newsDB = dbHelper.getWritableDatabase();
	}

	/**
	 * 初始化方法
	 * 
	 * @param context
	 *            上下文对象
	 */
	public static void init(Context context) {
		databaseTool = new DBTools(context);
	}

	/**
	 * 获取实例对象
	 * 
	 * @return DBTool 数据库操作工具类实例
	 */
	public static DBTools getInstance() {
		return databaseTool;
	}

	/**
	 * 插入一条新纪录的方法
	 * 
	 * @param tableName
	 *            对应的数据表名
	 * @param values
	 *            列名和值对
	 * @return 成功则返回插入的行号，失败返回0
	 * @throws DBException
	 *             数据库操作异常
	 */
	public long insertRecord(String tableName, ContentValues values)
			throws DBException {
		long rowID = 0;
		try {
			rowID = newsDB.insert(tableName, null, values);
		} catch (SQLException e) {
			// throw new DBException(e);
		}
		return rowID;
	}

	/**
	 * 查询方法
	 * 
	 * @param tableName
	 *            要查询的表名
	 * @param col
	 *            要查询的字段名数组
	 * @param selection
	 *            相当于sql语句的where部分，如果想返回所有的数据，那么就直接置为null
	 * @param selectionArg
	 *            在selection部分，你有可能用到“?”,那么在selectionArgs定义的字符串会代替selection中的“?”
	 * @param groupBy
	 *            定义查询出来的数据是否分组，如果为null则说明不用分组
	 * @param having
	 *            相当于sql语句当中的having部分
	 * @param orderBy
	 *            来描述我们期望的返回值是否需要排序，如果设置为null则说明不需要排序
	 * @return Cursor 游标对象
	 * @throws DBException
	 *             数据库操作异常
	 */
	public Cursor query(String tableName, String[] col, String selection,
			String[] selectionArg, String groupBy, String having, String orderBy)
			throws DBException {
		Cursor cur = null;
		try {
			cur = newsDB.query(tableName, col, selection, selectionArg,
					groupBy, having, orderBy);
		} catch (SQLException e) {
			// throw new DBException(e);
		}
		return cur;
	}

	/**
	 * 更新一条纪录的方法
	 * 
	 * @param tableName
	 *            对应的数据表名
	 * @param values
	 *            列名和值对
	 * @param whereClause
	 *            相当于sql语句的where部分
	 * @param whereArgs
	 *            替换whereClause中的“?”
	 * @return 更新成功返回true，失败返回false
	 * @throws DBException
	 *             数据库操作异常
	 */
	public boolean updateRecord(String tableName, ContentValues values,
			String whereClause, String[] whereArgs) throws DBException {
		long rowID = -1;
		try {
			rowID = newsDB.update(tableName, values, whereClause, whereArgs);
		} catch (SQLException e) {
			// throw new DBException(e);
		}
		return rowID > 0;
	}

	/**
	 * 外部写好的非查询的SQL语句直接执行，通常不建议直接使用该方法
	 * 
	 * @param sql
	 *            完整的非查询类的SQL语句
	 * @throws DBException
	 *             数据库操作异常
	 */
	public void execSQL(String sql) throws DBException {
		try {
			newsDB.execSQL(sql);
		} catch (SQLException e) {
			// throw new DBException(e);
		}
	}

	/**
	 * 创建数据表
	 * 
	 * @param tableName
	 *            表名
	 * @param isRenew
	 *            如果要创建的表已存在，是否先删除
	 * @param sql
	 *            完整的建表SQL语句
	 * @throws DBException
	 *             数据库操作异常
	 */
	public void creatTable(String tableName, boolean isRenew, String sql)
			throws DBException {

		try {

			if (isRenew) {
				newsDB.execSQL("DROP TABLE IF EXISTS " + tableName);
			}
			newsDB.execSQL(sql);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	/**
	 * 关闭连接
	 */
	public void onClose() {
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
