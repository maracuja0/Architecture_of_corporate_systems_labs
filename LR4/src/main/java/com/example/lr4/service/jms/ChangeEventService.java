package com.example.lr4.service.jms;


import com.example.lr4.config.JmsConfig;
import com.example.lr4.entity.ChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChangeEventService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(ChangeEvent changeEvent) {
        jmsTemplate.convertAndSend(JmsConfig.CHANGE_TOPIC, changeEvent);
    }
}