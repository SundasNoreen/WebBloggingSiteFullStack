// Web Blogging Site by Sundas Noreen

package com.sundas.blogs;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.beans.BeanProperty;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Blob;
import java.lang.*;

@Controller
public class Home_Controller
{
    DatabaseConnection Data = new DatabaseConnection();
    String username;
    String Page_To_Open;
    String Error_Page="BlogSite/Error.html";
    int flag = 1;
    //************************************ ADMIN DASHBOARD ************************************//

    // Getting Login Page.
    @GetMapping ("/login")
    public String Login ()
    {
        flag=1;
        Page_To_Open="BlogSite/SignIn.html";
        return Data.Connection(Page_To_Open,Error_Page);
    }

    // Authenticating Login and Passwords.
    @PostMapping ("/login")
    public String UserLogin (Model model, @ModelAttribute Admin MyObj) throws SQLException
    {
        String UserName = MyObj.getLoginID();
        String Password = MyObj.getPassWord();
        username=UserName;
        Admin Login = new Admin();
        if (Login.login(UserName,Password))
        {
            if (username.equals("alirehman@admin"))
            {
                flag=2;
            }
            else
            {
                flag=3;
            }
            username=username;
            return Data.Connection("Admin/Welcome.html",Error_Page);
        }
        else
        {
            model.addAttribute("error","Invalid Credentials.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // View All Blogs in a Sequence.
    @RequestMapping ("/view_all")
    public String All (Model model) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open = "Admin/List.html";
        List_Of_Blogs BlogsAll = new List_Of_Blogs();
        ArrayList<Blog> all = BlogsAll.All_Blogs();
        model.addAttribute("all",all);
        return Data.Connection(Page_To_Open,Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // View List of Categories to see Blogs.
    @RequestMapping ("/Admin_Category")
    public String Admin_Category (Model model)
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Category.html";
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Blogs by Categories.
    @RequestMapping ("/blog_national")
    public String National (Model model) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/List.html";
            List_Of_Blogs MyBlog2 = new List_Of_Blogs();
            ArrayList<Blog> all = MyBlog2.category_page("National");
            model.addAttribute("all", all);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_global")
    public String Global (Model model) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/List.html";
            List_Of_Blogs MyBlog2 = new List_Of_Blogs();
            ArrayList<Blog> all = MyBlog2.category_page("Global");
            model.addAttribute("all", all);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_education")
    public String Education (Model model) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/List.html";
            List_Of_Blogs MyBlog2 = new List_Of_Blogs();
            ArrayList<Blog> all = MyBlog2.category_page("Education");
            model.addAttribute("all", all);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_technology")
    public String Technology (Model model) throws SQLException
    {
        if (flag!=1) {
            Page_To_Open = "Admin/List.html";
            List_Of_Blogs MyBlog2 = new List_Of_Blogs();
            ArrayList<Blog> all = MyBlog2.category_page("Technology");
            model.addAttribute("all", all);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_viewpoint")
    public String ViewPoint (Model model) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open="Admin/List.html";
        List_Of_Blogs MyBlog2 = new List_Of_Blogs();
        ArrayList<Blog> all = MyBlog2.category_page("View Point");
        model.addAttribute("all",all);
        return Data.Connection(Page_To_Open,Error_Page);}
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_medialogs")
    public String MediaLogs (Model model) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open="Admin/List.html";
        List_Of_Blogs MyBlog2 = new List_Of_Blogs();
        ArrayList<Blog> all = MyBlog2.category_page("Media Logs");
        model.addAttribute("all",all);
        return Data.Connection(Page_To_Open,Error_Page);}
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_lifestyle")
    public String LifeStyle (Model model) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open="Admin/List.html";
        List_Of_Blogs MyBlog2 = new List_Of_Blogs();
        ArrayList<Blog> all = MyBlog2.category_page("Life Style");
        model.addAttribute("all",all);
        return Data.Connection(Page_To_Open,Error_Page);}
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @RequestMapping ("/blog_health")
    public String Health (Model model) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open="Admin/List.html";
        List_Of_Blogs MyBlog2 = new List_Of_Blogs();
        ArrayList<Blog> all = MyBlog2.category_page("Health");
        model.addAttribute("all",all);
        return Data.Connection(Page_To_Open,Error_Page);}
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Read the Complete Blog.
    @RequestMapping("/read_{id}")
    public String Read_Blog (Model model , @PathVariable("id") int id ) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open="Admin/FullBlog.html";
        // Complete Blog.
        Complete_Blog MyRead = new Complete_Blog();
        ArrayList<Blog> a = MyRead.Full_Blog(id);
        model.addAttribute("a",a);
        return Data.Connection(Page_To_Open,Error_Page);}
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Delete a Blog.
    @RequestMapping("/delete_{id}")
    public String Delete_Blog (Model model , @PathVariable("id") int id ) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/DeleteBlog.html";
            // Fetch Blog Details.
            Complete_Blog MyBlog = new Complete_Blog();
            ArrayList<Blog> a = MyBlog.Full_Blog(id);
            model.addAttribute("a", a);
            // Deleting Blog.
            String error;
            Admin_Blogs abc = new Admin_Blogs();
            boolean flag = abc.Delete_Blog(id);
            if (flag) {
                error = "Blog Deleted Successfully";
            } else {
                error = "There was an Error in Processing Your Request";
            }
            model.addAttribute("error", error);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // View All Entries.
    @RequestMapping ("/view_entries")
    public String Entries (Model model) throws SQLException
    {
        if (flag!=1)
        {
        Page_To_Open="Admin/Entries.html";
        Entries ent = new Entries();
        ArrayList<Entries> entries = ent.all_entries();
        model.addAttribute("entries",entries);
        return Data.Connection(Page_To_Open,Error_Page);}
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Clear a Particular Entry.
    @RequestMapping ("/clear_{id}")
    public String Entries (Model model,@PathVariable("id") String id) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Entries.html";
            Entries NOW = new Entries();
            NOW.del_entries(id);
            Entries ent = new Entries();
            ArrayList<Entries> entries = ent.all_entries();
            model.addAttribute("entries", entries);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Get Add Blog from Entry Page.
    @GetMapping ("/publish_{id}")
    public String Add_From_Entry (Model model,@PathVariable("id") String id) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Publish.html";
            Entries FULL = new Entries();
            ArrayList <Blog> here = FULL.publish_entries(id);
            model.addAttribute("here", here);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Manually Add a Blog.
    @PostMapping ("/publish_{id}")
    public String Add_Entries (Model model, @ModelAttribute Author MyObj, @ModelAttribute Blog blog) throws IOException,SQLException
    {
        if (flag!=1) {
            String Name = MyObj.getName();
            String Email = MyObj.getEmail();
            String Phone = MyObj.getPhone();
            String City = MyObj.getCity();
            String Intro = MyObj.getIntro();
            String Title = blog.getTitle();
            String Category = blog.getCategory();
            String Blog = blog.getBlog();
            int ID = MyObj.check_author(Name, Email, City, Intro, Phone);
            Admin_Blogs here = new Admin_Blogs();
            boolean Flag = here.Add_Blog(Title, Category, Name, ID, Blog, username, null);
            if (Flag) {
                model.addAttribute("msg", "Blog Added Successfully");
            } else {
                model.addAttribute("msg", "There was an Error in adding Blog.");
            }
            return Data.Connection("Admin/Publish.html", Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Get Add Blog Page.
    @GetMapping ("/add_blog")
    public String Add (Model model)
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Add.html";
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Manually Add a Blog.
    @PostMapping("/add_blog")
    public String AddBlog(Model model, @ModelAttribute Author Auth , @ModelAttribute Blog blog,
                          @RequestParam("Picture") MultipartFile Picture) throws SQLException, IOException {
        String Name = Auth.getName();
        String Email = Auth.getEmail();
        String City = Auth.getCity();
        String intro = Auth.getIntro();
        String Phone = Auth.getPhone();
        String Title = blog.getTitle();
        String Category = blog.getCategory();
        String Blog = blog.getBlog();
        String Admin = username;
        int id = Auth.check_author(Name,Email,City,intro,Phone);
        Admin_Blogs Admin1 = new Admin_Blogs();
//        for (MultipartFile Picture : Bean.get) {
//            byte[] bytes = Picture.getBytes();
//            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//        }
//        Picture=blog.getImgFile();//file from model attribute
//        Blob blob=Hibernate.createBlob(savedFile.getInputStream());
//        System.out.println(Picture);
//        InputStream image = Picture.getInputStream();
//        System.out.println(image);
        boolean Flag = Admin1.Blog(Title,Category,Name,id,Blog,Admin,Picture);
        if (Flag)
        {
            model.addAttribute("msg","Blog Added Successfully");
        }
        else
        {
            model.addAttribute("msg","There was an Error in adding Blog.");
        }
        return Data.Connection("Admin/Add.html",Error_Page);
    }

    // Get Editing from Entry Page.
    @GetMapping ("/edit_{id}")
    public String Edit (Model model,@PathVariable("id") int id) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Edit.html";
            Complete_Blog FULL = new Complete_Blog();
            ArrayList<Blog> here = FULL.Full_Blog(id);
            model.addAttribute("here", here);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Manually Edit a Blog.
    @PostMapping ("/edit_{id}")
    public String EditBlog (Model model,@PathVariable("id")int id, @ModelAttribute Blog blog) throws SQLException
    {
        if (flag!=1)
        {
            String Title = blog.getTitle();
            String Blog = blog.getBlog();
            Admin_Blogs EDIT = new Admin_Blogs();
            boolean Flag = EDIT.Edit_Blog(id, Title, Blog);
            String Msg;
            if (Flag) {
                Msg = "Blog was Edited Successfully.";
            } else {
                Msg = "Sorry! There was an error in Editing.";
            }
            model.addAttribute("msg", Msg);
            return Data.Connection("Admin/Edit.html", Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // View All Authors.
    @RequestMapping ("/view_authors")
    public String Authors (Model model) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Authors.html";
            Author ent = new Author();
            ArrayList<Author> entries = ent.Details();
            model.addAttribute("entries", entries);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // View All Admins.
    @RequestMapping ("/view_admins")
    public String Admins(Model model) throws SQLException
    {
        if (flag==2)
        {
            Page_To_Open = "Admin/Admins.html";
            Admin ent = new Admin();
            ArrayList<Author> entries = ent.view();
            model.addAttribute("entries", entries);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            return Data.Connection("Admin/Access.html",Error_Page);
        }
    }

    // Get Add Admin Page.
    @GetMapping ("/add_admins")
    public String AddAdmins () throws SQLException
    {
        if (flag==2)
        {
            Page_To_Open = "Admin/AddAdmins.html";
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            return Data.Connection("Admin/Access.html",Error_Page);
        }
    }

    // Get Add Admin Page.
    @GetMapping ("/remove_{id}")
    public String RemoveAdmins (Model model,@PathVariable("id") String id) throws SQLException
    {
        if (flag==2)
        {
            Page_To_Open = "Admin/AdminDeleted.html";
            Admin obj = new Admin();
            String error;
            boolean Flag = obj.Delete_Admin(id);
            if (Flag)
            {
                error = "Admin Deleted Successfully";
            } else {
                error = "There was an Error in Processing Your Request";
            }
            model.addAttribute("error",error);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            return Data.Connection("Admin/Access.html",Error_Page);
        }
    }

    // Add an Admin.
    @PostMapping ("/add_admins")
    public String AddAdmin (Model model, @ModelAttribute Admin MyObj) throws SQLException
    {
        if (flag==2) {
            String Name = MyObj.getName();
            String EmailID = MyObj.getEmailID();
            String LoginID = MyObj.getLoginID();
            String msg;
            String PassWord = MyObj.getPassWord();
            Admin My = new Admin();
            boolean Flag = My.new_admin(Name, EmailID, LoginID, PassWord);
            if (Flag) {
                msg = "Admin Added Successfully!";
            } else {
                msg = "There was an error in adding Admin. Check the Information and Try Again.";
            }
            model.addAttribute("msg", msg);
            return Data.Connection("Admin/AddAdmins.html", Error_Page);
        }
        else
        {
            return Data.Connection("Admin/Access.html",Error_Page);
        }
    }

    // General Settings.
    @RequestMapping("/general")
    public String remove (Model model) throws SQLException
    {
        if (flag!=1)
        {
            Admin MyAdmin = new Admin();
            ArrayList<Admin> a = MyAdmin.Profile(username);
            Page_To_Open = "Admin/Profile.html";
            model.addAttribute("a",a);
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    // Get Editing from Entry Page.
    @GetMapping ("/change_{id}")
    public String Password (Model model,@PathVariable("id") String id) throws SQLException
    {
        if (flag!=1)
        {
            Page_To_Open = "Admin/Password.html";
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    @PostMapping ("/change_{id}")
    public String Password (Model model,@PathVariable("id") String id, @ModelAttribute Admin MyObj) throws SQLException
    {
        if (flag!=1) {
            Page_To_Open = "Admin/Password.html";
            String Msg;
            Admin WRITE = new Admin();
            String Old = MyObj.getPassWord();
            String New = MyObj.getNewPWS();
            boolean Flag = WRITE.change_password(id, Old, New);
            if (Flag) {
                Msg = "Password Changed Successfully.";
                model.addAttribute("msg", Msg);
            } else {
                Msg = "Your Password couldn't be changed at Present. Try Again Later.";
                model.addAttribute("msg", Msg);
            }
            return Data.Connection(Page_To_Open, Error_Page);
        }
        else
        {
            model.addAttribute("error","You Need to Login First.");
            return Data.Connection("BlogSite/SignIn.html",Error_Page);
        }
    }

    //************************************ BLOG SITE ***************************************//

    // Home Page for Blog Site.
    @RequestMapping("/")
    public String Home_Page (Model model) throws SQLException {
        Page_To_Open="BlogSite/MainPage.html";
        Home_Page MyObj = new Home_Page();
        // First Four Blogs.
        ArrayList<Blog> a = MyObj.First_Four_Blogs();
        model.addAttribute("a",a);
        // First Four Latest Blogs from each Category.
        ArrayList<Blog> b = MyObj.Category_Wise("National");
        model.addAttribute("b",b);
        ArrayList<Blog> c = MyObj.Category_Wise("Global");
        model.addAttribute("c",c);
        ArrayList<Blog> d = MyObj.Category_Wise("Education");
        model.addAttribute("d",d);
        ArrayList<Blog> e = MyObj.Category_Wise("Technology");
        model.addAttribute("e",e);
        ArrayList<Blog> f = MyObj.Category_Wise("Media Logs");
        model.addAttribute("f",f);
        ArrayList<Blog> g = MyObj.Category_Wise("Life Style");
        model.addAttribute("g",g);
        ArrayList<Blog> h = MyObj.Category_Wise("View Point");
        model.addAttribute("h",h);
        ArrayList<Blog> i = MyObj.Category_Wise("Health");
        model.addAttribute("i",i);
        // Some Suggested Blogs.
        ArrayList <Blog> j = MyObj.Suggested_Blogs();
        model.addAttribute("j",j);
        return Data.Connection(Page_To_Open,Error_Page);
    }

    // Page Displaying All Blogs of a Specific Category.
    @RequestMapping("/{category}")
    public String Category_Blog (Model model , @PathVariable("category") String category ) throws SQLException {
        Page_To_Open="BlogSite/Seperate.html";
        List_Of_Blogs cat= new List_Of_Blogs();
        ArrayList<Blog> a = cat.category_page(category);
        model.addAttribute("a",a);
        model.addAttribute("Category",category);
        return Data.Connection(Page_To_Open,Error_Page);
    }

    // Open a Complete Blog.
    @RequestMapping("/blog_{id}")
    public String Full_Blog (Model model , @PathVariable("id") int id ) throws SQLException {
        Page_To_Open="BlogSite/BlogPage.html";
        // Complete Blog.
        Complete_Blog full = new Complete_Blog();
        ArrayList<Blog> a = full.Full_Blog(id);
        model.addAttribute("a",a);
        // Display Some Blogs from the Same Category, Some Recent and Suggested Blogs.
        String Category = Complete_Blog.Category;
        Home_Page MyObj = new Home_Page();
        ArrayList<Blog> b = MyObj.First_Four_Blogs();
        model.addAttribute("b",b);
        ArrayList<Blog> j = MyObj.Suggested_Blogs();
        model.addAttribute("j",j);
        ArrayList<Blog> c = MyObj.Category_Wise(Category);
        model.addAttribute("c",c);
        return Data.Connection(Page_To_Open,Error_Page);
    }

    // Display all the Blogs of a Particular Author.
    @RequestMapping("/author_{id}")
    public String AuthorID (Model model , @PathVariable("id") int id ) throws SQLException
    {
        Page_To_Open="BlogSite/Author.html";
        // Fetch Authors Details.
        Author Details = new Author();
        ArrayList <Author> b = Details.Details(id);
        // Fetch Author's Blogs.
        List_Of_Blogs MyBlog = new List_Of_Blogs();
        ArrayList <Blog> a = MyBlog.Author_Page(id);
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        return Data.Connection(Page_To_Open,Error_Page);
    }

    // Getting Contact Page.
    @GetMapping ("/write_for_us")
    public String Write ()
    {
        Page_To_Open="BlogSite/WriteForUs.html";
        return Data.Connection(Page_To_Open,Error_Page);
    }

    // Submitting Message.
    @PostMapping ("/write_for_us")
    public String Contact (Model model, @ModelAttribute Entries MyObj) throws SQLException
    {
        Page_To_Open="BlogSite/WriteForUs.html";
        String Name = MyObj.getName();
        String Email = MyObj.getEmail();
        String City = MyObj.getCity();
        String Phone = MyObj.getPhone();
        String intro = MyObj.getIntro();
        String Title = MyObj.getTitle();
        String Blog = MyObj.getBlog();
        String Msg;
        Entries WRITE = new Entries();
        boolean Flag = WRITE.write(Name,Email,intro,Phone,City,Title,Blog);
        if (Flag)
        {
            Msg="Message Sent Successfully! Thankyou for Contacting us.";
            model.addAttribute("msg",Msg);
        }
        else
        {
            Msg="Your Message couldn't be sent. Try Contacting the Administrator by some other way.";
            model.addAttribute("msg",Msg);
        }
        return Data.Connection(Page_To_Open,Error_Page);
    }



}
