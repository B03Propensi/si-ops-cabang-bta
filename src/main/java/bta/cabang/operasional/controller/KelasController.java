package bta.cabang.operasional.controller;

import java.util.List;
import java.util.NoSuchElementException;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class KelasController {
    @Autowired
    private KelasService kelasService;

    @GetMapping(value = "/jadwal/{idKelas}")
    private KelasModel getKelas(@PathVariable(value = "idKelas") Long idKelas) {
        try {
            return kelasService.getKelas(idKelas);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jadwal Kelas tidak ditemukan!"
            );
        }
    }

    @GetMapping(value = "/jadwal")
    public List<KelasModel> getAllKelas() {
        return kelasService.getAllKelas();
    }

    @PostMapping(value = "/jadwal")
    private ResponseEntity<String> addKelas(@Valid @RequestBody KelasModel kelas, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Jadwal Kelas gagal ditambah!"
            );
        } else {
            kelasService.addKelas(kelas);
            return ResponseEntity.ok("Jadwal Kelas berhasil ditambah!");
        }
    }

    @PutMapping(value = "jadwal/{idKelas}")
    private ResponseEntity<String> editKelas(@PathVariable(value = "idKelas") Long idKelas, @RequestBody KelasModel kelas) {
        try {
            kelasService.editKelas(idKelas, kelas);
            return ResponseEntity.ok("Jadwal Kelas berhasil diubah!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jadwal Kelas gagal diubah!"
            );
        }
    }

    @DeleteMapping(value = "/jadwal/{idKelas}")
    private ResponseEntity<String> deleteKelas(@PathVariable("idKelas") Long idKelas) {
        try {
            kelasService.deleteKelas(idKelas);
            return ResponseEntity.ok("Jadwal kelas berhasil dihapus!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Jadwal Kelas gagal dihapus!"
            );
        }
    }
}
