package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class JadwalController {
    @Autowired
    private KelasService kelasService;

    @GetMapping("/jadwal")
    public String viewAllJadwal(Model model) {
        List<List<KelasModel>> cells = kelasService.getAllCells();
        model.addAttribute("cells", cells);
        return "jadwal";
    }

    @GetMapping("/jadwal/{idKelas}")
    public String viewJadwal(
            @PathVariable Long idKelas,
            Model model
    ) {
        KelasModel kelas = kelasService.getKelas(idKelas);
        model.addAttribute("kelas", kelas);
        return "detail-jadwal";
    }
}
