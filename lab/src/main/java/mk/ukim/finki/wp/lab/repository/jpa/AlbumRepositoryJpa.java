package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepositoryJpa extends JpaRepository<Album, Long>  {

}
