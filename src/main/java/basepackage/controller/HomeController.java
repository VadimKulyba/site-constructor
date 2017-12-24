package basepackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public void redirect(HttpServletResponse response){
        try {
            response.sendRedirect("index.html");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}