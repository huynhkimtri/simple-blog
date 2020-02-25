/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.daos;

import fuhcm.lab.trihk.blogging.dtos.ArticleDTO;
import fuhcm.lab.trihk.blogging.dtos.ArticleMapper;
import fuhcm.lab.trihk.blogging.utilities.Constants;
import fuhcm.lab.trihk.blogging.utilities.DatabaseUtility;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private final String sqlInsertArticle
            = "INSERT INTO [dbo].[tblArticles]"
            + "([title],[description],[articleContent],[createdDate],[authorEmail],[status])\n"
            + "VALUES(?,?,?,?,?,?)";

    private List<ArticleDTO> listArticles;

    public List<ArticleDTO> getListArticles() {
        return listArticles;
    }

    private List<ArticleMapper> listArticlesMapper;

    public List<ArticleMapper> getListArticlesMapper() {
        return listArticlesMapper;
    }

    public int getRowsCount(String searchValue, String statusValue) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sql = "select count(id) as countNum from tblArticles where title like ? ";
                if (statusValue.length() == 0) {
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + searchValue + "%");
                } else {
                    sql += "and status = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + searchValue + "%");
                    ps.setString(2, statusValue);
                }
                rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("countNum");
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public void getListArticleAndAuthorByStatus(String status) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sqlFindByStatus
                        = "select a.id, a.title, a.description, a.articleContent, a.publishedDate, u.firstName, u.lastName\n"
                        + "from tblUsers u, tblArticles a\n"
                        + "where u.email = a.authorEmail and a.status = ?\n"
                        + "order by a.publishedDate desc";
                ps = con.prepareStatement(sqlFindByStatus);
                ps.setString(1, Constants.STATUS_ARTICLE_ACTIVE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Timestamp dateTime = rs.getTimestamp("publishedDate");
                    if (dateTime != null) {
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm - MMMM dd, yyyy");
                        String strDate = dateFormat.format(dateTime);
                        String authorFirstName = rs.getString("firstName");
                        String authorLastName = rs.getString("lastName");
                        ArticleMapper mapper
                                = new ArticleMapper(id, title, description, strDate, authorFirstName, authorLastName);
                        if (listArticlesMapper == null) {
                            listArticlesMapper = new ArrayList<>();
                        }
                        listArticlesMapper.add(mapper);
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

    public void getListArticleAndAuthorByTitleAndStatus(String searchString, String searchStatus) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sqlFindByTitleAndStatus
                        = "select a.id, a.title, a.description, a.publishedDate, a.createdDate, a.status, u.firstName, u.lastName\n"
                        + "from tblUsers u, tblArticles a\n"
                        + "where a.title like ? and u.email = a.authorEmail ";

                if (searchStatus.length() == 0) {
                    sqlFindByTitleAndStatus += "order by a.createdDate desc";
                    ps = con.prepareStatement(sqlFindByTitleAndStatus);
                    ps.setString(1, "%" + searchString + "%");
                } else {
                    sqlFindByTitleAndStatus += "and a.status = ? order by a.createdDate desc";
                    ps = con.prepareStatement(sqlFindByTitleAndStatus);
                    ps.setString(1, "%" + searchString + "%");
                    ps.setString(2, searchStatus);
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String authorFirstName = rs.getString("firstName");
                    String authorLastName = rs.getString("lastName");
                    String status = rs.getString("status");
                    Timestamp pDateTime = rs.getTimestamp("publishedDate");
                    Timestamp cDateTime = rs.getTimestamp("createdDate");
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm - yyyy/MM/dd");
                    String pDate = "";
                    String cDate = "";
                    if (pDateTime != null) {
                        pDate = dateFormat.format(pDateTime);
                    }
                    if (cDateTime != null) {
                        cDate = dateFormat.format(cDateTime);
                    }
                    ArticleMapper mapper = new ArticleMapper(id, title, description, "", pDate, cDate, "", authorFirstName, authorLastName, status);
                    if (listArticlesMapper == null) {
                        listArticlesMapper = new ArrayList<>();
                    }
                    listArticlesMapper.add(mapper);
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

    public void getListArticleAndAuthorPagingByTitleAndStatus(String searchString, String searchStatus, int start, int stop) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String newSql = "select * from (select row_number() over (order by createdDate desc)\n"
                        + "as RowIndex, a.id, a.title, a.description, a.articleContent, a.publishedDate, a.createdDate, a.status, u.firstName, u.lastName \n"
                        + "from tblArticles a, tblUsers u where a.authorEmail = u.email ";

                if (searchStatus.length() == 0) {
                    newSql += "and a.title like ?) as Sub\n"
                            + "where Sub.RowIndex >= ? and Sub.RowIndex <= ?";
                    ps = con.prepareStatement(newSql);
                    ps.setString(1, "%" + searchString + "%");
                    ps.setInt(2, start);
                    ps.setInt(3, stop);
                } else {
                    newSql += "and a.title like ? and a.status = ?) as Sub\n"
                            + "where Sub.RowIndex >= ? and Sub.RowIndex <= ?";
                    ps = con.prepareStatement(newSql);
                    ps.setString(1, "%" + searchString + "%");
                    ps.setString(2, searchStatus);
                    ps.setInt(3, start);
                    ps.setInt(4, stop);
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String authorFirstName = rs.getString("firstName");
                    String authorLastName = rs.getString("lastName");
                    String status = rs.getString("status");
                    Timestamp pDateTime = rs.getTimestamp("publishedDate");
                    Timestamp cDateTime = rs.getTimestamp("createdDate");
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm - yyyy/MM/dd");
                    String pDate = "";
                    String cDate = "";
                    if (pDateTime != null) {
                        pDate = dateFormat.format(pDateTime);
                    }
                    if (cDateTime != null) {
                        cDate = dateFormat.format(cDateTime);
                    }
                    ArticleMapper mapper = new ArticleMapper(id, title, description, "", pDate, cDate, "", authorFirstName, authorLastName, status);
                    if (listArticlesMapper == null) {
                        listArticlesMapper = new ArrayList<>();
                    }
                    listArticlesMapper.add(mapper);
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

    public void getListArticleAndAuthorForAdmin() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sqlFindByTitleAndStatus
                        = "select a.id, a.title, a.description, a.createdDate, a.status, u.firstName, u.lastName\n"
                        + "from tblUsers u, tblArticles a\n"
                        + "where u.email = a.authorEmail\n"
                        + "order by a.createdDate desc";
                ps = con.prepareStatement(sqlFindByTitleAndStatus);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Timestamp dateTime = rs.getTimestamp("createdDate");
                    String authorFirstName = rs.getString("firstName");
                    String authorLastName = rs.getString("lastName");
                    String status = rs.getString("status");
                    if (dateTime != null) {
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm - yyyy/MM/dd");
                        String createdDate = dateFormat.format(dateTime);

                        ArticleMapper mapper = new ArticleMapper(id, title, description, createdDate, authorFirstName, authorLastName, status);
                        if (listArticlesMapper == null) {
                            listArticlesMapper = new ArrayList<>();
                        }
                        listArticlesMapper.add(mapper);
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

    public boolean updateStatus(int id, String status) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sql = "update tblArticles set status = ? where id = ?;";
                if (status.equals("active")) {
                    sql = "update tblArticles set status = ?, publishedDate = ? where id = ?;";
                    ps = con.prepareStatement(sql);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String publishedDate = formatter.format(now);
                    ps.setString(1, status);
                    ps.setString(2, publishedDate);
                    ps.setInt(3, id);
                } else {
                    ps = con.prepareStatement(sql);
                    ps.setString(1, status);
                    ps.setInt(2, id);
                }
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

    public ArticleMapper findById(int articleId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sqlFindById
                        = "SELECT A.[id],A.[title],A.[description],A.[articleContent],A.[publishedDate],A.[createdDate],A.[authorEmail],A.[status],B.[firstName],B.[lastName]\n"
                        + "FROM [SimpleBlog].[dbo].[tblArticles] A, [SimpleBlog].[dbo].[tblUsers] B\n"
                        + "WHERE A.[id] = ? AND A.[authorEmail] = B.[email]";
                ps = con.prepareStatement(sqlFindById);
                ps.setInt(1, articleId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String des = rs.getString("description");
                    String content = rs.getString("articleContent");
                    String auEmail = rs.getString("authorEmail");
                    String auFirstName = rs.getString("firstName");
                    String auLastName = rs.getString("lastName");
                    String status = rs.getString("status");
                    Timestamp pDateTime = rs.getTimestamp("publishedDate");
                    Timestamp cDateTime = rs.getTimestamp("createdDate");
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm - MMMM dd, yyyy");
                    String pDate = "";
                    String cDate = "";
                    if (pDateTime != null) {
                        pDate = dateFormat.format(pDateTime);
                    }
                    if (cDateTime != null) {
                        cDate = dateFormat.format(cDateTime);
                    }
                    return new ArticleMapper(id, title, des, content, pDate, cDate, auEmail, auFirstName, auLastName, status);
                }
            }
        } catch (SQLException | NamingException ex) {
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
        return null;
    }
}
