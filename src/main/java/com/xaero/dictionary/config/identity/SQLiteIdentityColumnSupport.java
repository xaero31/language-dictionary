package com.xaero.dictionary.config.identity;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    private static final String SELECT_ID = "select last_insert_rowid()";
    private static final String ID_COLUMN_TYPE = "integer";

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) throws MappingException {
        return SELECT_ID;
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return ID_COLUMN_TYPE;
    }
}
