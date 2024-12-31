package com.example.provider.database.entity;

import jakarta.persistence.*;
import java.util.UUID;


@Entity
@NamedQuery(name = "HistoryRecord.findByUUID", query = "SELECT h FROM HistoryRecord h WHERE h.uuid = :uuid")
@NamedQuery(name = "HistoryRecord.findAll", query = "SELECT h FROM HistoryRecord h")
@NamedQuery(name = "HistoryRecord.deleteByUuid", query = "DELETE FROM HistoryRecord h WHERE h.uuid = :uuid")
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String url;

    @Column(nullable = false)
    public String path;

    @Column(nullable = false, unique = true)
    public UUID uuid;

    @Column(nullable = false)
    String status;

    @Column(nullable = false)
    String error;

    public HistoryRecord(String url, String path, UUID uuid, String status, String error) {
        this.url = url;
        this.path = path;
        this.uuid = uuid;
        this.status = status;
        this.error = error;
    }

    public HistoryRecord() {

    }


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String urlString) {
        this.url = urlString;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }
}

