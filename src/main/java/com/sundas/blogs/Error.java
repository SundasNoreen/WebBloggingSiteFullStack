package com.sundas.blogs;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class Error implements ErrorController  {

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "BlogSite/Error.html";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
