package database.annotations;

import database.processor.ColumnNames;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForeignKey {
	String name();
	Class<?> references();
	String foreignKey() default ColumnNames.ID_NAME;
}