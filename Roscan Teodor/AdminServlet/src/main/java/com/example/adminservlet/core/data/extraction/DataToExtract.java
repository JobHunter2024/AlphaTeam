package com.example.adminservlet.core.data.extraction;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;


@Entity
@NamedQuery(name = "DataToExtract.findByUUID", query = "SELECT d FROM DataToExtract d WHERE d.uuid = :uuid")
@NamedQuery(name = "DataToExtract.findAll", query = "SELECT d FROM DataToExtract d")
@NamedQuery(name = "DataToExtract.deleteByUuid", query = "DELETE FROM DataToExtract d WHERE d.uuid = :uuid")
public class DataToExtract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String url;

    @Column(nullable = false)
    public String path;

    @Column(nullable = false, unique = true)
    public UUID uuid;

    public DataToExtract(String url, String path, UUID uuid) {
        this.url = url;
        this.path = path;
        this.uuid = uuid;
    }

    public DataToExtract() {

    }


    //Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataToExtract that = (DataToExtract) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(path, that.path) && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, path, uuid);
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


    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String[] splitPath(){
        return path.split(" > ");
    }
}

