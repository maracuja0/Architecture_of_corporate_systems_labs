package com.example.lr2.controller;

import com.example.lr2.entity.PositionEntity;
import com.example.lr2.service.LikedService;
import com.example.lr2.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private LikedService likedService;

    @GetMapping
    public String getPositions(@RequestParam("userId") Long userId, Model model) {
        List<PositionEntity> positions = positionService.getAllPositions();
        List<Long> likedIds = likedService.getLikedPositionIds(userId);

        model.addAttribute("positions", positions);
        model.addAttribute("likedIds", likedIds);
        model.addAttribute("userId", userId);

        return "positions";
    }

    @PostMapping
    public String handleLikeAction(@RequestParam("userId") Long userId,
                                   @RequestParam("positionId") Long positionId,
                                   @RequestParam("action") String action) {

        if ("like".equalsIgnoreCase(action)) {
            likedService.like(userId, positionId);
        } else if ("unlike".equalsIgnoreCase(action)) {
            likedService.dislike(userId, positionId);
        }

        return "redirect:/positions?userId=" + userId;
    }
}
