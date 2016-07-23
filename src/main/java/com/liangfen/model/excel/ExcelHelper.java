package com.liangfen.model.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelHelper {
	
	private List<CardNumUserNamePair> cardNumUserNamePairsList = new ArrayList<CardNumUserNamePair>();
	
	private final static String[] HEADERS = {"卡号", "用户名"};
	private final static String XLS_SHEET_NAME = "售卡列表";

	public List<CardNumUserNamePair> getCardNumUserNamePairsList() {
		return cardNumUserNamePairsList;
	}

	public void setCardNumUserNamePairsList(
			List<CardNumUserNamePair> cardNumUserNamePairsList) {
		this.cardNumUserNamePairsList = cardNumUserNamePairsList;
	}

	public void clear()
	{
		if(cardNumUserNamePairsList != null)
		{
			cardNumUserNamePairsList.clear();
		}		
	}
	
	public boolean addItem(CardNumUserNamePair item)
	{
		if(cardNumUserNamePairsList == null)
		{
			cardNumUserNamePairsList = new ArrayList<CardNumUserNamePair>();
		}
		return cardNumUserNamePairsList.add(item);
	}
	
	public boolean addItems(List<CardNumUserNamePair> items)
	{
		if(items == null || items.size() <= 0) return false;
		
		if(cardNumUserNamePairsList == null)
		{
			cardNumUserNamePairsList = new ArrayList<CardNumUserNamePair>();
		}
		
		for (CardNumUserNamePair item : items) {
			addItem(item);
		}
		
		return true;
	}
	
	public boolean readXls(String filePath) throws IOException
	{
		if(filePath == null || "".equals(filePath.trim())) return false;
		
		FileInputStream inStream = new FileInputStream(filePath);
		return readXls(inStream);
	}
	
	public boolean readXls(InputStream inputStream) throws IOException
	{
		if(inputStream == null) return false;
		
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = wb.getSheetAt(0);
		if(sheet == null) return false;
		
		// read title for validate
		HSSFRow row = sheet.getRow(0);
		if(row == null) return false;
		
		for (int i = 0; i < HEADERS.length; i++) {
			HSSFCell cell = row.getCell(i);
			if(!HEADERS[i].equals(cell.getStringCellValue()))
			{
				return false;
			}
		}
		
		// read data
		int rowIndex = 1;
		
		row = sheet.getRow(rowIndex++);
		while (row != null) {

			HSSFCell cardNumCell = row.getCell(0);
			HSSFCell userNameCell = row.getCell(1);
			
			// read for next			
			row = sheet.getRow(rowIndex++);
			
			// invalidate row
			if(cardNumCell == null || userNameCell == null) continue;
			
			String cardNumString = cardNumCell.getStringCellValue();
			String userNameString = userNameCell.getStringCellValue();
			
			if(cardNumString == null || "".equals(cardNumString.trim())) continue;
			if(userNameString == null || "".equals(userNameString.trim())) continue;
			
			// add
			addItem(new CardNumUserNamePair(cardNumString.trim(), userNameString.trim()));
		}
		
		return true;
	}
	
	public boolean writeXls(String filePath) throws IOException
	{
		if(filePath == null || "".equals(filePath.trim())) return false;
		
		FileOutputStream outputStream = new FileOutputStream(filePath);
		return writeXls(outputStream);
	}
	
	public boolean writeXls(OutputStream outputStream) throws IOException
	{
		if(outputStream == null) return false;
		
		if(cardNumUserNamePairsList == null || cardNumUserNamePairsList.size() <= 0) return false;
		
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(XLS_SHEET_NAME);
		int rowIndex = 0;
		
		// header
		HSSFRow headerRow = sheet.createRow(rowIndex++);
		for (int i = 0; i < HEADERS.length; i++) {
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(HEADERS[i]);
		}
		
		// data		
		for (int i = 0; i < cardNumUserNamePairsList.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex++);
			CardNumUserNamePair data = cardNumUserNamePairsList.get(i);
			
			HSSFCell cardNumCell = row.createCell(0);
			cardNumCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cardNumCell.setCellValue(data.getCardNum());
			
			HSSFCell userNameCell = row.createCell(1);
			userNameCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			userNameCell.setCellValue(data.getUserName());			
		}
		
		// write
		wb.write(outputStream);
		return true;
	}
	
	/**
	 * 打印模板文件
	 * @param outputStream
	 * @return
	 * @throws IOException
	 */
	public boolean printXlsTemplate(OutputStream outputStream) throws IOException
	{
		if(outputStream == null) return false;
		
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(XLS_SHEET_NAME);
		HSSFRow row = sheet.createRow(0);

		for (int index = 0; index < HEADERS.length; index++) {
			HSSFCell cell = row.createCell(index);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(HEADERS[index]);
		}
		
		// add dummy data
		HSSFRow dummyRow = sheet.createRow(1);
		HSSFCell cell = dummyRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("13411112222");
		
		cell = dummyRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("name");
		
		// write
		workbook.write(outputStream);
		
		return true;				
	}
}
