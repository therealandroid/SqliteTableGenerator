package database.processor;

import br.com.augustoccesar.querybuilder.query.creation.ColumnType;

/**
 * Created by diogojayme on 8/5/16.
 */
class QueryBuilderUtil {
    static ColumnType getTypeIdentifier(Class<?> classType){
        if(classType.getTypeName().equals("java.lang.String")){
            return  ColumnType.VARCHAR;
        }else if(classType.getTypeName().equalsIgnoreCase("Integer") || classType.getTypeName().equalsIgnoreCase("int")){
            return  ColumnType.INTEGER;
        }else if(classType.getTypeName().equalsIgnoreCase("boolean")){
            return  ColumnType.INTEGER;
        }else if(classType.getTypeName().equalsIgnoreCase("long")){
            return  ColumnType.INTEGER;
        }else{
            return  ColumnType.VARCHAR;
        }
    }
}
