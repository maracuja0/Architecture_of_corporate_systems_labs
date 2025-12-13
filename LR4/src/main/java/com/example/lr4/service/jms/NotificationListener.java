package com.example.lr4.service.jms;

import com.example.lr4.config.JmsConfig;
import com.example.lr4.entity.ChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Component
//public class NotificationListener {
//
//    private final EmailService emailService;
//
//    @Autowired
//    public NotificationListener(EmailService emailService) {
//        this.emailService = emailService;
//    }
//
//    @JmsListener(
//            destination = JmsConfig.CHANGE_TOPIC,
//            containerFactory = "topicListenerFactory",
//            subscription = "notificationListenerSub"
//    )
//
//    public void process(ChangeEvent changeEvent) {
//        if (!"User".equals(changeEvent.getEntityType()) || !"INSERT".equals(changeEvent.getAction())) {
//            return;
//        }
//
//        String subject = changeEvent.getAction() + " " + changeEvent.getEntityType() + " #" + changeEvent.getEntityId();
//
//        StringBuilder text = new StringBuilder();
//        text.append("Событие: ").append(changeEvent.getAction()).append("\n");
//        text.append("Сущность: ").append(changeEvent.getEntityType()).append("\n");
//        text.append("ID: ").append(changeEvent.getEntityId()).append("\n");
//
//        if (changeEvent.getData() != null && !changeEvent.getData().isEmpty()) {
//            text.append("\nДанные:\n");
//            changeEvent.getData().forEach((k, v) -> text.append(k).append(": ").append(v).append("\n"));
//        }
//
//        emailService.send(
//                "admin@example.com",
//                subject,
//                text.toString()
//        );
//    }
//}


@Component
public class NotificationListener {

    private final EmailService emailService;

    @Autowired
    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(
            destination = JmsConfig.CHANGE_TOPIC,
            containerFactory = "topicListenerFactory",
            subscription = "notificationListenerSub"
    )
    public void process(ChangeEvent changeEvent) {

        if (!"User".equals(changeEvent.getEntityType())) {
            return;
        }

        if (!"INSERT".equals(changeEvent.getAction())
                && !"DELETE".equals(changeEvent.getAction())) {
            return;
        }

        String subject = changeEvent.getAction()
                + " " + changeEvent.getEntityType()
                + " #" + changeEvent.getEntityId();

        StringBuilder text = new StringBuilder();
        text.append("Событие: ").append(changeEvent.getAction()).append("\n");
        text.append("Сущность: ").append(changeEvent.getEntityType()).append("\n");
        text.append("ID: ").append(changeEvent.getEntityId()).append("\n");

        if (changeEvent.getData() != null && !changeEvent.getData().isEmpty()) {
            text.append("\nДанные:\n");
            changeEvent.getData()
                    .forEach((k, v) ->
                            text.append(k).append(": ").append(v).append("\n")
                    );
        }

        emailService.send(
                "admin@example.com",
                subject,
                text.toString()
        );
    }
}
