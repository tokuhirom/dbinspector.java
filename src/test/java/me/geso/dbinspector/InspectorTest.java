package me.geso.dbinspector;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

public class InspectorTest extends TestBase {

	@Test
	public void test() throws SQLException, ClassNotFoundException {
		assertEquals(inspector.getTables().size(), 3);
		assertEquals(inspector.getTable("job").get().getName(), "job");
	}

}
