package cn.myapp.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：使用Gson解析Json数据信息的工具类
 * @author xiaoyang
 *
 */
public class GsonUtils {

	
	
	private static Gson gson = new Gson();
	/**
	 * 功能描述：把JSON数据转换成普通字符串列表
	 * @param jsonData   JSON数据
	 * @return
	 * @throws Exception
	 * @author xiaoyang
	 */
	public static List<String> getStringList(String jsonData) throws Exception{
		return gson.fromJson(jsonData, new TypeToken<List<String>>(){}.getType());
	}
	
	/**
	 * 功能描述：把JSON数据转换成指定的java对象
	 * @param jsonData   JSON数据
	 * @param clazz      指定的java对象
	 * @return
	 * @throws Exception
	 * @author xiaoyang
	 */
	public static <T> T getSingleBean(String jsonStr , Class<T> clazz) throws Exception{
		return gson.fromJson(jsonStr, clazz);
	}
	
	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 * @param jsonData    JSON数据
	 * @return
	 * @throws Exception
	 * @author xiaoyang
	 */
	public static <T> List<T> getBeanList(String jsonData) throws Exception{
		return gson.fromJson(jsonData, new TypeToken<List<T>>(){}.getType());
	}
	
	/**
	 * 功能描述：把JSON数据转换成较为复杂的java对象列表
	 * @param jsonData    JSON数据
	 * @return
	 * @throws Exception
	 * @author xiaoyang
	 */
	public static List<Map<String , Object>> getBeanMapList(String jsonData)throws Exception{
		return gson.fromJson(jsonData, new TypeToken<List<Map<String , Object>>>(){}.getType());
	}
	
	/**
	 * 传入一个实体对象，解析出标准的JSON串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
}
