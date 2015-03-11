package me.geso.dbinspector;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class PrimaryKeyTest extends TestBase {

	@Test
	public void test() throws SQLException {
		Optional<Table> table = inspector.getTable("job");
		List<PrimaryKey> primaryKeys = table.get().getPrimaryKeys();
		assertEquals(primaryKeys.size(), 1);
		PrimaryKey pk = primaryKeys.get(0);
		assertEquals(pk.getColumn().getName(), "id");
		assertEquals(pk.getColumnName(), "id");
		assertEquals(pk.getDataType(), java.sql.Types.INTEGER);
		assertEquals(pk.getPrimaryKeyName(), "CONSTRAINT_1");
		assertEquals(pk.getSequenceNumber(), 1);
		assertEquals(pk.getTableCatalog(), "test");
		assertEquals(pk.getTableSchema(), "PUBLIC");
		assertEquals(pk.getTableName(), "job");
	}

}
