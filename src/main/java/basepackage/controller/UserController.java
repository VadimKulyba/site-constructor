package basepackage.controller;

import basepackage.models.User;
import basepackage.service.UserService;
import basepackage.session.Session;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource(name = "session")
    private Session session;

    @RequestMapping(method = RequestMethod.POST)
    public User authorize(@RequestBody User user){
        User u = userService.getUser(user.getLogin());
        if (u != null && u.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))){
            session.setUser(u);
            return u;
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Collection<User> delete(@RequestParam("login") String login){
        userService.remove(login);
        return userService.getAll();
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public User update(@RequestBody User user){
        userService.update(user);
        session.setUser(user);
        return userService.getUser(user.getLogin());
    }

    // add site method

    //for current wort personal page
    @RequestMapping(value = "/{userLogin}",method = RequestMethod.POST)
    public User getByLogin(@PathVariable("userLogin") String login){
        return userService.getUser(login);
    }

    // for search
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<User> getAll(){
        return userService.getAll();
    }
}
