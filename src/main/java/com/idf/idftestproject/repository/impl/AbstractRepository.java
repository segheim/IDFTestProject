package com.idf.idftestproject.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final Class<T> entityType;

    @PersistenceContext
    protected EntityManager entityManager;

    protected AbstractRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    public List<T> findAll() {
        final TypedQuery<T> query = entityManager.createQuery("select t from " +
                entityType.getSimpleName() + " t", entityType);
        return query.getResultList();
    }
}
