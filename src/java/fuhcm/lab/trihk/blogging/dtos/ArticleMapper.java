/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.dtos;

import java.io.Serializable;

/**
 *
 * @author huynh
 */
public class ArticleMapper implements Serializable {

    private int id;
    private String title;
    private String description;
    private String content;
    private String publishedDate;
    private String createdDate;
    private String authorEmail;
    private String authorFirstName;
    private String authorLastName;
    private String status;

    public ArticleMapper(int id, String title, String description, String content, String publishedDate, String createdDate, String authorEmail, String authorFirstName, String authorLastName, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishedDate = publishedDate;
        this.createdDate = createdDate;
        this.authorEmail = authorEmail;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.status = status;
    }

    public ArticleMapper(int id, String title, String description, String publishedDate, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public ArticleMapper(int id, String title, String description, String createdDate, String authorFirstName, String authorLastName, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ArticleMapper{" + "id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + ", publishedDate=" + publishedDate + ", createdDate=" + createdDate + ", authorEmail=" + authorEmail + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName + ", status=" + status + '}';
    }

}
