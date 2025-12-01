package com.example.lr3.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> homePage() {
        String html = """
            <!DOCTYPE html>
            <html lang="ru">
            <head>
                <meta charset="UTF-8">
                <title>Избранное</title>
            </head>
            <body>
                <h1>Пример работы "Избранное"</h1>
                <p>6132-010402D Сорокин Д.М. и Буторина П.В.</p>
                <br/>
                <a href="/api/users">Старт</a>
            </body>
            </html>
            """;

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
}
