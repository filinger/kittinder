package com.technoirarts.kittinder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    private Integer photoId;

    @Column
    private String name;

    @Column
    private String occupation;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> likes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> dislikes = new HashSet<>();

    public User() {
    }

    public User(Long id, Integer photoId, String name, String occupation) {
        this.id = id;
        this.photoId = photoId;
        this.name = name;
        this.occupation = occupation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Set<Long> getLikes() {
        return likes;
    }

    public void setLikes(Set<Long> likes) {
        this.likes = likes;
    }

    public Set<Long> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<Long> dislikes) {
        this.dislikes = dislikes;
    }
}
