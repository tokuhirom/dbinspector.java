package me.geso.dbinspector;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class TableTest extends TestBase {

	@Test
	public void testGetColumns() throws Exception {
		final Optional<Table> tableOptional = inspector.getTable("branch");
		assertTrue(tableOptional.isPresent());
		final Table table = tableOptional.get();
		final List<Column> columns = table.getColumns();
		assertEquals(4, columns.size());
		assertEquals("id", columns.get(0).getName());
		assertFalse(table.toString().isEmpty());
	}
}
