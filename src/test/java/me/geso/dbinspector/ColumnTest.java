package me.geso.dbinspector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.Test;

public class ColumnTest extends TestBase {

	@Test
	public void test() throws SQLException {
		Optional<Table> table = inspector.getTable("job");
		assertTrue(table.isPresent());
		Column column = table.get().getColumn("id");
		assertEquals(java.sql.Types.INTEGER, column.getDataType());
		assertEquals("id", column.getName());
		assertEquals(1, column.getOrdinalPosition());
		assertEquals(10, column.getSize());
		assertEquals("INTEGER", column.getTypeName());
		assertEquals(false, column.isNullable());
		assertEquals(false, column.isAutoIncrement());
	}

}
