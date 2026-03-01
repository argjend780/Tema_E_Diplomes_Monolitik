package com.example.hospitalmonolitik.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalmonolitik.Entity.Reparti;
import com.example.hospitalmonolitik.Repository.RepartiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepartiService {

    private final RepartiRepository repartiRepository;

    public Reparti createReparti(Reparti reparti) {
        return repartiRepository.save(reparti);
    }

    public Reparti getRepartiById(Long id) {
        return repartiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reparti not found with id: " + id));
    }

    public Reparti updateReparti(Long id, Reparti updatedReparti) {
        Reparti existingReparti = repartiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reparti not found with id: " + id));

        existingReparti.setEmri(updatedReparti.getEmri());

        return repartiRepository.save(existingReparti);
    }

    public void deleteReparti(Long id) {
        Reparti existingReparti = repartiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reparti not found with id: " + id));

        repartiRepository.delete(existingReparti);
    }

    public List<Reparti> getAllReparti() {
        return repartiRepository.findAll();
    }

}