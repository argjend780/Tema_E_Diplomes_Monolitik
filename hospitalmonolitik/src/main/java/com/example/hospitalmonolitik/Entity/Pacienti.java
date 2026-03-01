package com.example.hospitalmonolitik.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacienti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pacienti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pacientiid;

    @Column(name = "emri", nullable = false)
    private String emri;

    @Column(name = "mbiemri", nullable = false)
    private String mbiemri;

    @Column(name = "email", nullable = false)
    private String email;

    // One to Many me Reparti
    @ManyToOne
    @JoinColumn(name = "repartiid", nullable = false)
    private Reparti reparti;

    @OneToMany(mappedBy = "pacienti")
    @JsonIgnore
    private List<Takimi> takimet;

    @OneToMany(mappedBy = "pacienti")
    @JsonIgnore
    private List<Diagnoza> diagnozat;

}
