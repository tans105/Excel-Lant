package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name ="reject_table")
public class RejectTable {

	@Id 
	@GenericGenerator(name="tAn$" , strategy="increment")
	@GeneratedValue(generator="tAn$")
	int id;
	String tableName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="table_name",unique = true)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
