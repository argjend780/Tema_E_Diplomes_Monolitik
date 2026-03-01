package com.example.hospitalmonolitik.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "takimi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Takimi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long takimiId;

    @Column(name = "data_ora", nullable = false)
    private LocalDateTime data_ora;

    @Column(name = "arsyeja", nullable = false)
    private String arsyeja;

    @ManyToOne
    @JoinColumn(name = "mjekuid", nullable = false)
    private Mjeku mjeku;

    @ManyToOne
    @JoinColumn(name = "pacientiid", nullable = false)
    private Pacienti pacienti;

}