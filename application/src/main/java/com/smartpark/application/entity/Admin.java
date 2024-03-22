package com.smartpark.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
