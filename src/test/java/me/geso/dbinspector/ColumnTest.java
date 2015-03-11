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
		assertEquals(column.getDataType(), java.sql.Types.INTEGER);
		assertEquals(column.getName(), "id");
		assertEquals(column.getOrdinalPosition(), 1);
		assertEquals(column.getSize(), 10);
		assertEquals(column.getTypeName(), "INTEGER");
	}

}
