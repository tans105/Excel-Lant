package service;

import java.util.List;

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
}
