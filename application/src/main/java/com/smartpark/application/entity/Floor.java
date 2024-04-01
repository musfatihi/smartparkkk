package com.smartpark.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Floors")
@Getter
@Setter
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer nbr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_id", nullable = false)
    private Parking parking;

    @OneToMany(mappedBy = "floor",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParkingSpace> parkingSpaces = Set.of();

}
