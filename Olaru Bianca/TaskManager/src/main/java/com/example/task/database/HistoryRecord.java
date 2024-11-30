package com.example.task.database;

import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "historyrecord")
@NamedQuery(name = "HistoryRecord.findByUUID", query = "SELECT h FROM HistoryRecord h WHERE h.uuid = :uuid")
@NamedQuery(name = "HistoryRecord.findAll", query = "SELECT h FROM HistoryRecord h")
@NamedQuery(name = "HistoryRecord.deleteByUuid", query = "DELETE FROM HistoryRecord h WHERE h.uuid = :uuid")
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String urlString;

    @Column(nullable = false)
    public String path;

    @Column(nullable = false, unique = true)
    public UUID uuid;

    @Column(nullable = false)
    String status;

    public HistoryRecord(String urlString, String path, UUID uuid, String status) {
        this.urlString = urlString;
        this.path = path;
        this.uuid = uuid;
        this.status = status;
    }

    public HistoryRecord() {

    }


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUrlString() {
        return urlString;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
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
}

