package com.example.task.database;

import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.*;


@Entity
@NamedQuery(name = "DataToExtractAdvanced.findByUUID", query = "SELECT d FROM DataToExtractAdvanced d WHERE d.uuid = :uuid")
@NamedQuery(name = "DataToExtractAdvanced.findAll", query = "SELECT d FROM DataToExtractAdvanced d")
@NamedQuery(name = "DataToExtractAdvanced.deleteByUuid", query = "DELETE FROM DataToExtractAdvanced d WHERE d.uuid = :uuid")
public class DataToExtractAdvanced {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String url;

    @Column
    public String jobUrlPath;

    @Column
    public String jobDescriptionPath;

    @Column
    public String jobLocationPath;

    @Column
    public String jobCompanyPath;

    @Column
    public String jobDatePath;

    @Column
    public boolean followLink;

    @Column(nullable = false, unique = true)
    public UUID uuid;

    @Column
    public String jobTitlePath;

    public DataToExtractAdvanced(String url, String jobUrlPath, String jobDescriptionPath, String jobLocationPath, String jobCompanyPath, String jobDatePath, boolean followLink, UUID uuid, String jobTitlePath) {
        this.url = url;
        this.jobUrlPath = jobUrlPath;
        this.jobDescriptionPath = jobDescriptionPath;
        this.jobLocationPath = jobLocationPath;
        this.jobCompanyPath = jobCompanyPath;
        this.jobDatePath = jobDatePath;
        this.followLink = followLink;
        this.uuid = uuid;
        this.jobTitlePath = jobTitlePath;
    }

    public DataToExtractAdvanced() {

    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataToExtractAdvanced that = (DataToExtractAdvanced) o;
        return followLink == that.followLink && Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(jobUrlPath, that.jobUrlPath) && Objects.equals(jobDescriptionPath, that.jobDescriptionPath) && Objects.equals(jobLocationPath, that.jobLocationPath) && Objects.equals(jobCompanyPath, that.jobCompanyPath) && Objects.equals(jobDatePath, that.jobDatePath) && Objects.equals(uuid, that.uuid) && Objects.equals(jobTitlePath, that.jobTitlePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, jobUrlPath, jobDescriptionPath, jobLocationPath, jobCompanyPath, jobDatePath, followLink, uuid, jobTitlePath);
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

    public String getJobUrlPath() {
        return jobUrlPath;
    }

    public void setJobUrlPath(String jobUrlPath) {
        this.jobUrlPath = jobUrlPath;
    }

    public String getJobDescriptionPath() {
        return jobDescriptionPath;
    }

    public void setJobDescriptionPath(String jobDescriptionPath) {
        this.jobDescriptionPath = jobDescriptionPath;
    }

    public String getJobLocationPath() {
        return jobLocationPath;
    }

    public void setJobLocationPath(String jobLocationPath) {
        this.jobLocationPath = jobLocationPath;
    }

    public String getJobCompanyPath() {
        return jobCompanyPath;
    }

    public void setJobCompanyPath(String jobCompanyPath) {
        this.jobCompanyPath = jobCompanyPath;
    }

    public String getJobDatePath() {
        return jobDatePath;
    }

    public void setJobDatePath(String jobDatePath) {
        this.jobDatePath = jobDatePath;
    }

    public boolean getFollowLink() {
        return followLink;
    }

    public void setFollowLink(boolean followLink) {
        this.followLink = followLink;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getJobTitlePath() {
        return jobTitlePath;
    }

    public void setJobTitlePath(String jobTitlePath) {
        this.jobTitlePath = jobTitlePath;
    }
}

