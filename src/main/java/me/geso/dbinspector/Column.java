package me.geso.dbinspector;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Column {

	private final String name;
	private final String typeName;
	private final int size;
	private final int ordinalPosition;
	private final boolean nullable;
	private final int dataType;
	private final boolean isAutoIncrement;

	/*
	 * <OL>
	 * <LI><B>TABLE_CAT</B> String {@code =>} table catalog (may be
	 * <code>null</code>)
	 * <LI><B>TABLE_SCHEM</B> String {@code =>} table schema (may be
	 * <code>null</code>)
	 * <LI><B>TABLE_NAME</B> String {@code =>} table name
	 * <LI><B>COLUMN_NAME</B> String {@code =>} column name
	 * <LI><B>DATA_TYPE</B> int {@code =>} SQL type from java.sql.Types
	 * <LI><B>TYPE_NAME</B> String {@code =>} Data source dependent type name,
	 * for a UDT the type name is fully qualified
	 * <LI><B>COLUMN_SIZE</B> int {@code =>} column size.
	 * <LI><B>BUFFER_LENGTH</B> is not used.
	 * <LI><B>DECIMAL_DIGITS</B> int {@code =>} the number of fractional digits.
	 * Null is returned for data types where DECIMAL_DIGITS is not applicable.
	 * <LI><B>NUM_PREC_RADIX</B> int {@code =>} Radix (typically either 10 or 2)
	 * <LI><B>NULLABLE</B> int {@code =>} is NULL allowed.
	 * <UL>
	 * <LI>columnNoNulls - might not allow <code>NULL</code> values
	 * <LI>columnNullable - definitely allows <code>NULL</code> values
	 * <LI>columnNullableUnknown - nullability unknown
	 * </UL>
	 * <LI><B>REMARKS</B> String {@code =>} comment describing column (may be
	 * <code>null</code>)
	 * <LI><B>COLUMN_DEF</B> String {@code =>} default value for the column,
	 * which should be interpreted as a string when the value is enclosed in
	 * single quotes (may be <code>null</code>)
	 * <LI><B>SQL_DATA_TYPE</B> int {@code =>} unused
	 * <LI><B>SQL_DATETIME_SUB</B> int {@code =>} unused
	 * <LI><B>CHAR_OCTET_LENGTH</B> int {@code =>} for char types the maximum
	 * number of bytes in the column
	 * <LI><B>ORDINAL_POSITION</B> int {@code =>} index of column in table
	 * (starting at 1)
	 * <LI><B>IS_NULLABLE</B> String {@code =>} ISO rules are used to determine
	 * the nullability for a column.
	 * <UL>
	 * <LI>YES --- if the column can include NULLs
	 * <LI>NO --- if the column cannot include NULLs
	 * <LI>empty string --- if the nullability for the column is unknown
	 * </UL>
	 * <LI><B>SCOPE_CATALOG</B> String {@code =>} catalog of table that is the
	 * scope of a reference attribute (<code>null</code> if DATA_TYPE isn't REF)
	 * <LI><B>SCOPE_SCHEMA</B> String {@code =>} schema of table that is the
	 * scope of a reference attribute (<code>null</code> if the DATA_TYPE isn't
	 * REF)
	 * <LI><B>SCOPE_TABLE</B> String {@code =>} table name that this the scope
	 * of a reference attribute (<code>null</code> if the DATA_TYPE isn't REF)
	 * <LI><B>SOURCE_DATA_TYPE</B> short {@code =>} source type of a distinct
	 * type or user-generated Ref type, SQL type from java.sql.Types (
	 * <code>null</code> if DATA_TYPE isn't DISTINCT or user-generated REF)
	 * <LI><B>IS_AUTOINCREMENT</B> String {@code =>} Indicates whether this
	 * column is auto incremented
	 * <UL>
	 * <LI>YES --- if the column is auto incremented
	 * <LI>NO --- if the column is not auto incremented
	 * <LI>empty string --- if it cannot be determined whether the column is
	 * auto incremented
	 * </UL>
	 * <LI><B>IS_GENERATEDCOLUMN</B> String {@code =>} Indicates whether this is
	 * a generated column
	 * <UL>
	 * <LI>YES --- if this a generated column
	 * <LI>NO --- if this not a generated column
	 * <LI>empty string --- if it cannot be determined whether this is a
	 * generated column
	 * </UL>
	 * </OL>
	 */
	public Column(ResultSet stmt) throws SQLException {
		this.name = stmt.getString("COLUMN_NAME");
		this.typeName = stmt.getString("TYPE_NAME");
		this.size = stmt.getInt("COLUMN_SIZE");
		this.nullable = stmt.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
		this.ordinalPosition = stmt.getInt("ORDINAL_POSITION");
		this.dataType = stmt.getInt("DATA_TYPE");
		this.isAutoIncrement = stmt.getString("IS_AUTOINCREMENT").equals("YES");
	}

	public String getName() {
		return name;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getSize() {
		return size;
	}

	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	public boolean isNullable() {
		return nullable;
	}

	public int getDataType() {
		return dataType;
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

}
