package karthik.UrlShortner.Repository;

import karthik.UrlShortner.Model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
    Url findByOriginalurl(String url);
}
