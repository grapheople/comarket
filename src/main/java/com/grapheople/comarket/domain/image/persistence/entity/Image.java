package com.grapheople.comarket.domain.image.persistence.entity;

import com.grapheople.comarket.domain.image.enums.ContentType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false, length = 20)
    Long id;
    Long contentId;
    @Enumerated(EnumType.STRING)
    ContentType contentType;
    String url;
}
