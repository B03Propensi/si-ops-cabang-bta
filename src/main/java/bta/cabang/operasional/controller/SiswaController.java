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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        List<SiswaModel> siswa = siswaService.getAllSiswa();
        model.addAttribute("siswa", siswa);

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
    public String addSiswaSubmit(@ModelAttribute SiswaModel siswa, RedirectAttributes redirectAttrs) {
        System.out.println("invoice");
        InvoiceModel invoice = new InvoiceModel();
        invoice.setSiswaInvoice(siswa);
        invoice.setPembuatInvoice(authService.getCurrentLoggedInUserByUsername());
        invoice.setProgramInvoice(siswa.getProgram());
        invoiceService.addInvoice(invoice);
        System.out.println("end invoice");

        siswaService.addSiswa(siswa);
        redirectAttrs.addAttribute("siswa", siswa);
        redirectAttrs.addAttribute("invoice", invoice);
        return "invoice";
    }
}
