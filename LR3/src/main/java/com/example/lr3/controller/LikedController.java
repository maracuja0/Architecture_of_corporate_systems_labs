package com.example.lr3.controller;

import com.example.lr3.entity.PositionEntity;
import com.example.lr3.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/users/{userId}/liked")
public class LikedController {

    @Autowired
    private LikedService likedService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getFavorites(
            @PathVariable Long userId,
            @RequestHeader(name = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE)
            String acceptHeader
    ) {
        List<PositionEntity> likedPositions = likedService.getLikedPositions(userId);

        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            StringBuilder xml = new StringBuilder();
            xml.append("""
                    <?xml version="1.0" encoding="UTF-8"?>
                    <?xml-stylesheet type="text/xsl" href="/xsl/liked.xsl"?>
                    <likedResponse>
                    """);

            xml.append("<userId>").append(userId).append("</userId>");
            xml.append("<positions>");

            for (PositionEntity p : likedPositions) {
                xml.append("<positionEntity>")
                        .append("<positionId>").append(p.getPositionId()).append("</positionId>")
                        .append("<positionName>").append(p.getPositionName()).append("</positionName>")
                        .append("<positionDesc>").append(p.getPositionDesc()).append("</positionDesc>")
                        .append("<positionType>").append(p.getPositionType()).append("</positionType>")
                        .append("</positionEntity>");
            }

            xml.append("</positions>");
            xml.append("</likedResponse>");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml.toString());
        } else {
            // Возвращаем объект напрямую, Spring сериализует его в JSON
            Map<String, Object> response = new HashMap<>();
            response.put("userId", userId);
            response.put("positions", likedPositions);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        }
    }




    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> likeOrUnlike(
            @PathVariable Long userId,
            @RequestParam Long positionId,
            @RequestParam String action,
            @RequestParam(required = false) String redirect
    ) {

        if ("like".equals(action)) {
            likedService.like(userId, positionId);
        } else if ("unlike".equals(action)) {
            likedService.dislike(userId, positionId);
        }

        if (redirect != null) {
            return ResponseEntity
                    .status(302)
                    .header("Location", redirect)
                    .build();
        }

        return ResponseEntity.ok(
                Map.of("userId", userId, "positionId", positionId, "action", action)
        );
    }
}

