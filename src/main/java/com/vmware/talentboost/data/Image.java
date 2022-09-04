package com.vmware.talentboost.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "images", schema = "talentboost")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "url")
    private String url;

    @Column(name = "analysed_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime analysedAt;
    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<ImageTags> imageTags;


    public String getUrl() {
        return url;
    }

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

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ImageTags> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<ImageTags> imageTags) {
        this.imageTags = imageTags;
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
