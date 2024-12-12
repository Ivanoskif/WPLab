package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepositoryJpa extends JpaRepository<Artist, Long>  {
}
