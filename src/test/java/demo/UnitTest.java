package demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.liangfen.model.excel.CardNumUserNamePair;
import com.liangfen.model.excel.ExcelHelper;



public class UnitTest {

	@Test
	//@Ignore
	public void excelWriteTest()
	{
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("target/workbook.xls");
			
			ExcelHelper excelHelper = new ExcelHelper();
			excelHelper.printXlsTemplate(fileOutputStream);
			
			fileOutputStream.close();
			
			Assert.assertTrue(true);
			
			return ;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(false);
		
	}
	
	@Test
	@Ignore
	public void excelReadTest()
	{
		try {
			FileInputStream inStream = new FileInputStream("workbook.xls");
			ExcelHelper excelHelper = new ExcelHelper();
			Assert.assertTrue(excelHelper.readXls(inStream));
			inStream.close();
			
			List<CardNumUserNamePair> list = excelHelper.getCardNumUserNamePairsList();
			Assert.assertNotNull(list);
			
			for (CardNumUserNamePair cardNumUserNamePair : list) {
				System.out.println(cardNumUserNamePair + "\n");
			}
			
			FileOutputStream outputStream = new FileOutputStream("workbook_new.xls");
			excelHelper.writeXls(outputStream);
			outputStream.close();
			
			return ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Assert.assertTrue(false);
	}
	
}
