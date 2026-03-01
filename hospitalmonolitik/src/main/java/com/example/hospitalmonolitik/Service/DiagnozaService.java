package com.example.hospitalmonolitik.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalmonolitik.Entity.Diagnoza;
import com.example.hospitalmonolitik.Entity.Pacienti;
import com.example.hospitalmonolitik.Repository.DiagnozaRepository;
import com.example.hospitalmonolitik.Repository.PacinetiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiagnozaService {

    private final DiagnozaRepository diagnozaRepository;
    private final PacinetiRepository pacientiRepository;

    public Diagnoza createDiagnoza(Diagnoza diagnoza, Long pacientiid) {
        Pacienti pacienti = pacientiRepository.findById(pacientiid)
                .orElseThrow(() -> new RuntimeException("Pacienti me id " + pacientiid + " nuk u gjet"));
        diagnoza.setPacienti(pacienti);
        return diagnozaRepository.save(diagnoza);
    }

    public List<Diagnoza> getAllDiagnoza() {
        return diagnozaRepository.findAll();
    }

    public Diagnoza getDiagnozaById(Long id) {
        return diagnozaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnoza me id " + id + " nuk u gjet"));
    }

    public void deleteDiagnoza(Long pacientiid, Long id) {
        Diagnoza diagnoza = diagnozaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnoza me id " + id + " nuk u gjet"));
        Pacienti pacienti = pacientiRepository.findById(pacientiid)
                .orElseThrow(() -> new RuntimeException("Pacienti me id " + pacientiid + " nuk u gjet"));
        if (!diagnoza.getPacienti().equals(pacienti)) {
            throw new RuntimeException("Diagnoza nuk i takon pacientit me id " + pacientiid);
        }
        diagnozaRepository.delete(diagnoza);
    }

}
