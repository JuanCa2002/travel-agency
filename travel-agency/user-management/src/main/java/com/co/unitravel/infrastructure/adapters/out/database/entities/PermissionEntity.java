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
@SequenceGenerator(name = "SEQ_PERMISSION", sequenceName = "SEQ_PERMISSION", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERMISSION")
    @Column(name = "id", length = 10)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;
}
