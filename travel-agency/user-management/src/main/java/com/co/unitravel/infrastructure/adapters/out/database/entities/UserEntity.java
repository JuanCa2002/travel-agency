package com.co.unitravel.infrastructure.adapters.out.database.entities;

import com.co.unitravel.domain.models.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @Column(name = "id", length = 10)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "last_names", length = 50, nullable = false)
    private String lastNames;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "document_number", length = 10, nullable = false)
    private String documentNumber;

    @Column(name = "phoneNumber", length = 13)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "city_id", length = 10, nullable = false)
    private Long cityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8, nullable = false)
    private UserStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentTypeEntity documentType;
}
