package database.processor;

import br.com.augustoccesar.querybuilder.query.creation.ColumnType;

/**
 * Created by diogojayme on 8/4/16.
 */
class ColumnProxy {
    private int size;
    private String columnName;
    private boolean primaryKey;
    private Class<?> fieldType;
    private String foreignKeyId;
    private String referencesTable;
    private boolean foreignKey;

    Class<?> getFieldType() {
        return fieldType;
    }

     void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

     String getColumnName() {
        return columnName;
    }

     void setColumnName(String columnName) {
        this.columnName = columnName;
    }

     boolean isPrimaryKey() {
        return primaryKey;
    }

     void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

     int getSize() {
        return size;
    }

     void setSize(int size) {
        this.size = size;
    }

     String getForeignKeyId() {
        return foreignKeyId;
    }

     void setForeignKeyId(String foreignKeyId) {
        this.foreignKeyId = foreignKeyId;
    }

     String getReferencesTable() {
        return referencesTable;
    }

     void setReferencesTable(String referencesTable) {
        this.referencesTable = referencesTable;
    }

     boolean isForeignKey() {
        return foreignKey;
    }

     void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

}
