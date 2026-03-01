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

import com.example.hospitalmonolitik.Entity.Diagnoza;
import com.example.hospitalmonolitik.Service.DiagnozaService;

@Controller
@RequestMapping("/diagnoza")
@CrossOrigin
public class DiagnozaController {

    private final DiagnozaService diagnozaService;

    public DiagnozaController(DiagnozaService diagnozaService) {
        this.diagnozaService = diagnozaService;
    }

    @PostMapping("/create/{pacientiid}")
    public ResponseEntity<?> create(@RequestBody Diagnoza diagnoza, @PathVariable Long pacientiid) {
        try {
            Diagnoza createdDiagnoza = diagnozaService.createDiagnoza(diagnoza, pacientiid);
            return new ResponseEntity<>(createdDiagnoza, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(diagnozaService.getAllDiagnoza(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Diagnoza diagnoza = diagnozaService.getDiagnozaById(id);
            return new ResponseEntity<>(diagnoza, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{pacientiid}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long pacientiid, @PathVariable Long id) {
        try {
            diagnozaService.deleteDiagnoza(pacientiid, id);
            System.out.println("Diagnoza me id " + id + " u fshi me sukses nga pacienti me id " + pacientiid);
            return new ResponseEntity<>("Diagnoza me id " + id + " u fshi me sukses nga pacienti me id " + pacientiid,
                    HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
