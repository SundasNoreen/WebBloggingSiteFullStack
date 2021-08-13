package com.sundas.blogs;

import java.sql.*;

public class DatabaseConnection {
    public String Connection (String a, String b) {
        Connection con = null;
        try
        {
            String url ="jdbc:mysql://vacation-planner.mysql.database.azure.com:3306/blogs?useSSL=true&requireSSL=false";
            con = DriverManager.getConnection(url, "sundasnoreen@vacation-planner","Sundas1234");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return a;
        }
        catch (Exception ex)
        {
            return b;
        }
    }
}

