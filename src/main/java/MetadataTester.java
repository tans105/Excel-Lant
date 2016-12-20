import dao.TableDao;

public class MetadataTester {
	public static void main(String args[]) {
		TableDao dao=new TableDao();
		System.out.println(dao.getTableList());
	}
}
