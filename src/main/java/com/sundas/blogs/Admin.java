package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;

public class Admin
{
    Connection con;
    private String LoginID;
    private String Name;
    private String EmailID;
    private String PassWord;
    // Getters and Setters.
    public String getLoginID() {
        return LoginID;
    }
    public void setLoginID(String LoginID) {
        this.LoginID = LoginID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getEmailID() {
        return EmailID;
    }
    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }
    public String getPassWord() {
        return PassWord;
    }
    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    Admin()
    {

    }

    Admin(String LoginID, String Name, String EmailID, String Password)
    {
        this.LoginID = LoginID;
        this.Name = Name;
        this.EmailID = EmailID;
        this.PassWord = Password;
    }

    public boolean login (String UserName, String Password) throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM editors ");
            while (rs.next())
            {
                String ID = rs.getString(3);
                String PWS = rs.getString(4);
                if (ID.equals(UserName) && PWS.equals(Password))
                {
                    return true;
                }
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        finally
        {
            con.close();
        }
        return false;
    }


    public boolean new_admin (String Name, String EmailID, String LoginID, String Password) throws SQLException
    {
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `editors`(`Name`, `Email ID`, `Login`, `Password`) VALUES ('" + Name + "','" + EmailID + "','" + LoginID + "','" + Password + "')";
            Statement stmt = con.prepareStatement(query);
            stmt.execute(query);
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

    public boolean Delete_Admin (String LoginID) throws SQLException
    {
        this.LoginID = LoginID;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("Delete FROM editors WHERE Login = ?");
            st.setString(1, LoginID);
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

    public String NewPWS;
    public String getNewPWS(){return NewPWS;}
    public void setNewPWS(String NewPWS){this.NewPWS=NewPWS;}

    public boolean change_password (String LoginID , String Old, String New) throws SQLException
    {
        this.LoginID = LoginID;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `editors` SET `Password`=? WHERE `Login`  =?  AND `Password`=? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, New);
            preparedStmt.setString(2,LoginID);
            preparedStmt.setString(3,Old);
            preparedStmt.executeUpdate();
            return true;
        }
        catch (Exception x)
        {
            System.out.println(x.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }
    }

    public ArrayList view () throws SQLException
    {
        ArrayList<Admin> Admins = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM editors ");
            while (rs.next()) {
                Name = rs.getString(1);
                EmailID = rs.getString(2);
                LoginID=rs.getString(3);
                PassWord=rs.getString(4);
                Admins.add(new Admin(LoginID,Name,EmailID,PassWord));
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            con.close();
        }
        return Admins;
    }

    public ArrayList Profile (String UserName) throws SQLException
    {
        this.LoginID=UserName;
        ArrayList <Admin> Profile = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM editors WHERE Login  = '" + LoginID + "'");
            while (rs.next())
            {
                Name = rs.getString(1);
                EmailID = rs.getString(2);
                LoginID=rs.getString(3);
                PassWord=rs.getString(4);
                Profile.add(new Admin(LoginID,Name,EmailID,PassWord));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            con.close();
        }
        return Profile;
    }

}
