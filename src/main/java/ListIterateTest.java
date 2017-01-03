import java.util.List;

import service.TableService;

public class ListIterateTest {
	public static void main(String[] args) {
		TableService service = new TableService();
		List<String> rejectedTables=service.getRejectedTables();
		List<String> tableList = service.getTableList();
		List<String> validTables=getCurrentValidTableList(tableList,rejectedTables);
		System.out.println(validTables);
	}
	private static List<String> getCurrentValidTableList(List<String> tableList, List<String> rejectedTables) {
		for(String s:rejectedTables){
			if(tableList.contains(s)){
				tableList.remove(s);
			}
		}
		return tableList;
	}
}
