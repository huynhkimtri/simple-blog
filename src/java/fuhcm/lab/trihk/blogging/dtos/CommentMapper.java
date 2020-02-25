/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.dtos;

import java.io.Serializable;

/**
 *
 * @author huynhkimtri
 */
public class CommentMapper implements Serializable {

    private int id;
    private String comment;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private int articleId;
    private String date;

    public CommentMapper(int id, String comment, String userEmail, String userFirstName, String userLastName, int articleId, String date) {
        this.id = id;
        this.comment = comment;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.articleId = articleId;
        this.date = date;
    }

    public CommentMapper(String comment, String userEmail, String userFirstName, String userLastName, String date) {
        this.comment = comment;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
