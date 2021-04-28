package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Time;
import java.util.List;

@Controller
public class JadwalController {
    @Autowired
    private KelasService kelasService;

    @Autowired
    private CabangService cabangService;

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

//    @GetMapping("/jadwal/{idKelas}")
//    public RedirectView viewJadwal(
//            @PathVariable Long idKelas,
//            RedirectAttributes attributes
//    ) {
//        KelasModel kelas = kelasService.getKelas(idKelas);
//        attributes.addFlashAttribute("kelas", kelas);
//        String link = "redirect:/jadwal/" + idKelas;
//        return new RedirectView(link);
//    }

    @PostMapping("/jadwal/tambah")
    public String addJadwal(@ModelAttribute KelasModel kelasBaru, Model model) {
        System.out.println(kelasBaru);
        kelasService.addKelas(kelasBaru);

        return "redirect:/jadwal";
    }

    @GetMapping("/jadwal/tambah")
    public String tambahJadwalForm(Model model) {
        KelasModel kelasBaru = new KelasModel();
        List<Time> listWaktu = kelasService.getListWaktu();
        List<CabangModel> listCabang = cabangService.getCabangList();

        model.addAttribute("kelasBaru", kelasBaru);
        model.addAttribute("listWaktu", listWaktu);
        model.addAttribute("listCabang", listCabang);
        return "form-tambahJadwal";
    }

    @GetMapping("/jadwal/ubah/{idKelas}")
    public String ubahJadwalForm(
            @PathVariable Long idKelas,
            Model model
    ) {
        KelasModel kelas = kelasService.getKelas(idKelas);
        System.out.println(idKelas);
        System.out.println(kelas.getNamaKelas());
        List<Time> listWaktu = kelasService.getListWaktu();
        List<CabangModel> listCabang = cabangService.getCabangList();

        model.addAttribute("kelas", kelas);
        model.addAttribute("listWaktu", listWaktu);
        model.addAttribute("listCabang", listCabang);

        return "form-ubahJadwal";
    }

    @PostMapping("/jadwal/ubah/")
    public String ubahJadwal(
            @ModelAttribute KelasModel kelas,
            Model model
    ) {
        kelasService.editKelas(kelas);

       return "redirect:/jadwal/" + kelas.getIdKelas();
    }
}
