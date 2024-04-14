package com.example.demo.patron;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patron")
public class Patron {
    @Id
    @SequenceGenerator(
            name = "patron_sequence",
            sequenceName = "patron_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patron_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private LocalDate dob;
    @Transient
    private Integer age;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    public Integer getAge() {
        if (dob == null) {
            return null;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }
}
