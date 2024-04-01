package com.smartpark.application.entity;

import com.smartpark.application.enums.Types;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ParkingSpaces")
@Getter
@Setter
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nbr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    private Types type;

    @OneToMany(mappedBy = "parkingSpace",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = Set.of();

}
