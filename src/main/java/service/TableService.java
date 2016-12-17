package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;

import dao.TableDao;

public class TableService {
	public List<String> getTableList() {
		TableDao dao = new TableDao();
		return dao.getTableList();
	}

	public List<String> getColumnsList(String tableName) {
		TableDao dao = new TableDao();
		return dao.getColumnNames(tableName);

	}

	public HSSFCellStyle styleWorkbookCells(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.OLIVE_GREEN.index);
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setBoldweight((short) 5);
		style.setFont(font);
		return style;
	}

	public Workbook getWorkbook(String tableName, List<String> columnNames) {
		TableDao dao = new TableDao();
		List<Object[]> list = new ArrayList<Object[]>();
		list=dao.getTableDownloadData(tableName, columnNames);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFCellStyle style = styleWorkbookCells(workbook);
		int row = 0;
		HSSFRow rowTitle = sheet.createRow(row);
		HSSFCell[] cellTitle = new HSSFCell[columnNames.size()];

		for (int i = 0; i < columnNames.size(); i++) {
			cellTitle[i] = rowTitle.createCell(i);
			cellTitle[i].setCellValue(columnNames.get(i));
			cellTitle[i].setCellStyle(style);
		}
		row++;
		HSSFRow emptyRow = sheet.createRow(row);
		row++;
		for (Object[] obj : list) {
			HSSFRow rowValue = sheet.createRow(row);
			for (int i = 0; i < obj.length; i++) {
				HSSFCell[] cell = new HSSFCell[obj.length];

				cell[i] = rowValue.createCell(i);
				if (null != obj[i]) {
					cell[i].setCellValue(obj[i].toString());
				} else
					cell[i].setCellValue(" ");

			}
			row++;
		}
		/*---------------------Sizing the column width------------------------*/
		for (int i = 0; i < columnNames.size(); i++) {
			sheet.autoSizeColumn(i);
		}
		/*---------------------------------------------------------------*/

		return workbook;
	}
}
