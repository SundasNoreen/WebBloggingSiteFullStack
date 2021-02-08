package com.sundas.blogs;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Home_Page
{
    private int BlogID;
    private String Title;
    private String Category;
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
    private ArrayList<Blog> BlogPosts = new ArrayList<Blog>();

    // First Four Blogs.
    public ArrayList First_Four_Blogs() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM blogs ORDER BY Date DESC LIMIT 0,4");
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
                encode=Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Failed to Load Posts.");
        }
        finally
        {
            con.close();
        }
        return BlogPosts;
    }

    // Some Suggested Blogs.
    public static ArrayList Suggested_Blogs() throws SQLException
    {
        ArrayList<Blog> BlogPosts = new ArrayList<>();
        Connection con = null;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM blogs ORDER BY Date ASC LIMIT 0,8");
            BlogPosts.clear();
            while (rs.next()) {
                int BlogID = rs.getInt(1);
                String Title = rs.getString(2);
                String Category = rs.getString(3);
                String Author = rs.getString(4);
                int AuthorID = rs.getInt(5);
                String Blog = rs.getString(6);
                String Disclaimer = rs.getString(7);
                String Admin = rs.getString(8);
                byte[]Pic = rs.getBytes(9);
                Date date = rs.getDate(10);
                String encode=Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
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

    // Some Latest Blogs from each Category.
    public static ArrayList Category_Wise (String Category) throws SQLException
    {
        ArrayList<Blog> BlogPosts = new ArrayList<>();
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM blogs WHERE Category  ='" + Category + "'ORDER BY Date DESC LIMIT 0,4");
            BlogPosts.clear();
            while (rs.next()) {
                int BlogID = rs.getInt(1);
                String Title = rs.getString(2);
                Category = rs.getString(3);
                String Author = rs.getString(4);
                int AuthorID = rs.getInt(5);
                String Blog = rs.getString(6);
                String Disclaimer = rs.getString(7);
                String Admin = rs.getString(8);
                byte[]Pic = rs.getBytes(9);
                Date date = rs.getDate(10);
                String encode=Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
        } catch (Exception ex)
        {
            System.out.println("Failed to Load Posts.");
        }
        return BlogPosts;
    }
}



