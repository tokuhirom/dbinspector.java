package me.geso.dbinspector;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

public class ImportedKeyTest extends TestBase {

	@Test
	public void test() throws SQLException {
		List<ImportedKey> importedKeys = inspector.getTable("branch").get().getImportedKeys();
		assertEquals(importedKeys.size(), 1);
		ImportedKey key = importedKeys.get(0);
		assertEquals("branch", key.getTable().getName());
		assertEquals(7, key.getDeferrability());
		assertEquals(0, key.getDeleteRule());
		assertEquals("test", key.getForeignKeyCatalog());
		assertEquals("repository_id", key.getForeignKeyColumn());
		assertEquals("CONSTRAINT_AD", key.getForeignKeyName());
		assertEquals("PUBLIC", key.getForeignKeySchema());
		assertEquals("branch", key.getForeignKeyTable());
		assertEquals(1, key.getKeySequence());
		assertEquals("test", key.getPrimaryKeyCatalog());
		assertEquals("id", key.getPrimaryKeyColumn());
		assertEquals("PRIMARY_KEY_7", key.getPrimaryKeyName());
		assertEquals("PUBLIC", key.getPrimaryKeySchema());
		assertEquals("repository", key.getPrimaryKeyTable());
		assertEquals(1, key.getUpdateRule());
		assertFalse(key.toString().isEmpty());
	}

}
