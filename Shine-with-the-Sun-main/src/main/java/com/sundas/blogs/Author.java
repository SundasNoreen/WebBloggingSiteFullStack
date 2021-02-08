package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;

public class Author
{
    private int ID;
    private String Name;
    private String Email;
    private String City;
    private String intro;
    private String Phone;
    ArrayList<Author> AuthorHere = new ArrayList<>();
    Connection con;

    // Getters and Setters
    public void setID(int BlogID) {
        this.ID = ID;
    }
    public void setName(String Name) { this.Name = Name; }
    public void setEmail(String Email) { this.Email = Email; }
    public void setCity(String City) {
        this.City = City;
    }
    public void setintro(String intro) {
        this.intro = intro;
    }
    public void setPhone(String Phone) { this.Phone = Phone; }
    public int getID(){return ID;}
    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getCity(){return City; }
    public String getIntro(){return intro;}
    public String getPhone(){return Phone;}

    Author()
    {

    }
    public Author(int authorID, String name, String email, String city, String intro, String phone)
    {
        this.ID=authorID;
        this.Name=name;
        this.Email= email;
        this.City=city;
        this.intro=intro;
        this.Phone=phone;
    }
    public Author( String name, String email, String city, String intro, String phone)
    {
        this.Name=name;
        this.Email= email;
        this.City=city;
        this.intro=intro;
        this.Phone=phone;
    }

    public ArrayList<Author> Details (int id) throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors WHERE AuthorID  ='" + id + "' ");
            while (rs.next()) {
                int AuthorID = rs.getInt(1);
                String Name = rs.getString(2);
                String Email = rs.getString(3);
                String City = rs.getString(4);
                String Intro = rs.getString(5);
                String Phone = rs.getString(6);
                AuthorHere.add(new Author(AuthorID,Name,Email,City,Intro,Phone));
            }
        }
        catch (Exception ex)
        {
        }
        finally
        {
           con.close();
        }
        return AuthorHere;
    }

    public int check_author(String Name, String Email, String City, String intro, String  Phone) throws SQLException
    {
        String Auth = Email;
        this.Name = Name;
        this.City = City;
        this.intro = intro;
        this.Phone = Phone;
        boolean flag = false;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors ");
            while (rs.next())
            {
                String Em = rs.getString(3);
                if (Auth.equals(Em))
                {
                    flag = true;
                    Name = rs.getString(2);
                    intro = rs.getString(5);
                    ID = rs.getInt(1);
                    break;
                }
            }
            if (!flag)
            {
                String query = "INSERT INTO `authors`( `Name`, `EmailID`, `City`, `Intro`, `PhoneNo`) VALUES ('" + Name + "','" + Auth + "','" + City + "','" + intro + "','" + Phone + "')";
                stmt = con.prepareStatement(query);
                stmt.executeUpdate(query);
                ResultSet cs = stmt.executeQuery("SELECT * FROM authors WHERE EmailID='" + Auth + "'");
                while (cs.next())
                {
                    ID = cs.getInt(1);
                }
            }
            System.out.println(ID);
            return ID;
        }
        catch (Exception ex)
        {
            System.out.println("There seems an error in Author Details. Try Again.");
            ID=1234;
        }
        finally
        {
            con.close();
        }
        return ID;
    }

    public ArrayList Details () throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Authors");
            AuthorHere.clear();
            while (rs.next())
            {
                int AuthorID = rs.getInt(1);
                String Name = rs.getString(2);
                String Email = rs.getString(3);
                String City = rs.getString(4);
                String Intro = rs.getString(5);
                String Phone = rs.getString(6);
                AuthorHere.add(new Author(AuthorID,Name,Email,City,Intro,Phone));
            }
        } catch (Exception ex)
        {
        }
        finally
        {
            con.close();
        }
        return AuthorHere;
    }
}
