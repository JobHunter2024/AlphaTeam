package com.example.adminservlet.core.provider;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@NamedQuery(name = "ModificationRecord.findAll", query = "SELECT m FROM ModificationRecord m ORDER BY m.date DESC")
@NamedQuery(name = "ModificationRecord.findBySection", query = "SELECT m FROM ModificationRecord m WHERE m.section = :section ORDER BY m.date DESC")
@NamedQuery(name = "ModificationRecord.findByUsername", query = "SELECT m FROM ModificationRecord m WHERE m.username = :username ORDER BY m.date DESC")
@NamedQuery(name = "ModificationRecord.findByDate", query = "SELECT m FROM ModificationRecord m WHERE m.date = :date ORDER BY m.date DESC")
public class ModificationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private String modificationName;


    //Constructor
    public ModificationRecord(String username, Date date, String section, String modificationName) {
        this.username = username;
        this.date = date;
        this.section = section;
        this.modificationName = modificationName;
    }

    public ModificationRecord() {
    }


    //Hash and Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModificationRecord that = (ModificationRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(date, that.date) && Objects.equals(section, that.section) && Objects.equals(modificationName, that.modificationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, date, section, modificationName);
    }


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getModificationName() {
        return modificationName;
    }

    public void setModificationName(String modificationName) {
        this.modificationName = modificationName;
    }
}
