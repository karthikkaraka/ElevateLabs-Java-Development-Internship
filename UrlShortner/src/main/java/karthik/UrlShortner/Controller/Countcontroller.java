package karthik.UrlShortner.Controller;

import karthik.UrlShortner.Dto.CountUrl;
import karthik.UrlShortner.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Countcontroller {
    @Autowired
    private UrlService urlser;
    @PostMapping("/clickcount")
    public ResponseEntity<Integer> count(@RequestBody CountUrl count)
    {
        String shorturl = count.getShorturl();
      int lastslash = shorturl.lastIndexOf("/");
      String encodedid = shorturl.substring(lastslash+1);
       int clickcount =  urlser.urlclickcount(encodedid);
        if(clickcount == 0) {
            return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
        }
       return new ResponseEntity<>(clickcount, HttpStatus.OK);
    }

}
