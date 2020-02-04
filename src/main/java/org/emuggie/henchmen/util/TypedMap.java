package org.emuggie.henchmen.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Referenced Or Self Hosting Map wrapper which offers shortened methods.
 * @author emuggie
 *
 */
@SuppressWarnings("unchecked")
public class TypedMap implements Map<Object, Object>, Serializable{
	private static final long serialVersionUID = 1L;

	public static TypedMap of(Map<Object, Object> map){
		return new TypedMap(map);
	}
	
	private Map<Object, Object> $this; 
	
	/**
	 * Self hosting mode.
	 */
	public TypedMap(){
		$this = new HashMap<Object, Object>();
	}
	
	/**
	 * Create TypedMap which all action will be applied to referenced map
	 * @param ref
	 */
	public TypedMap(Map<Object, Object> ref){
		$this = ref;
	}
	
	/**
	 * Return value with casting and throws if not castable
	 * @param key
	 * @return
	 */
	public <T>T tryGet(Object key){
		return (T)$this.get(key);
	}
	
	/**
	 * Return if value is castable or return default value
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public <T>T getOrElse(Object key, T defaultValue) {
		try {
			T value = this.tryGet(key);
			if(value == null){
				return defaultValue;
			}
			return (T)defaultValue.getClass().cast(value);
		}catch(Exception e) {
			if(defaultValue == null){
				return defaultValue;
			}
			try{
				return (T)TypeCast.cast(this.get(key), defaultValue.getClass()); 
			}catch(Exception err){
				return null;
			}
		}
	}
	
	/**
	 * Method set of Get -> Check -> Set 
	 * @param key
	 * @param value
	 * @return
	 */
	public <T>T getOrSet(Object key, T value) {
		try {
			T prev = this.tryGet(key);
			if(prev == null){
				this.put(key, value);
				return value;
			}
			return prev;
		}catch(Exception e) {
			this.put(key, value);
			return value;
		}
	}
	
	/**
	 * Remove and return or default value
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public <T>T removeOrElse(Object key, T defaultValue){
		T value = this.getOrElse(key, defaultValue);
		this.remove(key);
		return value;
	}
	
	public <T> Collection<T> valuesAs(){
		return (Collection<T>)$this.values();		
	}

	@Override
	public int size() {
		return $this.size();
	}

	@Override
	public boolean isEmpty() {
		return $this.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return $this.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return $this.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return $this.get(key);
	}

	@Override
	public Object put(Object key, Object value) {
		return $this.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return $this.remove(key);
	}

	@Override
	public void putAll(Map<? extends Object, ? extends Object> m) {
		$this.putAll(m);
	}

	@Override
	public void clear() {
		$this.clear();
	}

	@Override
	public Set<Object> keySet() {
		return $this.keySet();
	}

	@Override
	public Collection<Object> values() {
		return $this.values();
	}

	@Override
	public Set<java.util.Map.Entry<Object, Object>> entrySet() {
		return $this.entrySet();
	}
}