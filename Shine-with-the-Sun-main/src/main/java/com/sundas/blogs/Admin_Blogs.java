package com.sundas.blogs;

import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Admin_Blogs
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
    public Blob image;
    private ArrayList<Blog> BlogPosts = new ArrayList<Blog>();

    public boolean Add_Blog (String Title, String Category, String Author, int AuthorID, String Blog, String Admin,byte[] Pic)  throws SQLException
    {
        this.Title=Title;
        this.Category=Category;
        this.Author=Author;
        this.AuthorID=AuthorID;
        this.Blog=Blog;
        this.Admin = Admin;
        this.Pic=Pic;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `blogs`(`Title`, `Category`, `Author`, `AuthorID`, `Blog`, `Admin`, `Pic`) VALUES ('" + Title + "','" + Category + "','" + Author + "','" + AuthorID + "','" + Blog + "','" + Admin + "','" + Pic + "' )";
            Statement stmt = con.prepareStatement(query);
            stmt.execute(query);
            return true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }
    }

    public boolean Edit_Blog (int id,String Title, String Blog ) throws SQLException
    {
        this.BlogID=id;
        this.Title=Title;
        this.Blog=Blog;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `blogs` SET`Title`=?, `Blog`=? WHERE BlogID  ='" + id + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString   (1, Title);
            preparedStmt.setString(2,Blog);
            preparedStmt.executeUpdate();
            con.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally
        {
            con.close();
        }
    }

    public boolean Delete_Blog (int BlogID) throws SQLException
    {
        this.BlogID = BlogID;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("Delete FROM blogs WHERE BlogID = ?");
            st.setInt(1, BlogID);
            st.executeUpdate();
            return true;
        }
        catch (Exception x)
        {
            return false;
        }
        finally
        {
            con.close();
        }
    }

    public boolean Blog (String Title, String Category, String Author, int AuthorID, String Blog, String Admin, MultipartFile image) throws SQLException, IOException {
        this.Title=Title;
        this.Category=Category;
        this.Author=Author;
        this.AuthorID=AuthorID;
        this.Blog=Blog;
        this.Admin = Admin;
//        InputStream picture = image.getInputStream();
//        picture.
        byte[] Picture =image.getBytes();
        Blob blob = new SerialBlob(Picture);
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `blogs`(`Title`, `Category`, `Author`, `AuthorID`, `Blog`, `Admin`, `Pic`) VALUES ('" + Title + "','" + Category + "','" + Author + "','" + AuthorID + "','" + Blog + "','" + Admin + "','" + Picture + "' )";
            Statement stmt = con.prepareStatement(query);
            stmt.execute(query);
            return true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }
    }


}
