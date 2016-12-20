package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import utils.DbUtil;
import utils.HibernateUtils;

public class TableDao {

	public List<String> getTableList() {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();

		final ArrayList<String> tableList = new ArrayList<String>();
		try {
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					java.sql.DatabaseMetaData dbmd;
					dbmd = connection.getMetaData();
					
					java.sql.ResultSet rs = dbmd.getColumns(null, null, "%", null);
					while (rs.next()) {
						if (tableList.contains(rs.getString("TABLE_NAME"))) {
							continue;
						} else {
							tableList.add(rs.getString("TABLE_NAME"));
						}
					}
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeSession(session);
		}

		return tableList;
	}

	public List<String> getColumnNames(final String tableName) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();

		final ArrayList<String> columnNames = new ArrayList<String>();
		try {
			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					java.sql.DatabaseMetaData dbmd;
					dbmd = connection.getMetaData();
					java.sql.ResultSet columns = dbmd.getColumns(null, null, tableName, "%");
					while (columns.next()) {
						columnNames.add(columns.getString("COLUMN_NAME"));
					}
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeSession(session);
		}

		return columnNames;
	}

	public List<Map<Object, Object>> getUserBranchMappingList() {
		Session session = null;
		Transaction tx = null;
		List<Map<Object, Object>> list = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("select * from users")
					.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			list = query.list();
			System.out.println(list.get(0).get("mobile_no"));
			tx.commit();
			tx = null;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return list;
	}

	public List<Object[]> getTableDownloadData(String tableName, List<String> columnList) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> list = null;
		StringBuffer qry = new StringBuffer();
		qry.append("SELECT ");
		for (int i = 0; i < columnList.size(); i++) {
			qry.append(" ");
			qry.append(columnList.get(i));
			if (i != columnList.size()-1) {
				qry.append(",");
			}
			qry.append(" ");
		}

		qry.append("FROM ");
		qry.append(tableName);
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(qry.toString());
			list = query.list();
			tx.commit();
			tx = null;

		} catch (Exception e) {
			System.out.println("ALERT!------------>SYSTEM TABLE" );
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return list;
	}

}
