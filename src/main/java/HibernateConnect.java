import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Product;

public class HibernateConnect {
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Product product = new Product();
		product.setId(1);
		product.setName("COKE");
		product.setCode("C001");
		product.setPrice(new BigDecimal("18.00"));

		

		

		// Save product to database
		Integer productId = (Integer) session.save(product);
		System.out.println(product);
		session.getTransaction().commit();
		session.close();
	}
}
