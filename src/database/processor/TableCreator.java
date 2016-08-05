package database.processor;

import br.com.augustoccesar.querybuilder.builders.TableBuilder;
import br.com.augustoccesar.querybuilder.query.creation.CreateColumn;
import database.annotations.Column;
import database.annotations.ForeignKey;
import database.annotations.PrimaryKey;
import database.annotations.Table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by diogojayme on 8/4/16.
 *
 * http://stackover flow.com/questions/4453159/how-to-get-annotations-of-a-member-variable
 */
public class TableCreator {

    public static String createFrom(Class<?> clazz){

        String tableName = getClassTable(clazz);
        TableBuilder builder = new TableBuilder();
        builder.withName(tableName);

        Map<?, ?> map = getClassColumns(clazz);

        if(map == null)
            throw new NullPointerException("Cannot generate Sql from a class that have no Columns annotations");

        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ColumnProxy columnProxy = (ColumnProxy) pair.getValue();

            if(columnProxy.isPrimaryKey()){
                builder.columns(
                        new CreateColumn()
                                .withName(columnProxy.getColumnName())
                                .ofType(QueryBuilderUtil.getTypeIdentifier(columnProxy.getFieldType()))
                                .primaryKey(true)
                );
            }else if(columnProxy.isForeignKey()){
                builder.foreignKeys(
                        new br.com.augustoccesar.querybuilder.query.creation.ForeignKey().column(columnProxy.getColumnName())
                                .references(columnProxy.getReferencesTable(), columnProxy.getForeignKeyId())
                );
            }else{
                builder.columns(
                        new CreateColumn()
                                .withName(columnProxy.getColumnName())
                                .ofType(QueryBuilderUtil.getTypeIdentifier(columnProxy.getFieldType()))
                );
            }

            it.remove();
        }

        return builder.build();

    }

    private static String getClassTable(Class<?> clazz) {
        if(clazz.isAnnotationPresent(Table.class)){
            Table table = clazz.getAnnotation(Table.class);
            return table.name();
        }else{
            return clazz.getSimpleName();
        }
    }

    private static HashMap<String, ColumnProxy> getClassColumns(Class<?> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            HashMap<String, ColumnProxy> columns = new HashMap<>();

            for (Field field: fields) {
                if(field != null){
                    field.setAccessible(true);
                    ColumnProxy columnProxy = new ColumnProxy();

                    if (field.isAnnotationPresent(PrimaryKey.class)) {
                        PrimaryKey column = field.getAnnotation(PrimaryKey.class);
                        Class<?> fieldType = field.getType();
                        String columnName = column.name();
                        columnProxy.setColumnName(columnName);
                        columnProxy.setFieldType(fieldType);
                        columnProxy.setPrimaryKey(true);
                        columnProxy.setSize(column.size());

                        columns.put(columnName, columnProxy);
                    }else if(field.isAnnotationPresent(ForeignKey.class)){
                        ForeignKey annotation = field.getAnnotation(ForeignKey.class);
                        String columnName = annotation.name();
                        Class<?> references = annotation.references();
                        Class<?> fieldType = field.getType();
                        columnProxy.setForeignKeyId(annotation.foreignKey());
                        columnProxy.setReferencesTable(getClassTable(references));
                        columnProxy.setFieldType(fieldType);
                        columnProxy.setForeignKey(true);
                        columnProxy.setColumnName(columnName);

                        columns.put(columnName, columnProxy);
                    }else{
                        Column column = field.getAnnotation(Column.class);
                        Class<?> fieldType = field.getType();
                        String columnName = column.name();
                        columnProxy.setColumnName(columnName);
                        columnProxy.setFieldType(fieldType);
                        columnProxy.setSize(column.size());

                        columns.put(columnName, columnProxy);
                    }
                }
            }

            return columns;
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

}

