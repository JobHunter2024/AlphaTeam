package com.example.task.database;

import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.*;


@Entity
@NamedQuery(name = "ResultRecordAdvanced.findByUUID", query = "SELECT r FROM ResultRecordAdvanced r WHERE r.uuid = :uuid")
@NamedQuery(name = "ResultRecordAdvanced.findAll", query = "SELECT r FROM ResultRecordAdvanced r")
@NamedQuery(name = "ResultRecordAdvanced.deleteByUUID", query = "DELETE FROM ResultRecordAdvanced r WHERE r.uuid = :uuid")
public class ResultRecordAdvanced {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String url;

    @Column(nullable = false)
    public UUID uuid;

    @Column
    public String description;

    @Column
    public String location;

    @Column
    public String company;

    @Column
    public String title;

    @Column
    public String date;

    public ResultRecordAdvanced(String url, UUID uuid, String description, String location, String company, String title, String date) {
        this.url = url;
        this.uuid = uuid;
        this.description = description;
        this.location = location;
        this.company = company;
        this.title = title;
        this.date = date;
    }

    public ResultRecordAdvanced(){

    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultRecordAdvanced that = (ResultRecordAdvanced) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(uuid, that.uuid) && Objects.equals(description, that.description) && Objects.equals(location, that.location) && Objects.equals(company, that.company) && Objects.equals(title, that.title) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, uuid, description, location, company, title, date);
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

