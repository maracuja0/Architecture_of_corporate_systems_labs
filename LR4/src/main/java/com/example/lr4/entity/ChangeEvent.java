package com.example.lr4.entity;

import java.io.Serializable;
import java.util.Map;


public class ChangeEvent implements Serializable {
    private String entityType;
    private Long entityId;
    private String action;
    private Map<String, Object> data;

    public ChangeEvent() {}

    private ChangeEvent(EventBuilder builder) {
        this.entityType = builder.entityType;
        this.entityId = builder.entityId;
        this.action = builder.action;
        this.data = builder.data;
    }

    // Геттеры
    public String getEntityType() { return entityType; }
    public Long getEntityId() { return entityId; }
    public String getAction() { return action; }
    public Map<String, Object> getData() { return data; }

    // Статический метод builder()
    public static EventBuilder builder() {
        return new EventBuilder();
    }

    // Внутренний класс Builder
    public static class EventBuilder {
        private String entityType;
        private Long entityId;
        private String action;
        private Map<String, Object> data;

        public EventBuilder entityType(String entityType) {
            this.entityType = entityType;
            return this;
        }

        public EventBuilder entityId(Long entityId) {
            this.entityId = entityId;
            return this;
        }

        public EventBuilder action(String action) {
            this.action = action;
            return this;
        }

        public EventBuilder data(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public ChangeEvent build() {
            return new ChangeEvent(this);
        }
    }
}
