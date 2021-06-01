package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RegistrasiKelasController {

    @Autowired
    private KelasService kelasService;

    @Autowired
    private CabangService cabangService;

    @GetMapping("/kelas")
    public String viewAllKelas(Model model) {
        List<KelasModel> listKelas = kelasService.getAllKelas();
        model.addAttribute("listKelas", listKelas);

        return "kelas";
    }

}
