package com.xaero.dictionary.config.db;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import static java.sql.Types.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class SQLiteDialect extends Dialect {

    private static final String ADD_COLUMN_QUERY = "add column";

    public SQLiteDialect() {
        registerColumnType(BIT, "integer");
        registerColumnType(TINYINT, "tinyint");
        registerColumnType(SMALLINT, "smallint");
        registerColumnType(INTEGER, "integer");
        registerColumnType(BIGINT, "bigint");
        registerColumnType(FLOAT, "float");
        registerColumnType(REAL, "real");
        registerColumnType(DOUBLE, "double");
        registerColumnType(NUMERIC, "numeric");
        registerColumnType(DECIMAL, "decimal");
        registerColumnType(CHAR, "char");
        registerColumnType(VARCHAR, "varchar");
        registerColumnType(LONGVARCHAR, "longvarchar");
        registerColumnType(DATE, "date");
        registerColumnType(TIME, "time");
        registerColumnType(TIMESTAMP, "timestamp");
        registerColumnType(BINARY, "blob");
        registerColumnType(VARBINARY, "blob");
        registerColumnType(LONGVARBINARY, "blob");
        registerColumnType(BLOB, "blob");
        registerColumnType(CLOB, "clob");
        registerColumnType(BOOLEAN, "integer");
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getDropForeignKeyString() {
        return EMPTY;
    }

    @Override
    public String getAddForeignKeyConstraintString(String cn, String[] fk, String t, String[] pk, boolean rpk) {
        return EMPTY;
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return EMPTY;
    }

    @Override
    public String getAddColumnString() {
        return ADD_COLUMN_QUERY;
    }
}
