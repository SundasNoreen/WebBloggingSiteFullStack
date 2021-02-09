package com.sundas.blogs;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class Adverisements
{
    protected String link_hor;
    private String link_ver;
    private String AdHor;
    private String AdVer;
    Connection con;
    private byte[] Hor_Pic;
    private byte[] Ver_Pic;

    public byte[] getHor_Pic(){return Hor_Pic;}
    public void setHor_Pic(byte[] Hor_Pic){this.Hor_Pic=Hor_Pic;}

    public String getLink_hor(){return link_hor;}
    public void setLink_hor(String link_hor){this.link_hor=link_hor;}

    public String getAdHor(){return AdHor;}
    public void setAdHor(String AdHor){this.AdHor=AdHor;}

    public String getAdVer(){return AdVer;}
    public void setAdVer(String AdVer){this.AdVer=AdVer;}

    public byte[]  getVer_Pic(){return Ver_Pic;}
    public void setVer_Pic(byte[] Ver_Pic){this.Ver_Pic=Ver_Pic;}

    public String getLink_ver(){return link_ver;}
    public void setLink_ver(String link_ver){this.link_ver=link_ver;}

    Adverisements(){}

    Adverisements(String link,byte[] Pic)
    {
        this.Hor_Pic = Pic;
        this.Ver_Pic = Pic;
        this.link_ver=link;
        this.link_hor=link;
    }
    Adverisements(String link,String encode)
    {
        this.AdHor=encode;
        this.AdVer=encode;
        this.link_ver=link;
        this.link_hor=link;
    }

    ArrayList<Adverisements> Ads = new ArrayList<>();

    public ArrayList Horizontal() throws SQLException
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM horizontal_ad");
            Ads.clear();
            while (rs.next()) {
                link_hor=rs.getString(1);
                Hor_Pic = rs.getBytes(2);
                AdHor=Base64.getEncoder().encodeToString(Hor_Pic);
                Ads.add(new Adverisements(link_hor,AdHor));
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
        return Ads;
    }

    public ArrayList Square() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM square_ad");
            Ads.clear();
            while (rs.next()) {
                link_ver=rs.getString(1);
                Ver_Pic = rs.getBytes(2);
                AdVer=Base64.getEncoder().encodeToString(Ver_Pic);
                Ads.add(new Adverisements(link_ver,AdVer));
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
        return Ads;
    }
    public boolean Add_Hor_Ad (String link,byte[] Pic) throws SQLException
    {
        this.link_hor=link;
        this.Hor_Pic=Pic;
        if (link_hor.equals("")&& Pic.equals(""))
        {
           return false;
        }
        else{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("TRUNCATE TABLE horizontal_ad");
            st.executeUpdate();
            String query = "INSERT INTO `horizontal_ad`(`Link`, `Pic`) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,link_hor);
            stmt.setBytes(2,Pic);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }}
    }
    public boolean Add_Square_Ad (String link,byte[] Pic) throws SQLException
    {
        this.link_ver=link;
        this.Ver_Pic=Pic;
        if (link_ver.equals("") && Ver_Pic.equals("")) {
            return false;
        }
        else{
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("TRUNCATE TABLE square_ad");
            st.executeUpdate();
            String query = "INSERT INTO `square_ad`(`Link`, `Pic`) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,link_ver);
            stmt.setBytes(2,Ver_Pic);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }}
    }
    public boolean Clear_All () throws SQLException
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blogs", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("TRUNCATE TABLE horizontal_ad");
            st.executeUpdate();
            PreparedStatement stmt = con.prepareStatement("TRUNCATE TABLE square_ad");
            stmt.executeUpdate();
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
