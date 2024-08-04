package site.copi.security.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2/login")
public class OAuth2TestApi {

    @GetMapping
    public String get() {
        System.out.println("OAuth2TestApi.get");
        return "/login";
    }
}