package com.fsy2001.artwork.repository;

import com.fsy2001.artwork.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Integer> {
    List<Artwork> findByTitleLike(String title);

    List<Artwork> findByAuthorLike(String author);
}
