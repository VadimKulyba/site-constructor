package basepackage.controller;

import basepackage.models.User;
import basepackage.models.enums.AuthType;
import basepackage.models.enums.Role;
import basepackage.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reg")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public Boolean registration(@RequestBody User user){
        try{

            user.setRole(String.valueOf(Role.USER));
            user.setType(String.valueOf(AuthType.DEFAULT_AUTH));
            user.setName(user.getLogin());
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            userService.add(user);

        }catch (Exception ex){
            return false;
        }
        return true;
    }
}
