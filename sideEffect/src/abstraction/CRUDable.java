/**
 * 
 */
package abstraction;

import java.util.List;

/**
 * @author Adam Hun/���ѿ�
 * @date 2013. 10. 29.
 * @description
 * TODO TODO
 */
public interface CRUDable {
	
	public abstract <T extends IDValuePair> T create(T t);
	public abstract <T extends IDValuePair> T update(T t);
	public abstract <T extends IDValuePair> boolean delete(T t);
	<T extends IDValuePair>List<T> list(T t);
	<T extends IDValuePair>List<T> list(T t, int page, int maxResults);
	<T extends IDValuePair>List<T> list(Class<T> t);
	<T extends IDValuePair>List<T> list(Class<T> t, int page, int maxResults);
	
	public abstract <T extends IDValuePair>T  read(Class clas, long id);
	public abstract <T extends IDValuePair>T  readCompany(Class clas, long id);
	public abstract <T extends IDValuePair>T  readProduct(Class clas, long id);
	public abstract <T extends IDValuePair> T merge(T t);
	

}
