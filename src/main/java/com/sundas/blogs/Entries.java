package com.sundas.blogs;

import java.sql.*;
import java.util.*;

public class Entries {
    private Connection con = null;
    public static ArrayList<Entries> BlogPosts = new ArrayList<Entries>();
    private String Title;
    private String Name;
    private String Email;
    private String Blog;
    private String intro;
    private String Phone;
    private String City;
    private Statement stmt = null;
    private ResultSet rs = null;
    Scanner no = new Scanner(System.in);

    Entries() {

    }

    Entries(String Name, String Email, String intro, String Phone, String City, String Title, String Blog) {
        this.Name = Name;
        this.Email = Email;
        this.intro = intro;
        this.Phone = Phone;
        this.City = City;
        this.Title = Title;
        this.Blog = Blog;
    }

    public String getTitle() {
        return Title;
    }
    public String getEmail() {
        return Email;
    }
    public String getName() {
        return Name;
    }
    public String getIntro() {
        return intro;
    }
    public String getPhone() {
        return Phone;
    }
    public String getCity() {
        return City;
    }
    public String getBlog() {
        return Blog;
    }

    public void setTitle(String Title) {
        this.Title=Title;
    }
    public void setEmail(String Email){this.Email = Email; }
    public  void setName(String Name) {
        this.Name = Name;
    }
    public void setIntro(String intro) {
        this.intro=intro;
    }
    public  void setPhone(String Phone) {
        this.Phone= Phone;
    }
    public  void setCity(String City) {
        this.City= City;
    }
    public  void setBlog(String Blog) {
        this.Blog = Blog;
    }

    public ArrayList<Entries> all_entries() throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM write_for_us ");
            BlogPosts.clear();
            while (rs.next()) {
                Name = rs.getString(1);
                Email = rs.getString(2);
                intro = rs.getString(3);
                Phone = rs.getString(4);
                City = rs.getString(5);
                Title = rs.getString(6);
                Blog = rs.getString(7);
                BlogPosts.add(new Entries(Name, Email, intro, Phone, City, Title, Blog));
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            con.close();
        }
        return BlogPosts;
    }



    public boolean del_entries(String Title) throws SQLException
    {
        boolean flag = false;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("Delete FROM write_for_us WHERE Title = ?");
            st.setString(1, Title);
            st.executeUpdate();
            flag = true;
        }
        catch (Exception x)
        {
            flag=false;
        }
        return flag;
    }


    public ArrayList  publish_entries(String Title) throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM write_for_us WHERE Title  ='" + Title + "'");
            BlogPosts.clear();
            System.out.println(Title);
            while (rs.next()) {
                Name = rs.getString(1);
                Email = rs.getString(2);
                intro = rs.getString(3);
                Phone = rs.getString(4);
                City = rs.getString(5);
                Title = rs.getString(6);
                Blog = rs.getString(7);
                System.out.println(Name+Email+intro+Phone);
                BlogPosts.add(new Entries(Name, Email, intro, Phone, City, Title, Blog));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally {
            con.close();
        }
        return BlogPosts;
    }

    public boolean write (String Name, String Email, String Intro, String Phone, String City, String Title, String Blog) throws SQLException
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `write_for_us`(`Name`, `Email`, `Intro`, `Phone`, `City`, `Title`, `Blog`) " +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Name);
            st.setString(2,Email);
            st.setString(3,Intro);
            st.setString(4,Phone);
            st.setString(5,City);
            st.setString(6,Title);
            st.setString(7,Blog);
            st.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        finally {
            con.close();
        }
    }

}

