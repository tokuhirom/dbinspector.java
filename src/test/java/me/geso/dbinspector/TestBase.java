package me.geso.dbinspector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

public class TestBase {

	protected Inspector inspector;
	private Connection conn;

	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		this.conn = DriverManager
				.getConnection("jdbc:h2:mem:test;DATABASE_TO_UPPER=FALSE");
		conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS repository (id INTEGER PRIMARY KEY, url VARCHAR(65535), created_on INTEGER UNSIGNED)")
				.execute();
		conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS branch (id INTEGER PRIMARY KEY, repository_id INT UNSIGNED NOT NULL, name VARCHAR(255), created_on INTEGER UNSIGNED, FOREIGN KEY(repository_id) REFERENCES repository(id) ON DELETE CASCADE)")
				.execute();
		conn.prepareStatement(
				"CREATE TABLE IF NOT EXISTS job (id INTEGER PRIMARY KEY, branch_id INT UNSIGNED NOT NULL, log_file_path VARCHAR(255) DEFAULT NULL, status INTEGER UNSIGNED DEFAULT 5, elapsed INTEGER DEFAULT NULL, data LONGBLOB, created_on INTEGER UNSIGNED, FOREIGN KEY(branch_id) REFERENCES branch(id) ON DELETE CASCADE)")
				.execute();
		inspector = new Inspector(conn);
	}

	@After
	public void cleanup() throws SQLException {
		conn.close();
	}

}
