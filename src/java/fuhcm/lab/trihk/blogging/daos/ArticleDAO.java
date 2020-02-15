/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.daos;

import fuhcm.lab.trihk.blogging.dtos.ArticleDTO;
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

    private List<ArticleDTO> listArticles;

    public List<ArticleDTO> getListArticles() {
        return listArticles;
    }

    public void searchByTitle(String searchString) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtility.initConnection();
            if (connection != null) {
                String sql = "select id, title, shortDescription, publishedDate from tblArticles where title LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + searchString + "%");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("shortDescription");
                    Date publishedDate = resultSet.getDate("publishedDate");
                    ArticleDTO dto = new ArticleDTO(id, title, description, publishedDate);
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
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public boolean insert(ArticleDTO article) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sql = "";
                ps = con.prepareStatement(sql);
                ps.setString(1, article.getTitle());
                ps.setString(2, article.getDescription());
                ps.setString(3, article.getContent());
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
