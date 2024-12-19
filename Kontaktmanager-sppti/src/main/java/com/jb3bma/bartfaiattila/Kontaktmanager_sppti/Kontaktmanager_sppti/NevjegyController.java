package com.jb3bma.bartfaiattila.Kontaktmanager_sppti.Kontaktmanager_sppti;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nevjegyek")
public class NevjegyController {

    private final NevjegyRepository nevjegyRepository;

    public NevjegyController(NevjegyRepository nevjegyRepository) {
        this.nevjegyRepository = nevjegyRepository;
    }

    @GetMapping("/count")
    public Long getNevjegyekCount() {
        return nevjegyRepository.count();
    }
    
    @GetMapping
    public List<Nevjegy> getAllNevjegyek() {
        return nevjegyRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Nevjegy> addNevjegy(@RequestBody Nevjegy nevjegy) {
        Nevjegy savedNevjegy = nevjegyRepository.save(nevjegy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNevjegy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nevjegy> updateNevjegy(@PathVariable Integer id, @RequestBody Nevjegy nevjegyDetails) {
        return nevjegyRepository.findById(id)
            .map(existingNevjegy -> {
                // Frissítési mezők csak nem-null értékekkel
                if (nevjegyDetails.getMegszolitas() != null) {
                    existingNevjegy.setMegszolitas(nevjegyDetails.getMegszolitas());
                }
                if (nevjegyDetails.getTitulus() != null) {
                    existingNevjegy.setTitulus(nevjegyDetails.getTitulus());
                }
                if (nevjegyDetails.getVezeteknev() != null) {
                    existingNevjegy.setVezeteknev(nevjegyDetails.getVezeteknev());
                }
                if (nevjegyDetails.getKeresztnev() != null) {
                    existingNevjegy.setKeresztnev(nevjegyDetails.getKeresztnev());
                }
                if (nevjegyDetails.getUtonev() != null) {
                    existingNevjegy.setUtonev(nevjegyDetails.getUtonev());
                }
                if (nevjegyDetails.getIranyitoszam() != null) {
                    existingNevjegy.setIranyitoszam(nevjegyDetails.getIranyitoszam());
                }
                if (nevjegyDetails.getTelepules() != null) {
                    existingNevjegy.setTelepules(nevjegyDetails.getTelepules());
                }
                if (nevjegyDetails.getUtca() != null) {
                    existingNevjegy.setUtca(nevjegyDetails.getUtca());
                }
                if (nevjegyDetails.getHazszam() != null) {
                    existingNevjegy.setHazszam(nevjegyDetails.getHazszam());
                }
                if (nevjegyDetails.getEmeletAjto() != null) {
                    existingNevjegy.setEmeletAjto(nevjegyDetails.getEmeletAjto());
                }
                if (nevjegyDetails.getTelefonszam() != null) {
                    existingNevjegy.setTelefonszam(nevjegyDetails.getTelefonszam());
                }
                if (nevjegyDetails.getEmail() != null) {
                    existingNevjegy.setEmail(nevjegyDetails.getEmail());
                }

                // Mentés az adatbázisba
                Nevjegy updatedNevjegy = nevjegyRepository.save(existingNevjegy);
                return ResponseEntity.ok(updatedNevjegy);
            })
            // .orElse(ResponseEntity.notFound().build()); // eredeti
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
                    // .body("! Névjegy nem található az adott azonosítóval: " + id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Nevjegy> getNevjegyById(@PathVariable Integer id) {
        return nevjegyRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNevjegy(@PathVariable Integer id) {
        if (nevjegyRepository.existsById(id)) {
            nevjegyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

