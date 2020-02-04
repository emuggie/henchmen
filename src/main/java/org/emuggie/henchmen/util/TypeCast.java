package org.emuggie.henchmen.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

@SuppressWarnings({"unchecked"})
public class TypeCast {
	/**
	 * Converts commonly used types to another. 
	 * @param value
	 * @param clazz
	 * @param args
	 * @return Type of clazz.
	 */
	
	public static <T>T cast(Object value, Class<T> clazz, Object... args){
		if(value == null){
			throw new RuntimeException("Null value cannot be casted to type");
		}
		
		// cast if is assignable.
		if(clazz.isAssignableFrom(value.getClass())){
			return clazz.cast(value);
		}
		
		// String is final class
		if(String.class.isAssignableFrom(clazz)){
			return (T)String.valueOf(value);
		}
		
		// Number : String to number or number to number
		if(Number.class.isAssignableFrom(clazz)){
			// force cast using double conversion
			if(Number.class.isAssignableFrom(value.getClass())){
				return TypeCast.castNumber((Number)value, clazz);
			}
			return TypeCast.castNumber(Double.parseDouble(String.valueOf(value)), clazz);
		}
		
		// DateTime : String to date or number to date
		if(Date.class.isAssignableFrom(clazz)){
			// String to Date
			if(value instanceof String){
				if(args.length == 0 || !(args[0] instanceof DateFormat)){
					throw new RuntimeException("args[0] requires DateFormat to convert");
				}
				DateFormat df = (DateFormat)args[0];
				try {
					return clazz.cast(df.parse((String)value));
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
			// Number to Date
			if(value instanceof Number){
				return clazz.cast(new Date(((Number)value).longValue()));
			}
			throw new RuntimeException("Unidentified Type conversion");
		}
		throw new RuntimeException("Undefined cast types. Ask for developer.");
	}
	
	public static <T> T castNumber(Number num, Class<T> clazz){
		Object t[] = new Object[]{
			num.doubleValue()
			, num.longValue()
			, num.floatValue()
			, num.intValue()
			, num.shortValue()
			, num.byteValue()
		};
		
		for(Object val : t){
			if(clazz.isAssignableFrom(val.getClass())){
				return (T)val;
			}
		}
		throw new RuntimeException("Uncastable type : " + clazz.getCanonicalName());
	}
	
}
