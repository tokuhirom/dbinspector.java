package me.geso.dbinspector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimaryKey {
	@Override
	public String toString() {
		return "PrimaryKey [tableCatalog=" + tableCatalog + ", tableSchema="
				+ tableSchema + ", tableName=" + tableName + ", columnName="
				+ columnName + ", sequenceNumber=" + sequenceNumber
				+ ", primaryKeyName=" + primaryKeyName + "]";
	}

	private final String tableCatalog;
	private final String tableSchema;
	private final String tableName;
	private final String columnName;
	private final short sequenceNumber;
	private final String primaryKeyName;
	private final Table table;

	/*
	 * <OL> <LI><B>TABLE_CAT</B> String {@code =>} table catalog (may be
	 * <code>null</code>) <LI><B>TABLE_SCHEM</B> String {@code =>} table schema
	 * (may be <code>null</code>) <LI><B>TABLE_NAME</B> String {@code =>} table
	 * name <LI><B>COLUMN_NAME</B> String {@code =>} column name
	 * <LI><B>KEY_SEQ</B> short {@code =>} sequence number within primary key( a
	 * value of 1 represents the first column of the primary key, a value of 2
	 * would represent the second column within the primary key).
	 * <LI><B>PK_NAME</B> String {@code =>} primary key name (may be
	 * <code>null</code>) </OL>
	 */
	public PrimaryKey(Table table, ResultSet stmt) throws SQLException {
		this.tableCatalog = stmt.getString("TABLE_CAT");
		this.tableSchema = stmt.getString("TABLE_SCHEM");
		this.tableName = stmt.getString("TABLE_NAME");
		this.columnName = stmt.getString("COLUMN_NAME");
		this.sequenceNumber = stmt.getShort("KEY_SEQ");
		this.primaryKeyName = stmt.getString("PK_NAME");
		this.table = table;
	}

	public String getTableCatalog() {
		return tableCatalog;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public short getSequenceNumber() {
		return sequenceNumber;
	}

	public String getPrimaryKeyName() {
		return primaryKeyName;
	}
	
	public Column getColumn() {
		return this.table.getColumn(this.getColumnName());
	}

	public int getDataType() {
		return this.getColumn().getDataType();
	}
}
