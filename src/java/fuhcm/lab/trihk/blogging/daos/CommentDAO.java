/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.daos;

import fuhcm.lab.trihk.blogging.dtos.CommentDTO;
import fuhcm.lab.trihk.blogging.dtos.CommentMapper;
import fuhcm.lab.trihk.blogging.utilities.DatabaseUtility;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huynhkimtri
 */
public class CommentDAO implements Serializable {

    private List<CommentMapper> listComments;

    public List<CommentMapper> getListComments() {
        return listComments;
    }

    public boolean insertComment(CommentDTO comment) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sql = "insert into tblComments (comment, userEmail, articleId, date) values (?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, comment.getComment());
                ps.setString(2, comment.getUserEmail());
                ps.setInt(3, comment.getArticleId());
                ps.setString(4, comment.getDate());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void getCommentsByArticle(int articleId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                String sql = "select c.comment, c.date, c.userEmail, u.firstName, u.lastName from tblComments c, tblUsers u where c.articleId = ? and c.userEmail = u.email order by c.date desc";
                ps = con.prepareStatement(sql);
                ps.setInt(1, articleId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String comment = rs.getString("comment");
                    String userEmail = rs.getString("userEmail");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    Timestamp timestamp = rs.getTimestamp("date");
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm - yyyy/MM/dd");
                    String date = dateFormat.format(timestamp);
                    CommentMapper mapper = new CommentMapper(comment, userEmail, firstName, lastName, date);
                    if (listComments == null) {
                        listComments = new ArrayList<>();
                    }
                    listComments.add(mapper);
                }
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
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
}
