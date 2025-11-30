package com.example.lr2.controller;

import com.example.lr2.entity.PositionEntity;
import com.example.lr2.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/liked")
public class LikedController {

    @Autowired
    private LikedService likedService;

    @GetMapping
    public String getFavorites(@RequestParam("userId") Long userId, Model model) {
        List<PositionEntity> likedPositions = likedService.getLikedPositions(userId);

        model.addAttribute("likedPositions", likedPositions);
        model.addAttribute("userId", userId);

        return "liked";
    }

    @PostMapping
    public String unlike(@RequestParam("userId") Long userId,
                         @RequestParam("positionId") Long positionId,
                         Model model) {

        likedService.dislike(userId, positionId);

        // Возвращаем ту же страницу с обновлёнными данными
        List<PositionEntity> likedPositions = likedService.getLikedPositions(userId);
        model.addAttribute("likedPositions", likedPositions);
        model.addAttribute("userId", userId);

        return "liked";
    }
}
