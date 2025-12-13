//package com.example.lr4.controller;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class StartController {
//    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
//    public ResponseEntity<String> homePage() {
//        String html = """
//            <!DOCTYPE html>
//            <html lang="ru">
//            <head>
//                <meta charset="UTF-8">
//                <title>Избранное</title>
//            </head>
//            <body>
//                <h1>Пример работы "Избранное"</h1>
//                <p>6132-010402D Сорокин Д.М. и Буторина П.В.</p>
//                <br/>
//                <a href="/api/users">Старт</a>
//            </body>
//            </html>
//            """;
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.TEXT_HTML)
//                .body(html);
//    }
//}


package com.example.lr4.controller;

import com.example.lr4.entity.ChangeLogEntity;
import com.example.lr4.repository.ChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StartController {

    @Autowired
    private ChangeLogRepository changeLogRepository;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> homePage() {
        List<ChangeLogEntity> logs = changeLogRepository.findAll();

        StringBuilder html = new StringBuilder();
        html.append("""
            <!DOCTYPE html>
            <html lang="ru">
            <head>
                <meta charset="UTF-8">
                <title>Избранное</title>
                <style>
                    table, th, td { border: 1px solid black; border-collapse: collapse; padding: 5px; }
                </style>
            </head>
            <body>
                <h1>Пример работы "Избранное"</h1>
                <p>6132-010402D Сорокин Д.М. и Буторина П.В.</p>
                <br/>
                <a href="/api/users">Старт</a>
                <h2>Логи событий</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Сущность</th>
                        <th>Тип изменения</th>
                        <th>Описание</th>
                    </tr>
            """);

        for (ChangeLogEntity log : logs) {
            html.append("<tr>")
                    .append("<td>").append(log.getId()).append("</td>")
                    .append("<td>").append(log.getEntityType()).append("</td>")
                    .append("<td>").append(log.getChangeType()).append("</td>")
                    .append("<td>").append(log.getDescription()).append("</td>")
                    .append("</tr>");
        }

        html.append("""
                </table>
            </body>
            </html>
            """);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html.toString());
    }
}
