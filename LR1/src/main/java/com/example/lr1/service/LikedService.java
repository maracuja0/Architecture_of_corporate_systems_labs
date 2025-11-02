package com.example.lr1.service;
import com.example.lr1.repository.LikedRepository;
import com.example.lr1.repository.UserRepository;
import com.example.lr1.repository.PositionRepository;
import com.example.lr1.entity.Liked;
import com.example.lr1.entity.User;
import com.example.lr1.entity.Position;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LikedService {
    @Inject private LikedRepository likedRepo;
    @Inject private UserRepository userRepo;
    @Inject private PositionRepository positionRepo;

    public void likePosition(Long userId, Long posId) {
        User user = userRepo.find(userId);
        Position position = positionRepo.find(posId);
        Liked like = new Liked();
        like.setUser(user);
        like.setPosition(position);
        like.setLikedTime(new Date());
        likedRepo.save(like);
    }

    public void unlikePosition(Long userId, Long posId) {
        Liked like = likedRepo.findByUserIdAndPositionId(userId, posId);

        if (like != null) {
            likedRepo.delete(like); // удаляем из базы
        }
    }


    public List<Position> getLikedPositions(Long userId) {
        List<Liked> likes = likedRepo.findByUserId(userId);

        // Извлекаем позиции
        return likes.stream()
                .map(Liked::getPosition)
                .collect(Collectors.toList()); // старый способ для всех Java 8+
    }

    public List<Long> getLikedPositionIds(Long userId) {
        List<Liked> likes = likedRepo.findByUserId(userId);

        // Извлекаем ID позиций
        return likes.stream()
                .map(like -> like.getPosition().getPositionId())
                .collect(Collectors.toList());
    }
}