package com.example.clientManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client {
    @Id
    @Column(
            name = "id",
            nullable = false
    )
    UUID id;

    @Column(name = "name")
    @NotBlank
    @Length(min = 2, max = 127)
    String name;

    @ElementCollection
    @CollectionTable(name="user_email",joinColumns = @JoinColumn(name = "id"))
    List<String> email;

    @ElementCollection
    @CollectionTable(name="user_phone",joinColumns = @JoinColumn(name = "id"))
    List<String> phoneNumber;
}
