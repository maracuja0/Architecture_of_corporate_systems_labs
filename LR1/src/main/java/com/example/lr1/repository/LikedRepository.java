package com.example.lr1.repository;
import com.example.lr1.entity.Liked;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;

@Stateless
public class LikedRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Liked> findByUserId(Long userId) {
        return em.createQuery(
                        "SELECT l FROM Liked l WHERE l.user.userId = :userId", Liked.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void save(Liked liked) { em.persist(liked); }
    public void delete(Liked liked) { em.remove(em.contains(liked) ? liked : em.merge(liked)); }

    public Liked findByUserIdAndPositionId(Long userId, Long posId) {
        try {
            return em.createQuery(
                            "SELECT l FROM Liked l WHERE l.user.userId = :userId AND l.position.positionId = :posId", Liked.class)
                    .setParameter("userId", userId)
                    .setParameter("posId", posId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // если лайка нет
        }
    }


}