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
		assertEquals(exportedKeys.size(), 1);
		ExportedKey ek = exportedKeys.get(0);
		assertEquals(ek.getDeferrability(), 7);
		assertEquals(ek.getDeleteRule(), 0);
		assertEquals(ek.getForeignKeyCatalog(), "test");
		assertEquals(ek.getForeignKeyColumn(), "branch_id");
		assertEquals(ek.getForeignKeyName(), "CONSTRAINT_19");
		assertEquals(ek.getForeignKeySchema(), "PUBLIC");
		assertEquals(ek.getForeignKeyTable(), "job");
		assertEquals(ek.getKeySequence(), 1);
		assertEquals(ek.getPrimaryKeyCatalog(), "test");
		assertEquals(ek.getPrimaryKeyColumn(), "id");
		assertEquals(ek.getPrimaryKeyName(), "PRIMARY_KEY_A");
		assertEquals(ek.getPrimaryKeyScehema(), "PUBLIC");
		assertEquals(ek.getPrimaryKeyTable(), "branch");
		assertEquals(ek.getUpdateRule(), 1);
	}

}
