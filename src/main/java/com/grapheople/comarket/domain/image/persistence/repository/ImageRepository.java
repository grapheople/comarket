package com.grapheople.comarket.domain.image.persistence.repository;

import com.grapheople.comarket.domain.image.enums.ContentType;
import com.grapheople.comarket.domain.image.persistence.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByContentIdAndContentType(Long contentId, ContentType contentType);
}
