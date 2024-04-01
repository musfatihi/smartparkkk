package com.smartpark.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends User{

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = Set.of();
}
