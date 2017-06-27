package cn.myapp.util;



import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.myapp.entity.BaseInfo;
import cn.myapp.entity.TenderNode;
import cn.myapp.entity.VersionNode;


public final class ParseJson {


	/**
	 * parseIsSuccess
	 * 
	 * @param buf
	 * @return
	 */
	public static boolean parseIsSuccess(String buf) {
		boolean isSuccessful = false;
		try {
			JSONObject jsonObj = new JSONObject(buf);
			if (jsonObj.has("errorCode")) {
				if ("0".equals(jsonObj.getString("errorCode"))) {
					isSuccessful = true;
				} else {
					isSuccessful = false;
				}
			}

		} catch (Exception e) {
			isSuccessful = false;
		}
		return isSuccessful;
	}

	/**
	 * 解析资料库
	 * 
	 * @param buf
	 * @return
	 */
	public static BaseInfo parseDatums(String buf) {
		BaseInfo baseInfo = null;

		try {
			JSONObject jsonObj = new JSONObject(buf);
			if (!"0".equals(jsonObj.getString("errorCode"))) {
				return baseInfo;
			}

			baseInfo = new BaseInfo();
			if (jsonObj.has("currPage")) {
				baseInfo.setCurrPage(jsonObj.getInt("currPage"));
			}

			if (jsonObj.has("pageSize")) {
				baseInfo.setPageSize(jsonObj.getInt("pageSize"));
			}
		} catch (Exception e) {
		}

		return baseInfo;

	}


	/**
	 * 版本信息解析
	 * 
	 * @param buf
	 * @return
	 */
	public static VersionNode parseVersionNode(String buf) {
		VersionNode versionNode = null;
		try {
			JSONObject jsonObj = new JSONObject(buf);
			if (jsonObj != null) {
				if (!"0".equals(jsonObj.getString("errorCode"))) {
					return versionNode;
				}

				if (jsonObj.has("versions")) {
					JSONObject subjsonObj = jsonObj.getJSONObject("versions");
					if (subjsonObj != null) {
						versionNode = new VersionNode();
						if (subjsonObj.has("id")) {
							versionNode.setId(subjsonObj.getLong("id"));
						}
						if (subjsonObj.has("vcode")) {
							versionNode.setVcode(subjsonObj.getString("vcode"));
						}
						if (subjsonObj.has("vdate")) {
							versionNode.setVdate(subjsonObj.getString("vdate"));
						}
						if (subjsonObj.has("verimprint")) {
							versionNode.setVerimprint(subjsonObj
									.getString("verimprint"));
						}
						if (subjsonObj.has("vurl")) {
							versionNode.setVurl(subjsonObj.getString("vurl"));
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return versionNode;

	}


	/**
	 * 解析拜访记录
	 * 
	 * @param buf
	 * @return
	 */
	public static BaseInfo parseShangJis(String buf) {
		BaseInfo baseInfo = null;

		try {
			JSONObject jsonObj = new JSONObject(buf);
			if (!"0".equals(jsonObj.getString("errorCode"))) {
				return baseInfo;
			}

			baseInfo = new BaseInfo();
			if (jsonObj.has("currPage")) {
				baseInfo.setCurrPage(jsonObj.getInt("currPage"));
			}

			if (jsonObj.has("pageSize")) {
				baseInfo.setPageSize(jsonObj.getInt("pageSize"));
			}



		} catch (Exception e) {
		}

		return baseInfo;

	}

	/**
	 * 解析招标信息
	 * 
	 * @param buf
	 * @return
	 */
	public static BaseInfo parseTenderNodes(String buf) {

		BaseInfo baseInfo = null;

		try {
			JSONObject jsonObj = new JSONObject(buf);
			if (!"0".equals(jsonObj.getString("errorCode"))) {
				return baseInfo;
			}

			baseInfo = new BaseInfo();
			if (jsonObj.has("currPage")) {
				baseInfo.setCurrPage(jsonObj.getInt("currPage"));
			}

			if (jsonObj.has("pageSize")) {
				baseInfo.setPageSize(jsonObj.getInt("pageSize"));
			}

			if (jsonObj.has("tenders")) {
				List<TenderNode> tenderNodes = new ArrayList<TenderNode>();
				JSONArray array = jsonObj.getJSONArray("tenders");
				if (array != null) {
					for (int i = 0; i < array.length(); i++) {
						jsonObj = array.getJSONObject(i);
						TenderNode tenderNode = new TenderNode();
						if (jsonObj.has("id")) {
							tenderNode.setTenderid(jsonObj.getString("id"));
						}
						if (jsonObj.has("tenderno")) {
							tenderNode.setTenderno(jsonObj
									.getString("tenderno"));
						}
						if (jsonObj.has("contents")) {
							tenderNode.setContents(jsonObj
									.getString("contents"));
						}
						if (jsonObj.has("category")) {
							tenderNode.setCategory(jsonObj
									.getString("category"));
						}
						if (jsonObj.has("purchaser")) {
							tenderNode.setPurchaser(jsonObj
									.getString("purchaser"));
						}
						if (jsonObj.has("project_funds")) {
							tenderNode.setProject_funds(jsonObj
									.getString("project_funds"));
						}
						if (jsonObj.has("receive_file_time")) {
							tenderNode.setReceive_file_time(jsonObj
									.getString("receive_file_time"));
						}
						if (jsonObj.has("receive_file_place")) {
							tenderNode.setReceive_file_place(jsonObj
									.getString("receive_file_place"));
						}
						if (jsonObj.has("start_time")) {
							tenderNode.setStart_time(jsonObj
									.getString("start_time"));
						}
						if (jsonObj.has("end_time")) {
							tenderNode.setEnd_time(jsonObj
									.getString("end_time"));
						}
						if (jsonObj.has("info_source")) {
							tenderNode.setInfo_source(jsonObj
									.getString("info_source"));
						}
						if (jsonObj.has("tel_phone")) {
							tenderNode.setTel_phone(jsonObj
									.getString("tel_phone"));
						}
						if (jsonObj.has("zhongxin")) {
							tenderNode.setZhongxin(jsonObj
									.getString("zhongxin"));
						}
						if (jsonObj.has("pqzhuguan")) {
							tenderNode.setPqzhuguan(jsonObj
									.getString("pqzhuguan"));
						}
						if (jsonObj.has("jiekouren")) {
							tenderNode.setJiekouren(jsonObj
									.getString("jiekouren"));
						}
						
						
						if (jsonObj.has("follow_people")) {
							tenderNode.setFollow_people(jsonObj
									.getString("follow_people"));
						}
						if (jsonObj.has("follow_status")) {
							tenderNode.setFollow_status(jsonObj
									.getString("follow_status"));
						}
						if (jsonObj.has("follow_logs")) {
							tenderNode.setFollow_logs(jsonObj
									.getString("follow_logs"));
						}
						if (jsonObj.has("shangji_name")) {
							tenderNode.setShangji_name(jsonObj
									.getString("shangji_name"));
						}
						if (jsonObj.has("ready_resource")) {
							tenderNode.setReady_resource(jsonObj
									.getString("ready_resource"));
						}
						if (jsonObj.has("stop_cause")) {
							tenderNode.setStop_cause(jsonObj
									.getString("stop_cause"));
						}
						if (jsonObj.has("acquisition_time")) {
							tenderNode.setAcquisition_time(jsonObj
									.getString("acquisition_time"));
						}

						tenderNodes.add(tenderNode);
					}
				}

				baseInfo.setTenderNodes(tenderNodes);
			}

		} catch (Exception e) {
		}

		return baseInfo;

	}


}
