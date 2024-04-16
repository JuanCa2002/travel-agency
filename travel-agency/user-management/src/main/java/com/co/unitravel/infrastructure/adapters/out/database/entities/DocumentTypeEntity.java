package com.co.unitravel.infrastructure.adapters.out.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@SequenceGenerator(name = "SEQ_DOCUMENT_TYPE", sequenceName = "SEQ_DOCUMENT_TYPE", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "document_type")
public class DocumentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENT_TYPE")
    @Column(name = "id", length = 10)
    private Integer id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "keyType", length = 4, nullable = false)
    private String key;
}
