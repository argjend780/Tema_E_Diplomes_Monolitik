package com.example.hospitalmonolitik.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "diagnoza")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnoza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diagnozaid;
    @Column(name = "pershkrimi", nullable = false)
    private String pershkrimi;

    @ManyToOne
    @JoinColumn(name = "patientiid", nullable = false)
    private Pacienti pacienti;

}
