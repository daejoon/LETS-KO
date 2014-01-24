package dd2.com.dao.hibernate;

import dd2.com.dao.GenericDAO;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 14
 * Time: 오전 11:18
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
    private Class<T> persistentClass;
    private Session session;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected GenericHibernateDAO() {
        this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        if ( this.session == null || this.session.isOpen() == false) {
            this.session = this.sessionFactory.getCurrentSession();
        }
        return this.session;
    }

    protected Criteria getCriteria(String alias) {
        if ( alias == null || alias.equals("") == true ) {
            return this.getSession().createCriteria(getPersistentClass());
        } else {
            return this.getSession().createCriteria(getPersistentClass(), alias);
        }
    }

    protected Criteria getCriteria() {
        return this.getCriteria(null);
    }

    protected List<T> findByCriteria(Criterion... criterions) {
        Criteria criteria = this.getCriteria();
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    @Override
    public T findById(ID id, boolean lock) {
        T entity;
        if ( lock ) {
            entity = (T)getSession().get(getPersistentClass(), id, LockOptions.UPGRADE);
        } else {
            entity = (T)getSession().get(getPersistentClass(), id);
        }
        return entity;
    }

    @Override
    public T findById(ID id) {
        return this.findById(id, false);
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        Criteria criteria = this.getCriteria();
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        criteria.add(example);
        return criteria.list();
    }

    @Override
    public T save(T entity) {
        this.getSession().save(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        this.getSession().update(entity);
        return entity;
    }

    @Override
    public T saveOrUpdate(T entity) {
        this.getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        this.getSession().delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        T entity = findById(id, true);
        this.getSession().delete( entity );
    }

    @Override
    public void deleteByIds(ID[] ids) {
        for (ID id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void flush() {
        this.getSession().flush();
    }

    @Override
    public void clear() {
        this.getSession().clear();
    }
}
