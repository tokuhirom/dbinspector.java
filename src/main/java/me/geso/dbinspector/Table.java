package me.geso.dbinspector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Table {

	@Override
	public String toString() {
		return "Table [connection=" + connection + ", name=" + name
				+ ", typeName=" + getTypeName() + ", type=" + getType() + "]";
	}

	private final Connection connection;
	private final String name;
	private final String typeName;
	private final String type;
	private final String schema;
	private final String catalog;
	private List<Column> columns;
	private final List<ImportedKey> importedKeys;
	private final List<ExportedKey> exportedKeys;

	/*
	 * <OL> <LI><B>TABLE_CAT</B> String {@code =>} table catalog (may be
	 * <code>null</code>) <LI><B>TABLE_SCHEM</B> String {@code =>} table schema
	 * (may be <code>null</code>) <LI><B>TABLE_NAME</B> String {@code =>} table
	 * name <LI><B>TABLE_TYPE</B> String {@code =>} table type. Typical types
	 * are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY",
	 * "LOCAL TEMPORARY", "ALIAS", "SYNONYM". <LI><B>REMARKS</B> String {@code
	 * =>} explanatory comment on the table <LI><B>TYPE_CAT</B> String {@code
	 * =>} the types catalog (may be <code>null</code>) <LI><B>TYPE_SCHEM</B>
	 * String {@code =>} the types schema (may be <code>null</code>)
	 * <LI><B>TYPE_NAME</B> String {@code =>} type name (may be
	 * <code>null</code>) <LI><B>SELF_REFERENCING_COL_NAME</B> String {@code =>}
	 * name of the designated "identifier" column of a typed table (may be
	 * <code>null</code>) <LI><B>REF_GENERATION</B> String {@code =>} specifies
	 * how values in SELF_REFERENCING_COL_NAME are created. Values are "SYSTEM",
	 * "USER", "DERIVED". (may be <code>null</code>) </OL>
	 */
	public Table(Connection connection, String catalog, String schema, ResultSet stmt) throws SQLException {
		this.connection = connection;
		this.name = stmt.getString("TABLE_NAME");
		this.type = stmt.getString("TABLE_TYPE");
		this.typeName = stmt.getString("TYPE_NAME");
		this.catalog = catalog;
		this.schema = schema;
		this.columns = this.buildColumns();
		this.importedKeys = this.buildImportedKeys();
		this.exportedKeys = this.buildExportedKeys();
	}

	private List<ImportedKey> buildImportedKeys() throws SQLException {
		List<ImportedKey> keys = new ArrayList<>();
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet stmt = metaData.getImportedKeys(catalog, schema, name);
		while (stmt.next()) {
			keys.add(new ImportedKey(this, stmt));
		}
		return keys;
	}

	private List<ExportedKey> buildExportedKeys() throws SQLException {
		List<ExportedKey> keys = new ArrayList<>();
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet stmt = metaData.getExportedKeys(catalog, schema, name);
		while (stmt.next()) {
			keys.add(new ExportedKey(this, stmt));
		}
		return keys;
	}

	public String getName() {
		return name;
	}

	// TODO: Should we use streaming api?
	public List<Column> getColumns() throws SQLException {
		return columns;
	}

	public List<PrimaryKey> getPrimaryKeys() throws SQLException {
		List<PrimaryKey> columns = new ArrayList<>();
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet stmt = metaData.getPrimaryKeys(catalog, schema, name);

		while (stmt.next()) {
			columns.add(new PrimaryKey(this, stmt));
		}
		return columns;
	}

	private List<Column> buildColumns() throws SQLException {
		List<Column> columns = new ArrayList<>();
		ResultSet stmt = connection.getMetaData().getColumns(catalog, schema, name,
				"%");

		while (stmt.next()) {
			columns.add(new Column(stmt));
		}
		return columns;
	}

	public String getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}

	public Column getColumn(String columnName) {
		for (Column column: this.columns) {
			if (column.getName().equals(columnName)) {
				return column;
			}
		}
		return null;
	}

	public List<ImportedKey> getImportedKeys() {
		return importedKeys;
	}

	public List<ExportedKey> getExportedKeys() {
		return exportedKeys;
	}

}
