package com.sundas.blogs;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Complete_Blog
{
    private int BlogID;
    private String Title;
    static String Category;
    private String Author;
    private int AuthorID;
    private String Blog;
    private String Disclaimer;
    private String Admin;
    private byte[] Pic;
    private String encode;
    private Date date;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private int Views;
    private ArrayList<Blog> BlogPosts = new ArrayList<Blog>();

    public ArrayList Full_Blog(int id) throws SQLException
    {
        this.BlogID=id;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM blogs WHERE BlogID  ='" + BlogID + "'");
            BlogPosts.clear();
            while (rs.next()) {
                BlogID = rs.getInt(1);
                Title = rs.getString(2);
                Category = rs.getString(3);
                Author = rs.getString(4);
                AuthorID = rs.getInt(5);
                Blog = rs.getString(6);
                Disclaimer = rs.getString(7);
                Admin = rs.getString(8);
                Pic = rs.getBytes(9);
                date = rs.getDate(10);
                int V = rs.getInt(11);
                Views = V+1;
                encode = Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID,Views, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
            String query = "UPDATE `blogs` SET`Views`=? WHERE BlogID  ='" + id + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Views);
            preparedStmt.executeUpdate();
        }
        catch (Exception ex)
        {
            System.out.println("Failed to Load Posts.");
        }
        finally {
            con.close();
        }
        return BlogPosts;
    }
}
