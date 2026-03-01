package com.example.hospitalmonolitik.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hospitalmonolitik.Entity.Mjeku;
import com.example.hospitalmonolitik.Entity.Reparti;
import com.example.hospitalmonolitik.Service.MjekuService;
import com.example.hospitalmonolitik.Service.RepartiService;

@Controller
@RequestMapping("/mjeku")
@CrossOrigin

public class MjekuController {

    private final MjekuService mjekuService;
    private final RepartiService repartiService;

    public MjekuController(MjekuService mjekuService, RepartiService repartiService) {
        this.mjekuService = mjekuService;
        this.repartiService = repartiService;
    }

    @PostMapping("/create/{repartiId}")
    public ResponseEntity<?> create(@RequestBody Mjeku mjeku, @PathVariable Long repartiId) {
        try {
            Mjeku createdMjeku = mjekuService.createMjeku(mjeku, repartiId);
            return new ResponseEntity<>(createdMjeku, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Mjeku mjeku = mjekuService.getMjekuById(id);
            return new ResponseEntity<>(mjeku, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{repartiId}/{mjekuId}")
    public ResponseEntity<?> delete(@PathVariable Long repartiId, @PathVariable Long mjekuId) {
        try {
            // Reparti reparti = repartiService.getRepartiById(repartiId);
            // String emrireparti = reparti.getEmri();
            mjekuService.deleteMjeku(repartiId, mjekuId);
            System.out.println("Mjeku me id " + mjekuId + " u fshi me sukses nga reparti me id " + repartiId);
            return new ResponseEntity<>("Mjeku me id " + mjekuId + " u fshi me sukses nga reparti me id " + repartiId
                    + " me emrin ", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(mjekuService.getAllMjeku(), HttpStatus.OK);
    }
}
