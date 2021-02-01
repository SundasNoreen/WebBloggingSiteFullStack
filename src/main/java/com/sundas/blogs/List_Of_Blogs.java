package com.sundas.blogs;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class List_Of_Blogs
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

    public ArrayList category_page(String Category) throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM blogs WHERE Category  ='" + Category + "'ORDER BY Date DESC ");
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
                String encode=Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
        } catch (Exception ex)
        {
        }
        finally
        {
            con.close();
        }
        return BlogPosts;
    }

    public ArrayList Author_Page (int id) throws SQLException
    {
        this.AuthorID=id;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM blogs WHERE AuthorID  ='" + AuthorID + "'ORDER BY Date DESC ");
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
                String encode=Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
        }
        catch (Exception ex)
        {
        }
        finally
        {
            con.close();
        }
        return BlogPosts;
    }

    public ArrayList All_Blogs() throws SQLException
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM blogs ORDER BY Date DESC");
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
                String encode=Base64.getEncoder().encodeToString(Pic);
                BlogPosts.add(new Blog(BlogID, Title, Category, Author, AuthorID, Blog, Disclaimer, Admin, encode, date));
            }
        } catch (Exception ex)
        {
        }
        finally
        {
            con.close();
        }
        return BlogPosts;
    }
}


