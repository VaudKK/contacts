package com.phone.contacts.config.sqlite;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import java.sql.Types;


public class SQLiteDialect extends Dialect {

    /**
     * Register sqlite data types
     */
    public SQLiteDialect(){
        registerColumnType(Types.INTEGER,"integer");
        registerColumnType(Types.VARCHAR,"varchar");
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SqliteIdentityColumnSupport();
    }

    //Disable features that sqlite does not support

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
        return "";
    }

    @Override
    public String getAddForeignKeyConstraintString(String cn,
                                                   String[] fk, String t, String[] pk, boolean rpk) {
        return "";
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return "";
    }
}
