package karthik.UrlShortner.Controller;

import karthik.UrlShortner.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlRedirectController {
    @Autowired
    private UrlService urlser;
    @GetMapping("/{shorturl}")
    public ResponseEntity<Void> shortToOriginal(@PathVariable String shorturl)
    {
        System.out.println();
         String mainurl = urlser.decodeandincrementcounturl(shorturl);
         if(mainurl==null)
         {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
        System.out.println(mainurl);
        return ResponseEntity.status(302).header("Location",mainurl).build();

    }
}
