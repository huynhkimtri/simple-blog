/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.daos;

import fuhcm.lab.trihk.blogging.dtos.UserDTO;
import fuhcm.lab.trihk.blogging.utilities.Constants;
import fuhcm.lab.trihk.blogging.utilities.DatabaseUtility;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author huynhkimtri
 */
public class UserDAO implements Serializable {

    private final String sqlFindByEmailAndPassword
            = "SELECT [email], [firstName], [lastName], [role], [status] "
            + "FROM [SimpleBlog].[dbo].[tblUsers] "
            + "WHERE [email] = ? AND [password] = ?";

    private final String sqlInsert
            = "INSERT INTO [SimpleBlog].[dbo].[tblUsers]"
            + "([email], [firstName], [lastName], [password], [role], [status]) "
            + "VALUES(?, ?, ?, ?, ?, ?)";

    private final String sqlUpdateStatus
            = "UPDATE [dbo].[tblUsers] SET [status] = ? WHERE [email] = ?";

    private final String sqlFindByEmail
            = "SELECT [email] FROM [SimpleBlog].[dbo].[tblUsers] WHERE [email] = ?";

    public UserDTO checkLogin(String email, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtility.initConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sqlFindByEmailAndPassword);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String lastName = resultSet.getString("lastName");
                    String firstName = resultSet.getString("firstName");
                    String role = resultSet.getString("role");
                    String status = resultSet.getString("status");
                    return new UserDTO(email, "", firstName, lastName, role, status);
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public boolean checkExistedEmail(String email) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                ps = con.prepareStatement(sqlFindByEmail);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
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
        return false;
    }

    public boolean createUser(UserDTO user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DatabaseUtility.initConnection();
            if (con != null) {
                ps = con.prepareStatement(sqlInsert);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getFirstName());
                ps.setString(3, user.getLastName());
                ps.setString(4, user.getPassword());
                ps.setString(5, Constants.USER_ROLE_MEMBER);
                ps.setString(6, Constants.USER_STATUS_NEW);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
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

    public boolean updateUserStatus(String updatedStatus, String email) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtility.initConnection();
            if (connection != null) {
                statement = connection.prepareStatement(sqlUpdateStatus);
                statement.setString(1, updatedStatus);
                statement.setString(2, email);
                int row = statement.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

}
