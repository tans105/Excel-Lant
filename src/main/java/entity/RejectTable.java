package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RejectTable {

	@Id
	int id;
	String tableName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
