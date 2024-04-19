package com.co.unitravel.infrastructure.adapters.out.database.entities;

import com.co.unitravel.domain.models.enums.CommentStatus;
import com.co.unitravel.domain.models.enums.CommentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_COMMENT", sequenceName = "SEQ_COMMENT", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMMENT")
    @Column(name = "id", length = 10)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private AccommodationEntity accommodation;

    @Column(name = "customer_id", nullable = false, length = 8)
    private Long customerId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "comment_date", nullable = false)
    private LocalDate commentDate;

    @Column(name = "comment_time", nullable = false)
    private LocalTime commentTime;

    @Column(name = "comment_edit_date")
    private LocalDate commentEditDate;

    @Column(name = "comment_edit_time")
    private LocalTime commentEditTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_type", nullable = false)
    private CommentType commentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", nullable = true)
    private CommentEntity comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status", nullable = false)
    private CommentStatus commentStatus;
}
