package com.example.lr3.controller;

import com.example.lr3.entity.UserEntity;
import com.example.lr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getAllUsers(
            @RequestHeader(name = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE)
            String acceptHeader
    ) {
        List<UserEntity> users = userService.getAllUsers();

        if (acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {

            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append("<?xml-stylesheet type=\"text/xsl\" href=\"/xsl/users.xsl\"?>\n");
            xml.append("<users>\n");

            for (UserEntity user : users) {
                xml.append("  <user userId=\"").append(user.getUserId()).append("\">\n");
                xml.append("    <userFirstName>").append(user.getUserFirstName()).append("</userFirstName>\n");
                xml.append("    <userLastName>").append(user.getUserLastName()).append("</userLastName>\n");
                xml.append("  </user>\n");
            }

            xml.append("</users>");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml.toString());
        }
        else{
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users);
        }
    }


    @GetMapping(params = "id", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserEntity getUserById(@RequestParam Long id) {
        return userService.getUser(id);
    }
}
