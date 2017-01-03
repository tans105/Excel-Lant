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
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.jdbc.Work;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import entity.RejectTable;
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

	public List<String> getRejectedTables() {
		Session session = null;
		Transaction tx = null;
		List<String> list = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("select tablename from reject_table");

			list = query.list();
			System.out.println("REJECTED TABLES: "+list);
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
			if (i != columnList.size() - 1) {
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
			System.out.println("ALERT!------------>SYSTEM TABLE");
			if (isTablePresentInRejects(tableName)) {
				createOrUpdateRejectionTable(tableName);
			}
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		return list;
	}

	private boolean isTablePresentInRejects(String tableName) {

		Session session = null;
		Transaction tx = null;
		int count = 0;
		try {
			SessionFactory sf = HibernateUtils.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("select count(*) from reject_table where tablename='" + tableName + "'");
			count = Integer.parseInt(query.list().get(0).toString());
			System.out.println("COUNT IS :" + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		if (count > 0)
			return false;
		else {
			return true;
		}
	}

	private void createOrUpdateRejectionTable(String tableName) {
		Session session = null;
		Transaction tx = null;
		try {
			SessionFactory sf = HibernateUtils.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			RejectTable reject = new RejectTable();
			reject.setTableName(tableName);
			session.persist(reject);
			tx.commit();
		} catch (Exception e) {
			System.out.println("FAILED TO CREATE REJECT TABLE");

		} finally {
			DbUtil.closeSession(session);
			DbUtil.rollBackTransaction(tx);
		}
		System.out.println("SAVED");

	}

}
