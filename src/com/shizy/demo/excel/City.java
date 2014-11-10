package com.shizy.demo.excel;

import org.json.JSONObject;

public class City {

	private String name;
	private String alias;
	private String provice;
	private String pinyin;
	
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
	
	public String getPinyin() {
		return pinyin;
	}
	
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	public String toJSONString() {
		return toJSONString().toString();
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("alias", alias);
		obj.put("provice", provice);
		obj.put("pinyin", pinyin);
		return obj;
	}
	
}
