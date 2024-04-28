package com.co.unitravel.infrastructure.adapters.out.database.entities;

import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.enums.AccommodationStatus;
import jakarta.persistence.*;
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
@SequenceGenerator(name = "SEQ_ACCOMMODATION", sequenceName = "SEQ_ACCOMMODATION", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "accommodation")
public class AccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOMMODATION")
    @Column(name = "id", length = 10)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id", nullable = false)
    private DestinationEntity destination;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "photosUrls", nullable = false)
    private String photosUrls;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "maximumCapacity", nullable = false)
    private Integer maximumCapacity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "accommodationStatus", nullable = false)
    private AccommodationStatus accommodationStatus;

    @Column(name = "administrator_id", nullable = false, length = 8)
    private Long administratorId;
}
