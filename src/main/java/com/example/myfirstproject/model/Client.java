package com.example.myfirstproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
@Entity
@Table(name = "client",indexes = {
        @Index(name = "idx_firstname",columnList = "firstname"),
        @Index(name = "idx_lastname",columnList = "lastname"),
        @Index(name = "idx_age",columnList = "age")
})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @PrePersist
    public void prePersist(){
        System.out.println("Inside prePersist");
    }

    @PostPersist
    public void postPersist(){
        photo=photo.replace("null",id+"");
        System.out.println(photo);
    }
}
