package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class JadwalController {
    @Autowired
    private KelasService kelasService;

    @GetMapping("/jadwal")
    public String viewAllJadwal(Model model) {
        List<List<String>> cells = kelasService.getAllCells();
        model.addAttribute("cells", cells);
        return "jadwal";
    }
}
