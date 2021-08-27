package com.mycompany.noteappdat.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDAO<T> {
    private final Class<T> entityType;

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public void flush() {
        getEntityManager().flush();
    }

    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public List<T> findAll() {
        final CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}