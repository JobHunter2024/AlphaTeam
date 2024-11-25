package com.example.adminservlet.core.provider;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;


@Entity
@NamedQuery(name = "HistoryRecord.findByUUID", query = "SELECT h FROM HistoryRecord h WHERE h.uuid = :uuid")
@NamedQuery(name = "HistoryRecord.findAll", query = "SELECT h FROM HistoryRecord h")
@NamedQuery(name = "HistoryRecord.deleteByUuid", query = "DELETE FROM HistoryRecord h WHERE h.uuid = :uuid")
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "url")
    public String url;

    @ElementCollection
    @CollectionTable(name = "HistoryRecord_Path", joinColumns = @JoinColumn(name = "parentid"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    public Map<String, String> path;

    @Column(nullable = false, unique = true, name = "uuid")
    public UUID uuid;

    @Column(nullable = false, unique = true, name = "status")
    String status;

    public HistoryRecord(String url, Map<String, String> path, UUID uuid, String status) {
        this.url = url;
        this.path = path;
        this.uuid = uuid;
        this.status = status;
    }

    public HistoryRecord() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HistoryRecord)) return false;
        HistoryRecord other = (HistoryRecord) obj;
        return Objects.equals(this.url, other.url) &&
                Objects.equals(this.path, other.path) &&
                Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, path, uuid);
    }

    public Long getId() {
        return id;
    }

    public String getUrlString() {
        return url;
    }

    public Map<String, String> getPath() {
        return path;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setURLString(String url) {
        this.url = url;
    }

    public void setPath(Map<String, String> path) {
        this.path = path;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

