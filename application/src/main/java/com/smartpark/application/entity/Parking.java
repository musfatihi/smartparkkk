package com.smartpark.application.entity;

import com.smartpark.application.enums.Cities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Parkings")
@Getter
@Setter
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "parking",fetch = FetchType.EAGER)
    private Set<Floor> floors = Set.of();
}
