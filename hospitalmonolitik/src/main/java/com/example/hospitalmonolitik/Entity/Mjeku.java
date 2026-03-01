package com.example.hospitalmonolitik.Entity;

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
import java.util.List;

@Entity
@Table(name = "mjeku")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mjeku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mjekuid;

    @Column(name = "emri", nullable = false)
    private String emri;
    @Column(name = "mbiemri", nullable = false)
    private String mbiemri;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "numri_telefonit", nullable = false)
    private String numri_telefonit;

    @ManyToOne
    @JoinColumn(name = "repartiid", nullable = false)
    private Reparti reparti;

    @OneToMany(mappedBy = "mjeku")
    @JsonIgnore
    private List<Takimi> takimet;
}
