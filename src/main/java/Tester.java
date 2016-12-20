import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import dao.TableDao;
import entity.RejectTable;
import utils.HibernateUtils;

public class Tester {
	public static void main(String[] args) {
		AnnotationConfiguration config=new AnnotationConfiguration();
		config.addAnnotatedClass(RejectTable.class);
		config.configure("hibernate.cfg.xml");
		
		new SchemaExport(config).create(true,true);
		
		
//		Session session = null;
//		Transaction tx = null;
//		SessionFactory sf = HibernateUtils.getSessionFactory();
//		session = sf.openSession();
//		tx = session.beginTransaction();
//		RejectTable reject = new RejectTable();
//		reject.setId(1);
//		reject.setTableName("test");
//		session.persist(reject);
//		tx.commit();
//		session.close();
//		System.out.println("SAVED");
	}
}
