package me.geso.dbinspector;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class ExportedKeyTest extends TestBase {

	@Test
	public void test() throws SQLException {
		Optional<Table> table = inspector.getTable("branch");
		List<ExportedKey> exportedKeys = table.get().getExportedKeys();
		assertEquals(1, exportedKeys.size());
		ExportedKey ek = exportedKeys.get(0);
		assertEquals("branch", ek.getTable().getName());
		assertEquals(7, ek.getDeferrability());
		assertEquals(0, ek.getDeleteRule());
		assertEquals("test", ek.getForeignKeyCatalog());
		assertEquals("branch_id", ek.getForeignKeyColumn());
		assertEquals("CONSTRAINT_19", ek.getForeignKeyName());
		assertEquals("PUBLIC", ek.getForeignKeySchema());
		assertEquals("job", ek.getForeignKeyTable());
		assertEquals(1, ek.getKeySequence());
		assertEquals("test", ek.getPrimaryKeyCatalog());
		assertEquals("id", ek.getPrimaryKeyColumn());
		assertEquals("PRIMARY_KEY_A", ek.getPrimaryKeyName());
		assertEquals("PUBLIC", ek.getPrimaryKeySchema());
		assertEquals("branch", ek.getPrimaryKeyTable());
		assertEquals(1, ek.getUpdateRule());
	}

}
