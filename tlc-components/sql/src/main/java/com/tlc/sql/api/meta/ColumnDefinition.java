package com.tlc.sql.api.meta;

import com.tlc.commons.code.ErrorCode;
import com.tlc.sql.internal.status.SQLErrorCodes;

import java.util.Objects;


/**
 * @author Abishek
 * @version 1.0
 */
public class ColumnDefinition
{
	public static final int DEFAULT_COLUMN_LENGTH = 250;

	private final String columnName;
    private final DataType dataType;
    
    private final int maxLength;
    private final Object defaultValue;
    private final boolean nullable;

	public ColumnDefinition(String columnName, DataType dataType)
	{
		this(columnName, dataType, DEFAULT_COLUMN_LENGTH, null, false);
	}

	public ColumnDefinition(String columnName, DataType dataType, boolean nullable)
	{
		this(columnName, dataType, DEFAULT_COLUMN_LENGTH, null, nullable);
	}

	public ColumnDefinition(String columnName, DataType dataType, Object defaultValue, boolean nullable)
	{
		this(columnName, dataType, DEFAULT_COLUMN_LENGTH, defaultValue, nullable);
	}

	public ColumnDefinition(String columnName, DataType dataType, int maxLength, Object defaultValue, boolean nullable)
    {
		if(maxLength < 0)
		{
			throw ErrorCode.get(SQLErrorCodes.DB_INVALID_COLUMN_MAX_LENGTH);
		}
		this.columnName = Objects.requireNonNull(columnName);
    	this.dataType = Objects.requireNonNull(dataType);
		this.maxLength = maxLength;
		this.defaultValue = defaultValue;
		this.nullable = nullable;
	}

	public ColumnDefinition copyNullable()
	{
		return new ColumnDefinition(columnName, dataType, maxLength, defaultValue, true);
	}

	public DataType getDataType()
	{
		return dataType;
	}
	
	public int getMaxLength()
	{
		return maxLength;
	}

	
	public Object getDefaultValue()
	{
		return defaultValue;
	}

	public boolean isNullable()
	{
		return nullable;
	}

	public String getColumnName()
	{
		return columnName;
	}
}
