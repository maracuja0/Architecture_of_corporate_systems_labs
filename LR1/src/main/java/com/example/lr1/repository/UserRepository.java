package com.example.lr1.repository;

import com.example.lr1.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class UserRepository {
    @PersistenceContext
    private EntityManager em;


    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public User find(Long id) { return em.find(User.class, id); }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void save(User user) { em.persist(user); }

    public void delete(User user) { em.remove(em.contains(user) ? user : em.merge(user)); }
}
