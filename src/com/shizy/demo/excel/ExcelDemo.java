package com.shizy.demo.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.json.JSONArray;
import org.json.JSONObject;

import com.shizy.demo.pinyin.PinyinUtil;

public class ExcelDemo {

	public static void main(String[] args) {
//		showExcel();
		cityToJson();
	}
	
	public static void showExcel() {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("./city.xls"));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			
			HSSFSheet sheet = wb.getSheet("全国地级市");
			System.out.println(sheet.getSheetName());
			for (int rowNum = sheet.getFirstRowNum(); rowNum < sheet.getLastRowNum(); rowNum++) {
				System.out.println("----------------------" + rowNum + "------------------------");
				HSSFRow row = sheet.getRow(rowNum);
				for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
					HSSFCell cell = row.getCell(cellNum);
					int type = cell.getCellType();
					switch (type) {
					case HSSFCell.CELL_TYPE_BLANK:
						System.out.print("BLANK  ");
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "  ");
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						System.out.print(cell.getErrorCellValue() + "  ");
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						System.out.print(cell.getCachedFormulaResultType() + "  ");
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "  ");
						break;
					case HSSFCell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "  ");
						break;
					}
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cityToJson() {
		try {
			List<City> cityList = new ArrayList<City>();
			City city = null;
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("./city.xls"));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet("全国地级市");
			for (int rowNum = 3; rowNum < sheet.getLastRowNum(); rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				city = new City();
				city.setName(row.getCell(1).getStringCellValue());
				city.setAlias(row.getCell(2).getStringCellValue());
				city.setProvice(row.getCell(3).getStringCellValue());
				city.setPinyin(PinyinUtil.convertToPinYin(city.getName()));
				city.setInitial(PinyinUtil.getAlpha(city.getPinyin()));
				cityList.add(city);
			}
			
			City[] cities = new City[cityList.size()];
			cityList.toArray(cities);
			Arrays.sort(cities);
			
			JSONArray array = new JSONArray();
			String initial = "";
			JSONArray section = null;
			JSONObject temp = null;
			for (int i = 0; i < cities.length; i++) {
				city = cities[i];
				if (!city.getInitial().equals(initial)) {
					initial = city.getInitial();
					section = new JSONArray();
				}
				section.put(cities[i].toJSONObject());
				if (i + 1 >= cities.length || !city.getInitial().equals(cities[i + 1].getInitial())) {
					temp = new JSONObject();
					temp.put("initial", initial);
					temp.put("list", section);
					array.put(temp);
				}
			}
			
			System.out.println(array.toString());
			array = null;
			cities = null;
			cityList.clear();
			cityList = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
