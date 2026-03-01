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

import com.example.hospitalmonolitik.Entity.Pacienti;
import com.example.hospitalmonolitik.Service.PacientiService;

@Controller
@RequestMapping("/pacienti")
@CrossOrigin
public class PacientiController {

    private final PacientiService pacientiService;

    public PacientiController(PacientiService pacientiService) {
        this.pacientiService = pacientiService;
    }

    @PostMapping("/create/{repartiid}")
    public ResponseEntity<?> create(@RequestBody Pacienti pacienti, @PathVariable Long repartiid) {
        try {
            Pacienti createdPacienti = pacientiService.createPacienti(pacienti, repartiid);
            return new ResponseEntity<>(createdPacienti, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(pacientiService.getAllPacienti(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Pacienti pacienti = pacientiService.getPacientiById(id);
            return new ResponseEntity<>(pacienti, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{repartiid}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Long repartiid) {
        try {
            pacientiService.deletePacienti(repartiid, id);
            System.out.println("Pacienti me id " + id + " u fshi me sukses nga reparti me id " + repartiid);
            return new ResponseEntity<>("Pacienti me id " + id + " u fshi me sukses nga reparti me id " + repartiid,
                    HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
