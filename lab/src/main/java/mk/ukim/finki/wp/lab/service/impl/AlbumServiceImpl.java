package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepositoryJpa albumRepository;

    public AlbumServiceImpl(AlbumRepositoryJpa albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List findAll() {
        return albumRepository.findAll();
    }

    @Override
    @Transactional
    public Album getAlbumById(Long albumId)
    {return albumRepository.findAll().stream()
            .filter(a -> a.getId().equals(albumId))
            .findFirst()
            .orElse(null);
    }

}
