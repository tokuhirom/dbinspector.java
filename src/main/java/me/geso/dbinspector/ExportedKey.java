package me.geso.dbinspector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportedKey {
	private final String primaryKeyCatalog;
	private final String primaryKeyScehema;
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

	private final Table table;

	/*
	 * Retrieves a description of the foreign key columns that reference the
	 * given table's primary key columns (the foreign keys exported by a
	 * table).  They are ordered by FKTABLE_CAT, FKTABLE_SCHEM,
	 * FKTABLE_NAME, and KEY_SEQ.
	 *
	 * <P>Each foreign key column description has the following columns:
	 *  <OL>
	 *  <LI><B>PKTABLE_CAT</B> String {@code =>} primary key table catalog (may be <code>null</code>)
	 *  <LI><B>PKTABLE_SCHEM</B> String {@code =>} primary key table schema (may be <code>null</code>)
	 *  <LI><B>PKTABLE_NAME</B> String {@code =>} primary key table name
	 *  <LI><B>PKCOLUMN_NAME</B> String {@code =>} primary key column name
	 *  <LI><B>FKTABLE_CAT</B> String {@code =>} foreign key table catalog (may be <code>null</code>)
	 *      being exported (may be <code>null</code>)
	 *  <LI><B>FKTABLE_SCHEM</B> String {@code =>} foreign key table schema (may be <code>null</code>)
	 *      being exported (may be <code>null</code>)
	 *  <LI><B>FKTABLE_NAME</B> String {@code =>} foreign key table name
	 *      being exported
	 *  <LI><B>FKCOLUMN_NAME</B> String {@code =>} foreign key column name
	 *      being exported
	 *  <LI><B>KEY_SEQ</B> short {@code =>} sequence number within foreign key( a value
	 *  of 1 represents the first column of the foreign key, a value of 2 would
	 *  represent the second column within the foreign key).
	 *  <LI><B>UPDATE_RULE</B> short {@code =>} What happens to
	 *       foreign key when primary is updated:
	 *      <UL>
	 *      <LI> importedNoAction - do not allow update of primary
	 *               key if it has been imported
	 *      <LI> importedKeyCascade - change imported key to agree
	 *               with primary key update
	 *      <LI> importedKeySetNull - change imported key to <code>NULL</code> if
	 *               its primary key has been updated
	 *      <LI> importedKeySetDefault - change imported key to default values
	 *               if its primary key has been updated
	 *      <LI> importedKeyRestrict - same as importedKeyNoAction
	 *                                 (for ODBC 2.x compatibility)
	 *      </UL>
	 *  <LI><B>DELETE_RULE</B> short {@code =>} What happens to
	 *      the foreign key when primary is deleted.
	 *      <UL>
	 *      <LI> importedKeyNoAction - do not allow delete of primary
	 *               key if it has been imported
	 *      <LI> importedKeyCascade - delete rows that import a deleted key
	 *      <LI> importedKeySetNull - change imported key to <code>NULL</code> if
	 *               its primary key has been deleted
	 *      <LI> importedKeyRestrict - same as importedKeyNoAction
	 *                                 (for ODBC 2.x compatibility)
	 *      <LI> importedKeySetDefault - change imported key to default if
	 *               its primary key has been deleted
	 *      </UL>
	 *  <LI><B>FK_NAME</B> String {@code =>} foreign key name (may be <code>null</code>)
	 *  <LI><B>PK_NAME</B> String {@code =>} primary key name (may be <code>null</code>)
	 *  <LI><B>DEFERRABILITY</B> short {@code =>} can the evaluation of foreign key
	 *      constraints be deferred until commit
	 *      <UL>
	 *      <LI> importedKeyInitiallyDeferred - see SQL92 for definition
	 *      <LI> importedKeyInitiallyImmediate - see SQL92 for definition
	 *      <LI> importedKeyNotDeferrable - see SQL92 for definition
	 *      </UL>
	 *  </OL>
	 *
	 */
	public ExportedKey(Table table, ResultSet stmt) throws SQLException {
		this.table = table;
		this.primaryKeyCatalog = stmt.getString("PKTABLE_CAT");
		this.primaryKeyScehema = stmt.getString("PKTABLE_SCHEM");
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

	public String getPrimaryKeyCatalog() {
		return primaryKeyCatalog;
	}

	public String getPrimaryKeyScehema() {
		return primaryKeyScehema;
	}

	public String getPrimaryKeyTable() {
		return primaryKeyTable;
	}

	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	public String getForeignKeyCatalog() {
		return foreignKeyCatalog;
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

	public Table getTable() {
		return table;
	}
}
