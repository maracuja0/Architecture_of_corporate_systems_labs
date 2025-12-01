package com.example.lr3.controller;

import com.example.lr3.entity.PositionEntity;
import com.example.lr3.entity.UserEntity;
import com.example.lr3.service.LikedService;
import com.example.lr3.service.PositionService;
import com.example.lr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private LikedService likedService;

    @Autowired
    private UserService userService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getAllPositions(
            @RequestParam(required = false) Long userId,
            @RequestHeader(name = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE)
            String acceptHeader
    ) {
        List<PositionEntity> positions = positionService.getAllPositions();

        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE) && userId != null) {
            UserEntity user = userService.getUser(userId);
            List<Long> likedIds = likedService.getLikedPositions(userId)
                    .stream()
                    .map(PositionEntity::getPositionId)
                    .toList();

            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append("<?xml-stylesheet type=\"text/xsl\" href=\"/xsl/positions.xsl\"?>\n");
            xml.append("<positions>\n");

            xml.append("<user>\n")
                    .append("<userId>").append(user.getUserId()).append("</userId>\n")
                    .append("<userFirstName>").append(user.getUserFirstName()).append("</userFirstName>\n")
                    .append("<userLastName>").append(user.getUserLastName()).append("</userLastName>\n")
                    .append("</user>\n");

            for (PositionEntity p : positions) {
                xml.append("<position>\n")
                        .append("<positionId>").append(p.getPositionId()).append("</positionId>\n")
                        .append("<positionName>").append(p.getPositionName()).append("</positionName>\n")
                        .append("<positionDesc>").append(p.getPositionDesc()).append("</positionDesc>\n")
                        .append("<positionType>").append(p.getPositionType()).append("</positionType>\n")
                        .append("<liked>").append(likedIds.contains(p.getPositionId())).append("</liked>\n")
                        .append("</position>\n");
            }

            xml.append("</positions>");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml.toString());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(positions);
    }

    @GetMapping(value = "/{positionId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getPositionById(
            @PathVariable Long positionId,
            @RequestHeader(name = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE) String acceptHeader
    ) {
        PositionEntity position = positionService.getPositionById(positionId);

        if (position == null) {
            return ResponseEntity.notFound().build();
        }

        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append("<?xml-stylesheet type=\"text/xsl\" href=\"/xsl/position.xsl\"?>\n");
            xml.append("<position>\n")
                    .append("<positionId>").append(position.getPositionId()).append("</positionId>\n")
                    .append("<positionName>").append(position.getPositionName()).append("</positionName>\n")
                    .append("<positionDesc>").append(position.getPositionDesc()).append("</positionDesc>\n")
                    .append("<positionType>").append(position.getPositionType()).append("</positionType>\n")
                    .append("</position>\n");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml.toString());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(position);
    }
}
