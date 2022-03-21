package com.fsy2001.artwork.service;

import com.fsy2001.artwork.exception.WebRequestException;
import com.fsy2001.artwork.model.Artwork;
import com.fsy2001.artwork.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ArtworkService {
    private final ArtworkRepository artworkRepository;

    @Autowired
    public ArtworkService(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    public Artwork findArtwork(Integer id, boolean browse) { // 根据ID查找
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new WebRequestException("artwork-not-exist"));
        if (browse) { // 访问量增加
            artwork.addView();
            artworkRepository.save(artwork);
        }
        return artwork;
    }

    public List<Artwork> searchArtwork(String query, boolean isTitle, String sort) { // 根据作者或标题查找
        /* 查找 */
        String fuzzyQuery = "%" + query + "%";
        List<Artwork> result = isTitle ? artworkRepository.findByTitleLike(fuzzyQuery) : artworkRepository.findByAuthorLike(fuzzyQuery);

        /* 排序，ref: https://dev.to/codebyamir/sort-a-list-of-objects-by-field-in-java-3coj */
        switch (sort) {
            case "view": result.sort(Comparator.comparing(Artwork::getView).reversed());
            case "price": result.sort(Comparator.comparing(Artwork::getPrice).reversed());
        }

        return result;
    }

    public List<Artwork> findTopView() {
        return artworkRepository.findFirst3ByOrderByView();
    }
}