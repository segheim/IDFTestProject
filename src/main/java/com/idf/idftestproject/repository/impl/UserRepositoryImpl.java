package com.idf.idftestproject.repository.impl;

import com.idf.idftestproject.model.Cryptocurrency;
import com.idf.idftestproject.model.User;
import com.idf.idftestproject.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository<User> {

    protected UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        User user;
        try {
            final TypedQuery<User> query = entityManager.createQuery("select u from " +
                    User.class.getSimpleName() + " u where u.name=:userName", User.class);
            user = query.setParameter("userName", userName)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
