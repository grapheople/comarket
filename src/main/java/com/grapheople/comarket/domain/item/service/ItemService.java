package com.grapheople.comarket.domain.item.service;

import com.grapheople.comarket.common.exception.ComarketException;
import com.grapheople.comarket.domain.image.enums.ContentType;
import com.grapheople.comarket.domain.image.persistence.entity.Image;
import com.grapheople.comarket.domain.image.persistence.repository.ImageRepository;
import com.grapheople.comarket.domain.image.service.ImageService;
import com.grapheople.comarket.domain.item.model.ItemRequestModel;
import com.grapheople.comarket.domain.item.persistence.entity.Item;
import com.grapheople.comarket.domain.item.persistence.repository.ItemRepository;
import com.grapheople.comarket.domain.user.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public void save(ItemRequestModel itemRequestModel, User user) {
        Item item = new Item();
        item.setUserId(user.getId());
        item.setCompanyId(user.getCompanyId());
        item.setTitle(itemRequestModel.getTitle());
        item.setBody(itemRequestModel.getBody());
        item.setLocation(itemRequestModel.getLocation());
        item.setPrice(itemRequestModel.getPrice());
        Item savedItem = itemRepository.save(item);

        itemRequestModel.getImages().forEach(multipartFile->{
            Image image = new Image();
            image.setContentType(ContentType.ITEM);
            image.setContentId(savedItem.getId());
            image.setUrl(imageService.uploadImage(multipartFile));
            imageRepository.save(image);
        });
    }

    public class ItemServiceException extends ComarketException {
        public ItemServiceException(int code, String message) {
            super(HttpStatus.BAD_REQUEST, code, message);
        }
    }
}
