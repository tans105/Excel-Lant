import dao.TableDao;

public class Tester {
	public static void main(String[] args) {
		TableDao dao=new TableDao();
		System.out.println(dao.getTableList());
	}
}
