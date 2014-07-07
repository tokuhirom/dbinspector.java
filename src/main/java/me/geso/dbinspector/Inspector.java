package me.geso.dbinspector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inspector {
	private Connection connection;
	private String catalog;
	private String schemaPattern;

	public Inspector(Connection connection) {
		this.connection = connection;
		this.catalog = null;
		this.schemaPattern = "%";
	}

	public Inspector(Connection connection, String catalog, String schemaPattern) {
		this.connection = connection;
		this.catalog = catalog;
		this.schemaPattern = schemaPattern;
	}

	public List<Table> getTables() throws SQLException {
		return this.getTables("%");
	}

	private List<Table> getTables(String tablePattern) throws SQLException {
		DatabaseMetaData metaData = connection.getMetaData();
		String[] types = new String[1];
		types[0] = "TABLE";
		ResultSet stmt = metaData.getTables(catalog, schemaPattern,
				tablePattern, types);
		List<Table> result = new ArrayList<>();
		while (stmt.next()) {
			result.add(new Table(connection, catalog, schemaPattern, stmt));
		}
		return result;
	}

	public Optional<Table> getTable(String name) throws SQLException {
		List<Table> tables = this.getTables(name);
		if (tables.size() > 0) {
			return Optional.of(tables.get(0));
		} else {
			return Optional.empty();
		}
	}
}
