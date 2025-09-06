package karthik.UrlShortner.Controller;

import karthik.UrlShortner.Dto.RequestUrl;
import karthik.UrlShortner.Dto.ResponseUrl;
import karthik.UrlShortner.Model.Url;
import karthik.UrlShortner.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlRecieverController {
    @Autowired
    private UrlService urlservice;
    @PostMapping("/shortenurl")
    public ResponseEntity<ResponseUrl> shortenurl(@RequestBody RequestUrl url )
    {
        ResponseUrl response = new ResponseUrl();
        String originalurl = url.getOriginalUrl();
         StringBuilder encodedid = urlservice.storeindb(originalurl);
         StringBuilder basic = new StringBuilder("http://localhost:8080/");
         String shorturl = basic.append(encodedid).toString();
         response.setShorturl(shorturl);
         return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
