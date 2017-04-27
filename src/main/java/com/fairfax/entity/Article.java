package com.fairfax.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class to hold article objects
 * @author dileep
 *
 */
@Entity
public class Article {
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    private String title;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String body;

    @ElementCollection
    @CollectionTable(name ="ARTICLE_TAGS")
    private Set<String> tags;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    
    protected Article() {};

    public Article (String id, String title, Date date, String body, Set<String> tags, Date lastModified) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.body = body;
        this.tags = tags;
        this.lastModified = lastModified;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
    	this.id = id;
    }

    public String getTitle () {
        return this.title;
    }

    public void setTitle (String title) {
    	this.title = title;
    }

    public Date getDate () {
        return this.date;
    }

    public void setDate (Date date) {
    	this.date = date;
    }

    public String getBody () {
        return this.body;
    }

    public void setBody (String body) {
    	this.body = body;
    }
    
    public Set<String> getTags () {
    	return this.tags;
    }
    
    public void setTags (Set<String> tags) {
    	this.tags = tags;
    }
    
    public Date getLastModified () {
    	return this.lastModified;
    }
    
    public void setLastModified (Date lastModified) {
    	this.lastModified = lastModified;
    }
    
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        this.lastModified = new Date();
    }
}