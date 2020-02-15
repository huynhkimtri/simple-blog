/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.utilities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author TriHK
 */
public class DatabaseUtility implements Serializable {

    public static Connection initConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        Connection connection = null;
        DataSource dataSource = (DataSource) tomcatContext.lookup("SimpleBlog");
        if (dataSource != null) {
            connection = dataSource.getConnection();
        }
        return connection;
    }

}
