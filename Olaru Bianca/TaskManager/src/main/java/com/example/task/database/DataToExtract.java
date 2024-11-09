package com.example.task.database;

import java.net.URL;
import java.util.Dictionary;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;


@Entity
@NamedQuery(name = "DataToExtract.findByUUID", query = "SELECT d FROM DataToExtract d WHERE d.uuid = :uuid")
public class DataToExtract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public URL url;

    @ElementCollection
    @CollectionTable(name = "Path", joinColumns = @JoinColumn(name = "parentid"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    public Map<String, String> path;

    @Column(nullable = false, unique = true)
    public UUID uuid;

    public DataToExtract(URL url, Map<String, String> path, UUID uuid) {
        this.url = url;
        this.path = path;
        this.uuid = uuid;
    }

    public DataToExtract() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DataToExtract)) return false;
        DataToExtract other = (DataToExtract) obj;
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

    public URL getUrl() {
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

    public void setURL(URL url) {
        this.url = url;
    }

    public void setPath(Map<String, String> path) {
        this.path = path;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

