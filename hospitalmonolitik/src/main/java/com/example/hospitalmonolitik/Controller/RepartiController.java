package com.example.hospitalmonolitik.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hospitalmonolitik.Service.RepartiService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.hospitalmonolitik.Entity.Reparti;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@Controller
@RequestMapping("/reparti")
@CrossOrigin
public class RepartiController {

    private final RepartiService repartiService;

    public RepartiController(RepartiService repartiService) {
        this.repartiService = repartiService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reparti reparti) {

        Reparti createdReparti = repartiService.createReparti(reparti);

        return new ResponseEntity<>(createdReparti, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reparti>> getAll() {
        return new ResponseEntity<>(repartiService.getAllReparti(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Reparti reparti = repartiService.getRepartiById(id);
            return new ResponseEntity<>(reparti, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            repartiService.deleteReparti(id);
            System.out.println("Reparti me id " + id + " u fshi me sukses.");
            return new ResponseEntity<>("Reparti me id " + id + " u fshi me sukses.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Reparti reparti) {
        try {
            Reparti updatedReparti = repartiService.updateReparti(id, reparti);
            return new ResponseEntity<>(updatedReparti, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}