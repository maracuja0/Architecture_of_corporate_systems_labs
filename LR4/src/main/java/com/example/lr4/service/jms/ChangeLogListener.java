package com.example.lr4.service.jms;

import com.example.lr4.config.JmsConfig;
import com.example.lr4.repository.ChangeLogRepository;
import com.example.lr4.entity.ChangeEvent;
import com.example.lr4.entity.ChangeLogEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeLogListener {

    @Autowired
    private ChangeLogRepository repository;

    @JmsListener(destination = JmsConfig.CHANGE_TOPIC, containerFactory = "topicListenerFactory")
    @Transactional
    public void onMessage(ChangeEvent changeEvent) {
        ChangeLogEntity log = new ChangeLogEntity();
        log.setEntityType(changeEvent.getEntityType());
        log.setEntityId(changeEvent.getEntityId());
        log.setChangeType(changeEvent.getAction());
        log.setDescription(changeEvent.getData() != null ? changeEvent.getData().toString() : "none");

        repository.save(log);
    }
}