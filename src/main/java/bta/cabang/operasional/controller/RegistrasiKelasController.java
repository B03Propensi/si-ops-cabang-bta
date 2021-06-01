package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
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

    @PostMapping("/kelas/tambah")
    public String tambahKelas(
            @ModelAttribute KelasModel kelasBaru,
            RedirectAttributes redirectAttrs
    ) {
        try {
            kelasService.addKelas(kelasBaru);
            redirectAttrs.addFlashAttribute("alert", "addSuccess");
            return "redirect:/kelas";

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "addFail");
            return "redirect:/kelas";
        }
    }

    @GetMapping("/kelas/tambah")
    public String tambahKelasForm(Model model) {
        KelasModel kelasBaru = new KelasModel();
        List<UserModel> listPengajar = kelasService.getAllPengajar();
        List<CabangModel> listCabang = cabangService.getCabangList();

        model.addAttribute("kelasBaru", kelasBaru);
        model.addAttribute("listCabang", listCabang);
        model.addAttribute("listPengajar", listPengajar);
        return "form-tambahKelas";
    }

    @GetMapping("/kelas/ubah/{idKelas}")
    public String ubahKelasForm(
            @PathVariable Long idKelas,
            Model model
    ) {
        KelasModel kelas = kelasService.getKelas(idKelas);
        System.out.println(idKelas);
        System.out.println(kelas.getNamaKelas());
        List<Time> listWaktu = kelasService.getListWaktu();
        List<CabangModel> listCabang = cabangService.getCabangList();
        List<UserModel> listPengajar = kelasService.getAllPengajar();

        model.addAttribute("kelas", kelas);
        model.addAttribute("listWaktu", listWaktu);
        model.addAttribute("listCabang", listCabang);
        model.addAttribute("listPengajar", listPengajar);

        return "form-ubahKelas";
    }

    @PostMapping("/kelas/ubah/{idKelas}")
    public String ubahKelas(
            @PathVariable Long idKelas,
            @ModelAttribute KelasModel kelas,
            RedirectAttributes redirectAttrs
    ) {
        try {
            kelasService.editKelas(idKelas, kelas);
            return "redirect:/kelas/" + kelas.getIdKelas();
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "editFail");
            return "redirect:/kelas";
        }
    }

    @GetMapping("/kelas/hapus/{idKelas}")
    public String deleteKelas(
            @PathVariable Long idKelas,
            RedirectAttributes redirectAttrs
    ) {
        try {
            kelasService.deleteKelas(idKelas);
            redirectAttrs.addFlashAttribute("alert", "delSuccess");
            return "redirect:/kelas";

        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/kelas";

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "delFail");
            return "redirect:/kelas";
        }
    }

}
