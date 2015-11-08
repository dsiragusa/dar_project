package etu.upmc.fr;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
public class GenericRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> tClass;
    private final String selectFrom;

    public GenericRepository(Class<T> tClass) {
        this.tClass = tClass;
        this.selectFrom = "select t from " + tClass.getName() + " t ";
    }

    @Transactional
    public T save (T t) {
        entityManager.persist(t);

        return t;
    }

    @Transactional
    public T findById(Long id) {
        try {
            return entityManager.createQuery(selectFrom + "where t.id = :id", tClass)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<T> findAll() {
        try {
            return entityManager.createQuery(selectFrom, tClass)
                    .getResultList();

        } catch (PersistenceException e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<T> findBy(Map<String, Object> params) {
        try {
            return generateQuery(params).getResultList();
        } catch (PersistenceException e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public T findOneBy(Map<String, Object> params) {
        try {
            return (T) generateQuery(params).getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<T> findBy(String columnName, Object value) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(columnName, value);

        return findBy(params);
    }

    public T findOneBy(String columnName, Object value) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(columnName, value);

        return findOneBy(params);
    }

    private Query generateQuery(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder(selectFrom + "where ");

        int i = 1;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            setEquality(sb, entry.getKey(), entry.getValue());

            if (i++ < params.size()) {
                sb.append(" and ");
            }
        }

        Query query = entityManager.createQuery(sb.toString(), tClass);

        try {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                setParameter(query, entry.getKey(), entry.getValue());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return query;
    }

    private void setParameter(Query query, String key, Object value) throws NoSuchMethodException {
        String getter = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
        Method m = tClass.getMethod(getter);

        if (value.getClass().isArray()) {
            int i = 0;
            for (Object o : (Object[])value) {
                query.setParameter(key + i++, m.getReturnType().cast(o));
            }
        }
        else {
            query.setParameter(key, m.getReturnType().cast(value));
        }
    }

    private void setEquality(StringBuilder sb, String key, Object value) {
        if (value.getClass().isArray()) {
            int length = ((Object[])value).length;

            for (int i = 0; i < length; i++) {
                sb.append("t.").append(key).append(" = :").append(key).append(i);

                if (i != length - 1) {
                    sb.append(" or ");
                }
            }
        }
        else {
            sb.append("t.").append(key).append(" = :").append(key);
        }
    }

}
