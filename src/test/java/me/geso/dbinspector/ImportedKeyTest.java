package me.geso.dbinspector;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

public class ImportedKeyTest extends TestBase {

	@Test
	public void test() throws SQLException {
		List<ImportedKey> importedKeys = inspector.getTable("branch").get().getImportedKeys();
		assertEquals(importedKeys.size(), 1);
		ImportedKey key = importedKeys.get(0);
		assertEquals(key.getDeferrability(), 7);
		assertEquals(key.getDeleteRule(), 0);
		assertEquals(key.getForeignKeyCatalog(), "test");
		assertEquals(key.getForeignKeyColumn(), "repository_id");
		assertEquals(key.getForeignKeyName(), "CONSTRAINT_AD");
		assertEquals(key.getForeignKeySchema(), "PUBLIC");
		assertEquals(key.getForeignKeyTable() , "branch");
		assertEquals(key.getKeySequence(), 1);
		assertEquals(key.getPrimaryKeyCatalog(), "test");
		assertEquals(key.getPrimaryKeyColumn(), "id");
		assertEquals(key.getPrimaryKeyName(), "PRIMARY_KEY_7");
		assertEquals(key.getPrimaryKeyScehema(), "PUBLIC");
		assertEquals(key.getPrimaryKeyTable(), "repository");
		assertEquals(key.getUpdateRule(), 1);
	}

}
