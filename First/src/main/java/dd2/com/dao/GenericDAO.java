package dd2.com.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오전 11:15
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDAO<T, ID extends Serializable> {
    T findById(ID id, boolean lock);
    T findById(ID id);
    List<T> findAll();
    List<T> findByExample(T exampleInstance, String... excludeProperty);
    T save(T entity);
    T update(T entity);
    T saveOrUpdate(T entity);
    void delete(T entity);
    void deleteById(ID id);
    void deleteByIds(ID[] ids);
    void flush();
    void clear();
}
