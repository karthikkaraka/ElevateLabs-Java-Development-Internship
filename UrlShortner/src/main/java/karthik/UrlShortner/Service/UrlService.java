package karthik.UrlShortner.Service;

import karthik.UrlShortner.Model.Url;
import karthik.UrlShortner.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
@Autowired
private UrlRepository urlrepo;

    public StringBuilder storeindb(String originalurl) {

        if(urlrepo.findByOriginalurl(originalurl)!= null){
            Long idifpresent = urlrepo.findByOriginalurl(originalurl).getId();
            StringBuilder encodedid1 = encodeid(idifpresent);
            return encodedid1;
        }
        Url urlmodel = new Url();
        urlmodel.setCount(0);
        urlmodel.setOriginalurl(originalurl);
        urlrepo.save(urlmodel);
        Long id = urlrepo.findByOriginalurl(originalurl).getId();
        StringBuilder encodedid = encodeid(id);
        return encodedid;
    }

    private StringBuilder encodeid(Long id) {
        String Base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder encodedid = new StringBuilder();
        while(id>0)
        {
            int remainder = (int)(id%62);
            encodedid.append(Base62.charAt(remainder));
            id=id/62;
        }
        return encodedid.reverse();
    }

    public String decodeandincrementcounturl(String shortCode) {
        long id = 0;
        String Base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < shortCode.length(); i++) {
            id = id + Base62.indexOf(shortCode.charAt(i))
                    * (long)Math.pow(62, shortCode.length() - i - 1);
        }
        Optional<Url> originalurl = urlrepo.findById(id);
        String longurl= null;
        if(!originalurl.isEmpty())
        {
            Url url = originalurl.get();
            url.setCount(url.getCount()+1);
            urlrepo.save(url);
            longurl = url.getOriginalurl();
        }
        else{
            return null;
        }
        return longurl;
    }

    public int urlclickcount(String encodedid) {
        String longurl = decodeurl(encodedid);
        System.out.println(longurl);
       Url url = urlrepo.findByOriginalurl(longurl);
       if(url!=null)
       {
           return urlrepo.findByOriginalurl(longurl).getCount();
       }

      return 0;
    }
    public String decodeurl(String shortCode) {
        long id = 0;
        String Base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < shortCode.length(); i++) {
            id = id + Base62.indexOf(shortCode.charAt(i))
                    * (long)Math.pow(62, shortCode.length() - i - 1);
        }
        Optional<Url> originalurl = urlrepo.findById(id);
        String longurl= null;
        if(!originalurl.isEmpty())
        {
            Url url = originalurl.get();
            longurl = url.getOriginalurl();
        }
        else{
            return null;
        }
        return longurl;
    }
}
