package com.grapheople.comarket.domain.auth.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@Data
public class EmailAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false, length = 20)
    protected Long id;
    @Column(unique = true)
    private String email;
    private String authCode;
    private LocalDateTime mailSendAt;
    private LocalDateTime authAt;
}
