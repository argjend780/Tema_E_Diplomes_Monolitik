package com.example.hospitalmonolitik.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.hospitalmonolitik.Service.TakimiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.hospitalmonolitik.Entity.Takimi;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/takimi")
@CrossOrigin
public class TakimiController {

    private final TakimiService takimiService;

    public TakimiController(TakimiService takimiService) {
        this.takimiService = takimiService;

    }

    @PostMapping("/create/{mjekuId}/{pacientiId}")
    public ResponseEntity<?> createTakimi(@RequestBody Takimi takimi,
            @PathVariable Long mjekuId,
            @PathVariable Long pacientiId) {
        try {

            Takimi createdTakimi = takimiService.createTakimi(takimi, mjekuId, pacientiId);

            return new ResponseEntity<>(createdTakimi, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
