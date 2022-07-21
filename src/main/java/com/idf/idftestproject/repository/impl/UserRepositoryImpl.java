package com.idf.idftestproject.repository.impl;

import com.idf.idftestproject.exception.RepositoryException;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findByUserName(String userName) {
        try {
            final TypedQuery<User> query = entityManager.createQuery("select u from " +
                    User.class.getSimpleName() + " u where u.name=:userName", User.class);
            return query.setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            String message = "Could not find User with name" + userName;
            logger.warn(message, e);
            throw new RepositoryException(message, e);
        }
    }
}
