package com.fsy2001.artwork.service;

import com.fsy2001.artwork.exception.WebRequestException;
import com.fsy2001.artwork.model.Artwork;
import com.fsy2001.artwork.model.Cart;
import com.fsy2001.artwork.model.User;
import com.fsy2001.artwork.repository.ArtworkRepository;
import com.fsy2001.artwork.repository.CartRepository;
import com.fsy2001.artwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ArtworkRepository artworkRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository,
                       ArtworkRepository artworkRepository,
                       UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.artworkRepository = artworkRepository;
        this.userRepository = userRepository;
    }


    public void addItem(String username, Integer itemId) {
        User user = userRepository.getUserByUsername(username);
        Artwork artwork = artworkRepository.getById(itemId);
        Cart cart = new Cart(user, artwork);
        if (cartRepository.existsById(cart.getId()))
            throw new WebRequestException("item already added");

        cartRepository.save(cart);
    }

    public void deleteItem(String username, Integer itemId) {
        User user = userRepository.getUserByUsername(username);
        Artwork artwork = artworkRepository.getById(itemId);
        Cart cart = cartRepository.findByUserAndArtwork(user, artwork);
        if (cart == null)
            throw new WebRequestException("item not added");
        cartRepository.delete(cart);
    }

    public List<Artwork> showCart(String username) {
        User user = userRepository.getUserByUsername(username);
        return cartRepository
                .findByUser(user)
                .stream()
                .map(Cart::getArtwork) // 将每个cart替换为其包含的artwork
                .collect(Collectors.toList());
    }
}