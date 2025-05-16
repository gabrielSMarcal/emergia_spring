package br.com.emergia.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    @Override
    public String getTableTypeString() {
        // Remove a definição redundante de primary key
        return "";
    }
}

// Nova classe de suporte à coluna identity:
class SQLiteIdentityColumnSupport extends org.hibernate.dialect.identity.IdentityColumnSupportImpl {
    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) {
        // Define apenas como INTEGER, sem duplicar PRIMARY KEY
        return "INTEGER";
    }
}