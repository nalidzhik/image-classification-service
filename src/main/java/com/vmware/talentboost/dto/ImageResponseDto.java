package com.vmware.talentboost.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class ImageResponseDto {
    private int id;
    private String url;
    private Map<String, Double> tags;

    private LocalDateTime analysedAt;
    private int width;

    private int height;

    public int getId() {
        return id;
    }

    public LocalDateTime getAnalysedAt() {
        return analysedAt;
    }

    public void setAnalysedAt(LocalDateTime analysedAt) {
        this.analysedAt = analysedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Double> getTags() {
        return tags;
    }

    public void setTags(Map<String, Double> tags) {
        this.tags = tags;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
