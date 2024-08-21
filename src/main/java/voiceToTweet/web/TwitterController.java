package voiceToTweet.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
public class TwitterController {

    @Autowired
    private Twitter twitter;

    @RequestMapping("/twitter/auth")
    public RedirectView twitterAuth(HttpSession session) throws TwitterException {
        RequestToken requestToken = twitter.getOAuthRequestToken();
        session.setAttribute("requestToken", requestToken);
        return new RedirectView(requestToken.getAuthorizationURL());
    }

    @RequestMapping("/twitter/callback")
    public String twitterCallback(@RequestParam("oauth_verifier") String oauthVerifier, HttpSession session) throws TwitterException {
        RequestToken requestToken = (RequestToken) session.getAttribute("requestToken");
        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, oauthVerifier);
        session.setAttribute("accessToken", accessToken);
        return "redirect:/";
    }

    @PostMapping("/tweet")
    public ResponseEntity<Void> tweet(@RequestParam String transcript) {
        try {
            final TwitterV2 v2 = TwitterV2ExKt.getV2(twitter);
            v2.createTweet(null, null, null, null, null,
                    null, null, null, null, null, null,
                    transcript);
            return ResponseEntity.ok().build(); // return 200 OK if the tweet was posted successfully
        } catch (TwitterException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // return 500 Internal Server Error if an error occurred
        }
    }

    @RequestMapping("/twitter/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("accessToken");
        twitter.setOAuthAccessToken(null);
        return "redirect:/";
    }
}