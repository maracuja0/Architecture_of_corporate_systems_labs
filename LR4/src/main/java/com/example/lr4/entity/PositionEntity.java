package com.example.lr4.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "positions")
@JacksonXmlRootElement(localName = "position")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    @JacksonXmlProperty(isAttribute = true)
    private Long positionId;

    @Column(name = "position_name", nullable = false, length = 200)
    private String positionName;

    @Column(name = "position_desc", columnDefinition = "TEXT")
    private String positionDesc;

    @Column(name = "position_type")
    private String positionType;

    @Column(name = "position_date")
    @Temporal(TemporalType.DATE)
    private Date positionDate;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public Date getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }
}
