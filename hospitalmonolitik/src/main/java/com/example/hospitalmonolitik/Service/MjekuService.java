package com.example.hospitalmonolitik.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hospitalmonolitik.Entity.Mjeku;
import com.example.hospitalmonolitik.Entity.Reparti;
import com.example.hospitalmonolitik.Repository.MjekuRepository;
import com.example.hospitalmonolitik.Repository.RepartiRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MjekuService {

    private final MjekuRepository mjekuRepository;
    private final RepartiRepository repartiRepository;

    public Mjeku createMjeku(Mjeku mjeku, Long repartiId) {
        Reparti reparti = repartiRepository.findById(repartiId)
                .orElseThrow(() -> new RuntimeException("Reparti me id" + repartiId + " nuk u gjend"));
        mjeku.setReparti(reparti);
        return mjekuRepository.save(mjeku);
    }

    public Mjeku getMjekuById(Long id) {
        return mjekuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mjeku me id: " + id + " nuk u gjend"));
    }

    public void deleteMjeku(Long repartiid, Long mjekuid) {
        Reparti reparti = repartiRepository.findById(repartiid)
                .orElseThrow(() -> new RuntimeException("Reparti me id: " + repartiid + " nuk u gjend"));
        Mjeku mjeku = mjekuRepository.findById(mjekuid)
                .orElseThrow(() -> new RuntimeException("Mjeku me id: " + mjekuid + " nuk u gjend"));
        if (!mjeku.getReparti().equals(reparti)) {
            throw new RuntimeException("Mjeku nuk i takon repartit me id: " + repartiid);
        }
        mjekuRepository.delete(mjeku);
    }

    public List<Mjeku> getAllMjeku() {
        return mjekuRepository.findAll();
    }

}
