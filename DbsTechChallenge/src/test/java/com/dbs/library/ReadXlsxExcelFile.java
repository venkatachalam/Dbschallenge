package com.dbs.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXlsxExcelFile {

	/**
	 * This method return Map of the all row and column with Header
	 *
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static LinkedHashMap<String, LinkedHashMap<String, String>> getRowAndColumnBasedAllData(String filePath,
			String sheetName) {

		LinkedHashMap<String, LinkedHashMap<String, String>> matrixData = new LinkedHashMap<>();

		File file = new File(filePath);
		FileInputStream fs;
		XSSFWorkbook wb;
		try {
			fs = new FileInputStream(file);
			wb = new XSSFWorkbook(fs);
			XSSFSheet dataSheet = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();
			int rowCount = dataSheet.getLastRowNum();
			int colCount = dataSheet.getRow(0).getLastCellNum();

			for (int i = 1; i <= rowCount; i++) {
				LinkedHashMap<String, String> rowMapData = new LinkedHashMap<>();
				for (int j = 1; j < colCount; j++) {
					Cell cell = dataSheet.getRow(i).getCell(j);
					if (!formatter.formatCellValue(cell).isEmpty() && !formatter.formatCellValue(cell).equals(null)) {
						// rowData.add(dataSheet.getRow(i).getCell(j).getStringCellValue());
						switch (cell.getCellType()) {
						case STRING:
							rowMapData.put(dataSheet.getRow(0).getCell(j).getStringCellValue(),
									cell.getStringCellValue());
							break;
						case NUMERIC:
							rowMapData.put(dataSheet.getRow(0).getCell(j).getStringCellValue(),
									String.valueOf(cell.getNumericCellValue()));
							break;
						default:
							throw new Exception("Cell TYpe not supporting");
						}
					}
				}
				matrixData.put(dataSheet.getRow(i).getCell(0).getStringCellValue(), rowMapData);
			}
//			Log.information("========================== getRowAndColumnBasedAllData - Data - XLSXXXXXXXXXXXXX ===============================");
//			Log.information("getRowAndColumnBasedAllData : " + matrixData);
		} catch (Exception e1) {
			System.out.println(e1);
			e1.printStackTrace();

		}
		return matrixData;
	}

	public static ArrayList<ArrayList<String>> getAllRowsInTheSheet(String filePath, String sheetName)
			throws Exception {
		ArrayList<ArrayList<String>> allRowData = new ArrayList<ArrayList<String>>();
		File file = new File(filePath);
		FileInputStream fs;
		XSSFWorkbook wb;
		try {
			fs = new FileInputStream(file);
			wb = new XSSFWorkbook(fs);
			XSSFSheet dataSheet = wb.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();
			int rowCount = dataSheet.getLastRowNum();
			int colCount = dataSheet.getRow(0).getLastCellNum();

			for (int i = 1; i <= rowCount; i++) {
				ArrayList<String> rowData = new ArrayList<>();
				for (int j = 0; j < colCount; j++) {
					// Row rowValue = dataSheet.getRow(i);
//					Cell cell = dataSheet.getRow(i).getCell(j);
					if (!formatter.formatCellValue(dataSheet.getRow(i).getCell(j)).isEmpty()
							&& !formatter.formatCellValue(dataSheet.getRow(i).getCell(j)).equals(null)) {
						rowData.add(formatter.formatCellValue(dataSheet.getRow(i).getCell(j)));
//						switch(cell.getCellType()) {
//							case STRING:
//								rowData.add(cell.getStringCellValue());
//								break;
//							case NUMERIC:
//								rowData.add(String.valueOf(cell.getNumericCellValue()));
//								break;
//							default:
//								throw new Exception("Cell TYpe not supporting");
//						}
					}
				}
				allRowData.add(rowData);
			}
//			Log.information("========================== getAllRowsData - Data- XLSXXXXXXX ===============================");
//			Log.information("getRowBasedAllData : " + allRowData);
		} catch (IOException e1) {
			System.out.println(e1.toString());

		}
		return allRowData;
	}

	public static void writeExcel(String pathToExcel, String sheetName, List<String> tableValues) {
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook();
			// for HSSF (.xls extension)
			// workbook = new HSSFWorkbook();
			// Creating sheet with in the workbook
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet(sheetName);

			Row row = sheet.createRow(0);

			int rowNum = 1;
			for (String value : tableValues) {
				// create new row
				row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(value);
			}
			// Writing sheet data
			FileOutputStream outputStream = new FileOutputStream(pathToExcel);
			workbook.write(outputStream);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
