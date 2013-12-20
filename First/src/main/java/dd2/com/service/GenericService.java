package dd2.com.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 24
 * Time: 오전 10:56
 * To change this template use File | Settings | File Templates.
 */
public interface GenericService<T, ID extends Serializable> {
    T findById(ID id);
    T findById(ID id, boolean lock);
    List<T> findAll();
    List<T> findByExample(T exampleInstance, String... excludeProperty);
    T save(T entity);
    T update(T entity);
    T saveOrUpdate(T entity);
    void delete(T entity);
    void deleteById(ID id);
    void deleteByIds(ID[] ids);
}
