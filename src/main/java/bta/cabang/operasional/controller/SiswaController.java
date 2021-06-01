package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.InvoiceModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.SiswaModel;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.InvoiceService;
import bta.cabang.operasional.service.ProgramService;
import bta.cabang.operasional.service.SiswaService;
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
}
