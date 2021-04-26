package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.service.CutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class CutiController {
    @Autowired
    private CutiService cutiService;

    @PostMapping(value = "/cuti")
    private ResponseEntity<String> addCuti(@Valid @RequestBody CutiModel cuti, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cuti gagal diajukan!"
            );
        } else {
            cutiService.addCuti(cuti);
            return ResponseEntity.ok("Cuti berhasil diajukan!");
        }
    }

    @GetMapping(value = "/cuti/all")
    private List<CutiModel> getAllCuti() { return cutiService.getAllCuti(); }

    @GetMapping(value = "/cuti/{idCuti}")
    private CutiModel viewCuti(@PathVariable("idCuti") Long idCuti) {
        try {
            return cutiService.getCutiByIdCuti(idCuti);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cuti tidak ditemukan!"
            );
        }
    }

    @DeleteMapping(value = "/cuti/delete/{idCuti}")
    private ResponseEntity<String> deleteCuti(@PathVariable("idCuti") Long idCuti) {
        try {
            cutiService.deleteCuti(idCuti);
            return ResponseEntity.ok("Cuti berhasil dihapus!");
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Cuti tidak ditemukan!"
            );
        }
    }
}
