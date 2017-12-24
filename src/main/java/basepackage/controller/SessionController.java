package basepackage.controller;

import basepackage.models.User;
import basepackage.service.UserService;
import basepackage.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("session")
public class SessionController {

    @Resource(name = "session")
    private Session session;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)// for personal page
    public User getCurrentUser(){
        return session.getUser();
    }

    @RequestMapping(method = RequestMethod.GET)// logout
    public void logout(){
        session.setUser(null);
    }
}
