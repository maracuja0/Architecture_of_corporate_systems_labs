package com.example.lr1.service;
import com.example.lr1.repository.PositionRepository;
import com.example.lr1.entity.Position;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class PositionService {
    @Inject
    private PositionRepository positionRepo;
    public List<Position> getAllPositions() {
        return positionRepo.findAll();
    }
}