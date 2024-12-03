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
    public String url;

    @Column(nullable = false)
    public String path;

    @Column(nullable = false)
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

    @Override
    public String toString() {
        return "HistoryRecord{" +
                "id=" + id +
                ", urlString='" + url + '\'' +
                ", path='" + path + '\'' +
                ", uuid=" + uuid +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
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
}

