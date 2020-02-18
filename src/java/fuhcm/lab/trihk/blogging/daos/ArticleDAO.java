/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.daos;

import fuhcm.lab.trihk.blogging.dtos.ArticleDTO;
import fuhcm.lab.trihk.blogging.utilities.Constants;
import fuhcm.lab.trihk.blogging.utilities.DatabaseUtility;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huynhkimtri
 */
public class ArticleDAO implements Serializable {

    private final String sqlFindAllByStatus
            = "SELECT [id],[title],[description],[publishedDate],[authorEmail],[status]\n"
            + "FROM [SimpleBlog].[dbo].[tblArticles]\n"
            + "WHERE [status] = ?"
            + "ORDER BY createdDate DESC";
    private final String sqlFindByTitle
            = "";
    private final String sqlInsertArticle
            = "INSERT INTO [dbo].[tblArticles]"
            + "([title],[description],[articleContent],[createdDate],[authorEmail],[status])\n"
            + "VALUES(?,?,?,?,?,?)";

    private List<ArticleDTO> listArticles;

    public List<ArticleDTO> getListArticles() {
        return listArticles;
    }

    public void getAllArticleForMemeber() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                ps = con.prepareStatement(sqlFindAllByStatus);
                ps.setString(1, Constants.STATUS_ARTICLE_NEW);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String date = rs.getString("publishedDate");
                    String author = rs.getString("authorEmail");
                    ArticleDTO dto = new ArticleDTO(id, title, description,
                            null, date, null, author, date);
                    if (listArticles == null) {
                        listArticles = new ArrayList<>();
                    } else {
                        listArticles.add(dto);
                    }
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchByTitle(String searchString) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                ps = con.prepareStatement(sqlFindByTitle);
                ps.setString(1, "%" + searchString + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("shortDescription");
                    Date publishedDate = rs.getDate("publishedDate");
                    ArticleDTO dto = new ArticleDTO(id, title, description, publishedDate.toString());
                    if (listArticles == null) {
                        listArticles = new ArrayList<>();
                    } else {
                        listArticles.add(dto);
                    }
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean insertArticle(ArticleDTO article) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                ps = con.prepareStatement(sqlInsertArticle);
                ps.setString(1, article.getTitle());
                ps.setString(2, article.getDescription());
                ps.setString(3, article.getContent());
                ps.setString(4, article.getCreatedDate());
                ps.setString(5, article.getAuthorEmail());
                ps.setString(6, article.getStatus());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateStatus(String id, String status) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sql = "";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, status);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

}
