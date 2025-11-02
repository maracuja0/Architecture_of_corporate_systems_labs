package com.example.lr1.repository;
import com.example.lr1.entity.Position;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;


@Stateless
public class PositionRepository {
    @PersistenceContext
    private EntityManager em;

    public Position find(Long id) { return em.find(Position.class, id); }
    public List<Position> findAll() {
        return em.createQuery("SELECT p FROM Position p", Position.class).getResultList();
    }
    public void save(Position position) { em.persist(position); }
    public void delete(Position position) { em.remove(em.contains(position) ? position : em.merge(position)); }
}