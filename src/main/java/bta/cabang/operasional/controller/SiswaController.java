package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SiswaController {
    @Autowired
    SiswaService siswaService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    ProgramService programService;

    @Autowired
    CabangService cabangService;

    @Autowired
    KuitansiService kuitansiService;

    @Autowired
    AuthService authService;

    @GetMapping("/siswa")
    public String viewAllSiswa(Model model) {
        List<SiswaModel> listSiswa = siswaService.getAllSiswa();
        model.addAttribute("listSiswa", listSiswa);

        return "siswa";
    }

    @GetMapping("siswa/add")
    public String addSiswaForm(Model model) {
        model.addAttribute("siswa", new SiswaModel());
        model.addAttribute("listCabang", cabangService.getCabangList());
        model.addAttribute("listProgram", programService.getAllProgram());
        return "form-addSiswa";
    }

    @PostMapping("siswa/add")
    public String addSiswaSubmit(@ModelAttribute SiswaModel siswa, Model model) {
        InvoiceModel invoice = new InvoiceModel();
        invoice.setSiswaInvoice(siswa);
        invoice.setPembuatInvoice(authService.getCurrentLoggedInUserByUsername());
        invoice.setProgramInvoice(siswa.getProgram());
        invoiceService.addInvoice(invoice);

        siswa.setInvoice(invoiceService.getInvoiceByIdSiswa(siswa.getIdSiswa()));
        siswaService.addSiswa(siswa);
        model.addAttribute("siswa", siswa);
        model.addAttribute("invoice", invoice);
        return "invoice";
    }

    @GetMapping("/siswa/{idSiswa}")
    public String siswaDetails(
            @PathVariable Long idSiswa,
            Model model,
            RedirectAttributes redirectAttrs
    ) {
        try {
            SiswaModel siswa = siswaService.getSiswa(idSiswa);
            model.addAttribute("siswa", siswa);
            return "siswa-details";

        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/siswa";
        }
    }

//    @GetMapping("/siswa/{idSiswa}/pembayaran")
//    public String siswaPembayaran(
//            @PathVariable Long idSiswa,
//            Model model,
//            RedirectAttributes redirectAttrs
//    ) {
//        model.addAttribute("siswa", new SiswaModel());
//        model.addAttribute("listCabang", cabangService.getCabangList());
//        model.addAttribute("listProgram", programService.getAllProgram());
//        return "form-siswaPembayaran";
//    }
//
//    @PostMapping("siswa/{idSiswa}/kuitansi")
//    public String kuitansiPembayaran(@ModelAttribute SiswaModel siswa, Model model) {
//        KuitansiModel kuitansi = new KuitansiModel();
//        kuitansi.setSiswaKuitansi(kuitansi);
//        kuitansi.setPembuatKuitansi(authService.getCurrentLoggedInUserByUsername());
//        kuitansi.setProgramKuitansi(siswa.getProgram());
//        kuitansiService.addKuitansi(kuitansi);
//
//        siswa.setKuitansi(kuitansiService.getKuitansiByIdSiswa(kuitansi.getIdSiswa()));
//        model.addAttribute("siswa", siswa);
//        model.addAttribute("kuitansi", kuitansi);
//        return "kuitansi";
//    }
}
