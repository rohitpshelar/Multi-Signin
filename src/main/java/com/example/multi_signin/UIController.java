package com.example.multi_signin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UIController {
    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User oAuth2User, @RegisteredOAuth2AuthorizedClient Authentication authentication) {
        System.out.println(oAuth2User.getAttributes());

        var oAuthIdToken = (OAuth2AuthenticationToken)authentication;
        String oAuthId = oAuthIdToken.getAuthorizedClientRegistrationId();

        //  *****  GitHub  *****
        if(oAuthId.equalsIgnoreCase("GitHub")) {
            model.addAttribute("img", oAuth2User.getAttribute("avatar_url"));
            model.addAttribute("user", oAuth2User.getAttribute("login"));
            model.addAttribute("email", oAuth2User.getAttribute("email"));
            model.addAttribute("profileLink", oAuth2User.getAttribute("html_url"));
            model.addAttribute("repos", oAuth2User.getAttribute("repos_url"));
        }
        //  *****  Google  *****
        if(oAuthId.equalsIgnoreCase("Google")) {
            model.addAttribute("img", oAuth2User.getAttribute("picture"));
            model.addAttribute("user", oAuth2User.getAttribute("name"));
            model.addAttribute("email", oAuth2User.getAttribute("email"));
        }
        //  *****  Facebook  *****
        if(oAuthId.equalsIgnoreCase("Facebook")) {
            model.addAttribute("user", oAuth2User.getAttribute("name"));
            model.addAttribute("email", oAuth2User.getAttribute("email"));
        }

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("In Login Method");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (oAuth2User != null) {
            System.out.println(authentication.toString());
            model.addAttribute("img", oAuth2User.getAttribute("avatar_url"));
            model.addAttribute("user", oAuth2User.getAttribute("login"));
            model.addAttribute("profileLink", oAuth2User.getAttribute("html_url"));
            model.addAttribute("repos", oAuth2User.getAttribute("repos_url"));
            return "home";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.clearContext();
        }
        request.getSession().invalidate();
        return "logout";
    }
    
    @GetMapping("/error")
    public String error(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("errorMessage", error != null ? error : "Unknown error occurred.");
        return "error";
    }
}
