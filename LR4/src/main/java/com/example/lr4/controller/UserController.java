package com.example.lr4.controller;

import com.example.lr4.entity.UserEntity;
import com.example.lr4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> createUser(
            @RequestParam String userFirstName,
            @RequestParam String userLastName,
            @RequestParam String userPassword,
            @RequestParam(required = false) String userEmail,
            @RequestParam(required = false) String userPhone,
            @RequestParam(required = false) Boolean userGender,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date userBDay
    ) {
        UserEntity user = new UserEntity();
        user.setUserFirstName(userFirstName);
        user.setUserLastName(userLastName);
        user.setUserPassword(userPassword);
        user.setUserEmail(userEmail);
        user.setUserPhone(userPhone);
        user.setUserGender(userGender);
        user.setUserBDay(userBDay);

        userService.addUser(user);

        return ResponseEntity.status(302)
                .header("Location", "/api/users")
                .build();
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
//
//        userService.deleteUser(userId);
//
//        return ResponseEntity.ok(
//                "User with id " + userId + " deleted successfully"
//        );
//    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.status(302)
                .header("Location", "/api/users")
                .build();
    }

}
