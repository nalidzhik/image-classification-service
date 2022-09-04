package com.vmware.talentboost.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags", schema = "talentboost")
public class Tag {

    public List<ImageTags> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<ImageTags> imageTags) {
        this.imageTags = imageTags;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ImageTags> imageTags;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

}
