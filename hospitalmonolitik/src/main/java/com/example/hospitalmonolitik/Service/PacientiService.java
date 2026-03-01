package com.example.hospitalmonolitik.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalmonolitik.Entity.Pacienti;
import com.example.hospitalmonolitik.Entity.Reparti;
import com.example.hospitalmonolitik.Repository.PacinetiRepository;
import com.example.hospitalmonolitik.Repository.RepartiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacientiService {

    private final PacinetiRepository pacientiRepository;
    private final RepartiRepository repartiRepository;

    // pacientiti duhet te i caktohet lidhja me reaprtin
    public Pacienti createPacienti(Pacienti pacienti, Long repartiId) {
        Reparti reparti = repartiRepository.findById(repartiId)
                .orElseThrow(() -> new RuntimeException("Reparti me id" + repartiId + " nuk u gjend"));
        pacienti.setReparti(reparti);
        return pacientiRepository.save(pacienti);
    }

    public List<Pacienti> getAllPacienti() {
        return pacientiRepository.findAll();
    }

    public Pacienti getPacientiById(Long id) {
        return pacientiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pacienti me id: " + id + " nuk u gjend"));
    }

    public void deletePacienti(Long repartiid, Long pacientiid) {
        Reparti reparti = repartiRepository.findById(repartiid)
                .orElseThrow(() -> new RuntimeException("Reparti me id: " + repartiid + " nuk u gjend"));
        Pacienti pacienti = pacientiRepository.findById(pacientiid)
                .orElseThrow(() -> new RuntimeException("Pacienti me id: " + pacientiid + " nuk u gjend"));
        if (!pacienti.getReparti().equals(reparti)) {
            throw new RuntimeException("Pacienti nuk i takon repartit me id: " + repartiid);
        }
        pacientiRepository.delete(pacienti);
    }
}