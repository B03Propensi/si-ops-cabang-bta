package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JadwalController {
    @Autowired
    private KelasService kelasService;

    @Autowired
    private CabangService cabangService;

    @GetMapping("/jadwal")
    public String viewAllJadwal(Model model) throws Exception {
        List<List<KelasModel>> cells = kelasService.getAllCells();
        model.addAttribute("cells", cells);

        return "jadwal";
    }

    @GetMapping("/jadwal/{idKelas}")
    public String viewJadwal(
            @PathVariable Long idKelas,
            Model model,
            RedirectAttributes redirectAttrs
    ) {
        try {
            KelasModel kelas = kelasService.getKelas(idKelas);
            model.addAttribute("kelas", kelas);
            return "detail-jadwal";

        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/jadwal";
        }
    }

    @PostMapping("/jadwal/tambah")
    public String tambahJadwal(
            @ModelAttribute KelasModel jadwalBaru,
            @ModelAttribute String dummyWaktuMulai,
            @ModelAttribute String dummyWaktuSelesai,
//            @ModelAttribute String hari,
            RedirectAttributes redirectAttrs
    ) {
        try {
//            jadwalBaru.setWaktuMulai(java.sql.Time.valueOf(dummyWaktuMulai));
//            jadwalBaru.setWaktuSelesai(java.sql.Time.valueOf(dummyWaktuSelesai));
//            jadwalBaru.setHari(jadwalBaru.getHari());
//            System.out.println(jadwalBaru.getWaktuMulai());
//            System.out.println(jadwalBaru.getWaktuSelesai());
//            System.out.println(jadwalBaru.getHari());
//            Time waktu = kelasService.generateWaktu(jadwalBaru.getWaktuMulai());
//            jadwalBaru.setWaktu(waktu);
            kelasService.addJadwal(jadwalBaru);
            redirectAttrs.addFlashAttribute("alert", "addSuccess");
            return "redirect:/jadwal";

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "addFail");
            return "redirect:/jadwal";
        }
    }

    @GetMapping("/jadwal/tambah")
    public String tambahJadwalForm(Model model) {
        KelasModel jadwalBaru = new KelasModel();
        List<KelasModel> listKelas = kelasService.getAllKelas();

        model.addAttribute("jadwalBaru", jadwalBaru);
        model.addAttribute("listKelas", listKelas);
        return "form-tambahJadwal";
    }

    @GetMapping("/jadwal/ubah/{idKelas}")
    public String ubahJadwalForm(
            @PathVariable Long idKelas,
            Model model
    ) {
        KelasModel kelas = kelasService.getKelas(idKelas);
        List<String> listHari = new ArrayList<>();

        listHari.add("Senin");
        listHari.add("Selasa");
        listHari.add("Rabu");
        listHari.add("Kamis");
        listHari.add("Jumat");
        listHari.add("Sabtu");

        model.addAttribute("kelas", kelas);
        model.addAttribute("listHari", listHari);

        return "form-ubahJadwal";
    }

    @PostMapping("/jadwal/ubah/{idKelas}")
    public String ubahJadwal(
            @PathVariable Long idKelas,
            @ModelAttribute KelasModel kelas,
            RedirectAttributes redirectAttrs
    ) {
        try {
            kelasService.editJadwal(idKelas, kelas);
            return "redirect:/jadwal/" + kelas.getIdKelas();
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "editFail");
            return "redirect:/jadwal";
        }
    }

    @GetMapping("/jadwal/hapus/{idKelas}")
    public String deleteJadwal(
            @PathVariable Long idKelas,
            RedirectAttributes redirectAttrs
    ) {
        try {
            kelasService.deleteJadwal(idKelas);
            redirectAttrs.addFlashAttribute("alert", "delSuccess");
            return "redirect:/jadwal";

        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/jadwal";

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "delFail");
            return "redirect:/jadwal";
        }
    }

}
