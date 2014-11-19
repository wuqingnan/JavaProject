package com.shizy.demo.excel;

import org.json.JSONException;
import org.json.JSONObject;

public class City implements Comparable<City> {

	private String name;
	private String alias;
	private String provice;
	private String code;
	private String pinyin;
	private String initial;
	
	public City() {
		
	}
	
	public City(JSONObject object) {
		if (object != null) {
			setName(object.optString("name"));
//			setAlias(object.optString("alias"));
			setProvice(object.optString("provice"));
			setCode(object.optString("code"));
//			setPinyin(object.optString("pinyin"));
			//setInitial(object.optString("initial"));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getProvice() {
		return provice;
	}
	
	public void setProvice(String provice) {
		this.provice = provice;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPinyin() {
		return pinyin;
	}
	
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String toJSONString() {
		return toJSONString().toString();
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("name", name);
//			obj.put("alias", alias);
			obj.put("provice", provice);
			obj.put("code", code);
//			obj.put("pinyin", pinyin);
			//obj.put("initial", initial);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int compareTo(City another) {
		return getPinyin().compareTo(another.getPinyin());
	}
	
}
