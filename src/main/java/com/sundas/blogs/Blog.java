package com.sundas.blogs;

import java.util.Date;
import java.sql.*;

public class Blog
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
    private int Views;

    public void setBlogID(int BlogID) {
        this.BlogID = BlogID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setAuthorID(int AuthorID) {
        this.AuthorID = AuthorID;
    }

    public void setBlog(String Blog) {
        this.Blog = Blog;
    }

    public void setDisclaimer(String Disclaimer) {
        this.Disclaimer = Disclaimer;
    }

    public void setAdmin(String Admin) {
        this.Admin = Admin;
    }

    public void setPic(byte[] Pic) {
        this.Pic = Pic;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setViews(int Views) {
        this.Views = Views;
    }

    public int getBlogID() {
        return BlogID;
    }

    public int getViews() {
        return Views;
    }

    public String getEncode(){return encode;}

    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getAuthor() {
        return Author;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public String getBlog() {
        return Blog;
    }

    public String getDisclaimer() {
        return Disclaimer;
    }

    public String getAdmin() {
        return Admin;
    }

    public byte[] getPic() {
        return Pic;
    }

    public Date getDate() {
        return date;
    }


    Blog(){}

    Blog(int BlogID,int Views, String Title, String Category, String Author, int AuthorID, String Blog, String Disclaimer, String Admin, String encode, Date date) {
        this.BlogID = BlogID;
        this.Views=Views;
        this.Title = Title;
        this.Category=Category;
        this.Author = Author;
        this.AuthorID = AuthorID;
        this.Blog = Blog;
        this.Disclaimer = Disclaimer;
        this.Admin = Admin;
        this.encode = encode;
        this.date = date;
    }
    Blog(int BlogID, String Title, String Category, String Author, int AuthorID, String Blog, String Disclaimer, String Admin, String encode, byte[] pic) {
        this.BlogID = BlogID;
        this.Title = Title;
        this.Category=Category;
        this.Author = Author;
        this.AuthorID = AuthorID;
        this.Blog = Blog;
        this.Disclaimer = Disclaimer;
        this.Admin = Admin;
        this.encode = encode;
        this.Pic = pic;
    }
    Blog(int BlogID, String Title, String Category, String Author, int AuthorID, String Blog, String Admin) {
        this.BlogID = BlogID;
        this.Title = Title;
        this.Category=Category;
        this.Author = Author;
        this.AuthorID = AuthorID;
        this.Blog = Blog;
        this.Admin = Admin;
    }

}
