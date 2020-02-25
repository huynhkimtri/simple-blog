/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.dtos;

import java.io.Serializable;

/**
 *
 * @author HuynhKimTri
 */
public class ArticleDTO implements Serializable {

    private int id;
    private String title;
    private String description;
    private String content;
    private String publishedDate;
    private String createdDate;
    private String authorEmail;
    private String status;

    public ArticleDTO() {
    }

    public ArticleDTO(int id, String title, String description, String content, String publishedDate, String createdDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishedDate = publishedDate;
        this.createdDate = createdDate;
        this.status = status;
    }

    public ArticleDTO(int id, String title, String description, String publishedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
    }

    public ArticleDTO(String title, String description, String content, String createdDate, String authorEmail, String status) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.createdDate = createdDate;
        this.authorEmail = authorEmail;
        this.status = status;
    }

    public ArticleDTO(int id, String title, String description, String content, String publishedDate, String createdDate, String authorEmail, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishedDate = publishedDate;
        this.createdDate = createdDate;
        this.authorEmail = authorEmail;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" + "id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + ", publishedDate=" + publishedDate + ", createdDate=" + createdDate + ", status=" + status + '}';
    }

}
