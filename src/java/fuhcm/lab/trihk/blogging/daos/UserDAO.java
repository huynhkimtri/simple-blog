/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.daos;

import fuhcm.lab.trihk.blogging.dtos.UserDTO;
import fuhcm.lab.trihk.blogging.utilities.ConstantsUtils;
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
            + "VALUE(?, ?, ?, ?, ?, ?)";

    private final String sqlUpdateStatus
            = "UPDATE [dbo].[tblUsers] SET [status] = ? WHERE [email] = ?";

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

    public boolean createUser(UserDTO createdUser) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseUtility.initConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, createdUser.getEmail());
                preparedStatement.setString(2, createdUser.getPassword());
                preparedStatement.setString(3, createdUser.getFirstName());
                preparedStatement.setString(4, createdUser.getLastName());
                preparedStatement.setString(5, ConstantsUtils.USER_ROLE_MEMBER);
                preparedStatement.setString(6, ConstantsUtils.USER_STATUS_NEW);
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
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
