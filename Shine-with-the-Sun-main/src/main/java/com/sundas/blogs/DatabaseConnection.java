package com.sundas.blogs;

import java.sql.*;

public class DatabaseConnection {
    public String Connection (String a, String b) {
        Connection con = null;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return a;
        }
        catch (Exception ex)
        {
            return b;
        }
    }
}
