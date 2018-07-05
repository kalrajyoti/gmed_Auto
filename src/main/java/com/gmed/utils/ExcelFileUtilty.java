package com.gmed.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ExcelFileUtilty {
	/** Logger to log the ExcelFileUtilty log messages */
	private static Logger logger             = LogManager.getLogger(ExcelFileUtilty.class);
	@SuppressWarnings("deprecation")
	public HashMap<String, String> readExcelSheet(final String workBookName, final String sheetName) {

		logger.info("Opening the excel file to read data from: " + workBookName + " workbook, " + sheetName	+ " worksheet");

		HashMap<String, String> dataMap = new HashMap<String, String>();

		try {

			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream
					(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + workBookName)));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			String mapKey = new String();
			String mapValue = new String();
			int rowCounter = 0;

			logger.info("reading the data from the excel data sheet");

			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {

				HSSFRow row = (HSSFRow) rows.next();
				rowCounter = 0;
				String cellText = null;

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();

					if (cell.getCellType() == Cell.CELL_TYPE_STRING ) {

						cellText = cell.getRichStringCellValue().getString();

					} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {

						cellText =  String.valueOf( (int) cell.getNumericCellValue());

					} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

						cellText = String.valueOf(cell.getBooleanCellValue());

					} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA ) {
						cellText = evaluator.evaluate(cell).getStringValue();


						SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
						if(cell.getDateCellValue()!= null){							 
							cellText = sdf.format(cell.getDateCellValue()); 
						}else{

						}

					} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {

						cellText = null;
						continue;
					}

					else {
						logger.info("Not a valid type");
					}

					if (rowCounter == 0) {

						mapKey = cellText;
						rowCounter= rowCounter + 1;


					} else if (rowCounter == 1) {

						mapValue = cellText;
						rowCounter= rowCounter + 1;
					}

					dataMap.put(mapKey, mapValue);

				}
			}

			workbook.close();
			workbook = null;
			sheet = null;

			logger.info("Closing the excel file and returning to the test...");

		} catch (IOException e) {

			logger.error("Unable to read from the excel file -  " + workBookName);

			e.printStackTrace();

		} finally {

			logger.info("Inside the finally Method of the readExcelSheet method");

		}

		return dataMap;

	}
	public static void writeExcel(String filePath,String fileName,String sheetName,String [] dataToWrite) throws IOException{

		//Create an object of File class to open xlsx file

		File file =    new File(filePath+"\\"+fileName);

		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		//Check condition if the file is xlsx file

		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		//If it is xls file then create object of XSSFWorkbook class

		//Read excel sheet by sheet name    
		HSSFSheet sheet = workbook.getSheet(sheetName);
		//Sheet sheet = workbook.getSheet(sheetName);

		//Get the current count of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		System.out.println(rowCount);

		//Get the first row from the sheet

		Row row = sheet.getRow(0);

		//Create a new row and append it at last of sheet

		Row newRow = sheet.createRow(rowCount+1);

		//Create a loop over the cell of newly created Row

		for(int j = 0; j < row.getLastCellNum(); j++){

			//Fill data in row
			org.apache.poi.ss.usermodel.Cell cell = newRow.createCell(j);
			// Cell cell = (Cell) newRow.createCell(j);

			((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(dataToWrite[j]);

		}

		//Close input stream

		inputStream.close();

		//Create an object of FileOutputStream class to create write data in excel file

		FileOutputStream outputStream = new FileOutputStream(file);

		//write data in the excel file

		workbook.write(outputStream);

		//close output stream

		outputStream.close();



	}
	/**
	 * 
	 * @param sheetName-sheet name present in GMED.xls 
	 * @return
	 */
	public static Map<String, String> readExcelSheet(String sheetName) 
	{
		ExcelFileUtilty testDataObj = new ExcelFileUtilty();
		return testDataObj.readExcelSheet(GmedConfig.getConfig("XLS_DATA"),
				sheetName);
	}

}
