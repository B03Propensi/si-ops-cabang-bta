package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PengajarController {
    @Autowired
    private KelasService kelasService;

    @GetMapping(value = "/pengajar")
    private List<UserModel> getPengajarByCabangAndBidang() {
        return kelasService.getAllPengajar(2L);
    }

    @PutMapping(value = "pengajar/{idKelas}")
    private ResponseEntity<String> editPengajar(@PathVariable(value = "idKelas") Long idKelas, @RequestBody KelasModel kelas) {
        try {
            kelasService.editPengajar(idKelas, kelas);
            return ResponseEntity.ok("Registrasi Pengajar berhasil disimpan!");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Registrasi Pengajar gagal disimpan!"
            );
        }
    }
}
