package basepackage.controller;

import basepackage.models.User;
import basepackage.models.enums.AuthType;
import basepackage.models.enums.Role;
import basepackage.service.UserService;
import basepackage.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("facebook")
public class FacebookController {

    @Autowired
    private FacebookConnectionFactory factory;

    @Autowired
    private UserService userService;

    @Resource(name = "session")
    private Session session;

    @RequestMapping(method = RequestMethod.POST)
    public String loggedIn(){
        OAuth2Operations operations = factory.getOAuthOperations();
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri("http://localhost/facebook");
        return operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void facebookAuth(@RequestParam("code") String code,
                             HttpServletResponse response){
        AccessGrant grant = factory.getOAuthOperations()
                .exchangeForAccess(code,"http://localhost/facebook",null);

        Connection<Facebook> connection = factory.createConnection(grant);
        FacebookProfile profile = connection.getApi().userOperations().getUserProfile();
        User user = userService.getUser(String.valueOf(profile.getId()));
        if (user == null){
            user = new User();
            user.setLogin(String.valueOf(profile.getId()));
            user.setName(profile.getName());
            user.setEmail(profile.getEmail());
            user.setRole(String.valueOf(Role.USER));
            user.setType(String.valueOf(AuthType.FACEBOOK_AUTH));
            user.setPassword("");
            userService.add(user);
        }
        session.setUser(user);
        try {
            response.sendRedirect("index.html");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
