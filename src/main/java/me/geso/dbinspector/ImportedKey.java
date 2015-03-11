package me.geso.dbinspector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportedKey {

	@Override
	public String toString() {
		return "ImportedKey{" +
				"table=" + table +
				", primaryKeyCatalog='" + primaryKeyCatalog + '\'' +
				", primaryKeySchema='" + primaryKeySchema + '\'' +
				", primaryKeyTable='" + primaryKeyTable + '\'' +
				", primaryKeyColumn='" + primaryKeyColumn + '\'' +
				", foreignKeyCatalog='" + foreignKeyCatalog + '\'' +
				", foreignKeySchema='" + foreignKeySchema + '\'' +
				", foreignKeyTable='" + foreignKeyTable + '\'' +
				", foreignKeyColumn='" + foreignKeyColumn + '\'' +
				", keySequence=" + keySequence +
				", updateRule=" + updateRule +
				", deleteRule=" + deleteRule +
				", foreignKeyName='" + foreignKeyName + '\'' +
				", primaryKeyName='" + primaryKeyName + '\'' +
				", deferrability=" + deferrability +
				'}';
	}

	private final Table table;
	private final String primaryKeyCatalog;
	private final String primaryKeySchema;
	private final String primaryKeyTable;
	private final String primaryKeyColumn;
	private final String foreignKeyCatalog;
	private final String foreignKeySchema;
	private final String foreignKeyTable;
	private final String foreignKeyColumn;
	private final short keySequence;
	private final short updateRule;
	private final short deleteRule;
	private final String foreignKeyName;
	private final String primaryKeyName;
	private final short deferrability;

	public ImportedKey(Table table, ResultSet stmt) throws SQLException {
		this.table = table;
		this.primaryKeyCatalog = stmt.getString("PKTABLE_CAT");
		this.primaryKeySchema = stmt.getString("PKTABLE_SCHEM");
		this.primaryKeyTable = stmt.getString("PKTABLE_NAME");
		this.primaryKeyColumn = stmt.getString("PKCOLUMN_NAME");
		this.foreignKeyCatalog = stmt.getString("FKTABLE_CAT");
		this.foreignKeySchema = stmt.getString("FKTABLE_SCHEM");
		this.foreignKeyTable = stmt.getString("FKTABLE_NAME");
		this.foreignKeyColumn = stmt.getString("FKCOLUMN_NAME");
		this.keySequence = stmt.getShort("KEY_SEQ");
		this.updateRule = stmt.getShort("UPDATE_RULE");
		this.deleteRule = stmt.getShort("DELETE_RULE");
		this.foreignKeyName = stmt.getString("FK_NAME");
		this.primaryKeyName = stmt.getString("PK_NAME");
		this.deferrability = stmt.getShort("DEFERRABILITY");
	}

	public Table getTable() {
		return this.table;
	}

	public String getPrimaryKeyCatalog() {
		return primaryKeyCatalog;
	}

	public String getPrimaryKeySchema() {
		return primaryKeySchema;
	}

	public String getPrimaryKeyTable() {
		return primaryKeyTable;
	}

	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	public String getForeignKeySchema() {
		return foreignKeySchema;
	}

	public String getForeignKeyTable() {
		return foreignKeyTable;
	}

	public String getForeignKeyColumn() {
		return foreignKeyColumn;
	}

	public short getKeySequence() {
		return keySequence;
	}

	public short getUpdateRule() {
		return updateRule;
	}

	public short getDeleteRule() {
		return deleteRule;
	}

	public String getForeignKeyName() {
		return foreignKeyName;
	}

	public String getPrimaryKeyName() {
		return primaryKeyName;
	}

	public short getDeferrability() {
		return deferrability;
	}

	public String getForeignKeyCatalog() {
		return foreignKeyCatalog;
	}

}
