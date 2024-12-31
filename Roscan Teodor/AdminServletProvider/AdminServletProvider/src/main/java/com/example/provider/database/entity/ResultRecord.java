package com.example.provider.database.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Entity
@NamedQuery(name = "ResultRecord.findByUUID", query = "SELECT r FROM ResultRecord r WHERE r.uuid = :uuid")
@NamedQuery(name = "ResultRecord.findAll", query = "SELECT r FROM ResultRecord r")
@NamedQuery(name = "ResultRecord.deleteByUUID", query = "DELETE FROM ResultRecord r WHERE r.uuid = :uuid")
public class ResultRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String url;

    @Column(nullable = false)
    public Date date;

    @Column(nullable = false, unique = true)
    public UUID uuid;

    @Column(nullable = false)
    public String content; //JSON content


    public ResultRecord(String url, Date date, UUID uuid, String content) {
        this.url = url;
        this.date = date;
        this.uuid = uuid;
        this.content = content;
    }

    public ResultRecord() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultRecord that = (ResultRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(date, that.date) && Objects.equals(uuid, that.uuid) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, date, uuid, content);
    }


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getContent() {
        return content;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

