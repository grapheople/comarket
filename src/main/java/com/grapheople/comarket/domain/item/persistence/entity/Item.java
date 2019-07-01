package com.grapheople.comarket.domain.item.persistence.entity;

import com.grapheople.comarket.domain.image.persistence.entity.Image;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false, length = 20)
    Long id;
    Long userId;
    Long companyId;

    @Column(length = 20)
    String title;
    @Column(length = 500)
    String body;
    Double price;

    @OneToMany
    @Where(clause = "contentType = 'ITEM'")
    @JoinColumn(name ="contentId")
    List<Image> images;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @PrePersist
    protected void onPersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
