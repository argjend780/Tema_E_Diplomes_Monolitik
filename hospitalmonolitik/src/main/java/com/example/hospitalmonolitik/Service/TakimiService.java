package com.example.hospitalmonolitik.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.hospitalmonolitik.Entity.Mjeku;
import com.example.hospitalmonolitik.Entity.Pacienti;
import com.example.hospitalmonolitik.Entity.Takimi;
import com.example.hospitalmonolitik.Repository.TakimiRepository;
import com.example.hospitalmonolitik.Repository.MjekuRepository;
import com.example.hospitalmonolitik.Repository.PacinetiRepository;

@Service
@RequiredArgsConstructor
public class TakimiService {

    private final TakimiRepository takimiRepository;
    private final MjekuRepository mjekuRepository;
    private final PacinetiRepository pacientiRepository;

    public Takimi createTakimi(Takimi takimi, Long mjekuId, Long pacientiId) {

        Mjeku mjeku = mjekuRepository.findById(mjekuId)
                .orElseThrow(() -> new RuntimeException("Mjeku nuk u gjet"));

        Pacienti pacienti = pacientiRepository.findById(pacientiId)
                .orElseThrow(() -> new RuntimeException("Pacienti nuk u gjet"));

        takimi.setMjeku(mjeku);
        takimi.setPacienti(pacienti);

        return takimiRepository.save(takimi);
    }

}
