package dd2.com.service.hibernate;

import dd2.com.dao.GenericDAO;
import dd2.com.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 24
 * Time: 오후 12:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericHibernateService<T, ID extends Serializable> implements GenericService<T, ID> {

    /**
     * 기본 CRUD 가능하게 하는 엔티티 DAO 이다.
     * 상속 받은 쪽에서 구현해 줘야 한다.
     * @return
     */
    protected abstract GenericDAO<T, ID> getGenericDAO();

    @Transactional
    @Override
    public T findById(ID id) {
        return this.getGenericDAO().findById(id);
    }

    @Transactional
    @Override
    public T findById(ID id, boolean lock) {
        return this.getGenericDAO().findById(id, lock);
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return this.getGenericDAO().findAll();
    }

    @Transactional
    @Override
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        return this.getGenericDAO().findByExample(exampleInstance, excludeProperty);
    }

    @Transactional
    @Override
    public T save(T entity) {
        return this.getGenericDAO().save(entity);
    }

    @Transactional
    @Override
    public T update(T entity) {
        return this.getGenericDAO().update(entity);
    }

    @Transactional
    @Override
    public T saveOrUpdate(T entity) {
        return this.getGenericDAO().saveOrUpdate(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        this.getGenericDAO().delete(entity);
    }

    @Transactional
    @Override
    public void deleteById(ID id) {
        this.getGenericDAO().deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByIds(ID[] ids) {
        this.getGenericDAO().deleteByIds(ids);
    }
}
