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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    @GetMapping("/siswa/{idSiswa}/pembayaran")
    public String siswaPembayaran(
            @PathVariable Long idSiswa,
            Model model,
            RedirectAttributes redirectAttrs
    ) {
        model.addAttribute("kuitansi", new KuitansiModel());
        model.addAttribute("idSiswa", idSiswa);
//        model.addAttribute("listCabang", cabangService.getCabangList());
//        model.addAttribute("listProgram", programService.getAllProgram());
        return "form-siswaPembayaran";
    }

//    @PostMapping("siswa/{idSiswa}/pembayaran")
//    public String kuitansiPembayaran(@ModelAttribute SiswaModel siswa, Model model) {
//        KuitansiModel kuitansi = new KuitansiModel();
//        kuitansi.setSiswaKuitansi(siswa);
//        kuitansi.setPembuatKuitansi(authService.getCurrentLoggedInUserByUsername());
//        kuitansi.setProgramKuitansi(siswa.getProgram());
//        kuitansiService.addKuitansi(kuitansi);
//        siswaService.getSiswa(siswa.getIdSiswa());
//        siswa.setStatusPembayaran(1);
//        siswa.setKuitansi(kuitansiService.getKuitansiByIdSiswa(siswa.getIdSiswa()));
//        model.addAttribute("siswa", siswa);
//        model.addAttribute("kuitansi", kuitansi);
//        return "kuitansi";
//    }

    @PostMapping("siswa/{idSiswa}/pembayaran")
    public String kuitansiPembayaran(@ModelAttribute KuitansiModel kuitansi, @PathVariable Long idSiswa, Model model) {
        SiswaModel siswa = siswaService.getSiswa(idSiswa);
        kuitansi.setPembuatKuitansi(authService.getCurrentLoggedInUserByUsername());
        kuitansi.setTanggalPembayaran(new Date());
        kuitansi.setProgramKuitansi(siswa.getProgram());
        kuitansi.setSiswaKuitansi(siswa);
        kuitansiService.addKuitansi(kuitansi);

        siswa.setStatusPembayaran(1);
        siswa.setKuitansi(kuitansiService.getKuitansiByIdSiswa(idSiswa));
        model.addAttribute("siswa", siswa);
        model.addAttribute("kuitansi", kuitansi);
        return "kuitansi";
    }

    @GetMapping("/siswa/edit/{idSiswa}")
    public String editSiswaForm(
            @PathVariable Long idSiswa,
            Model model
    ) {
        SiswaModel siswa = siswaService.getSiswa(idSiswa);
        List<ProgramModel> listProgram = programService.getAllProgram();

        model.addAttribute("siswa", siswa);
        model.addAttribute("listProgram", listProgram);

        return "form-ubahSiswa";
    }

    @PostMapping("/siswa/edit/{idSiswa}")
    public String editSiswa(
            @PathVariable Long idSiswa,
            @ModelAttribute SiswaModel siswa,
            RedirectAttributes redirectAttrs
    ) {
        try {
            siswaService.editSiswa(idSiswa, siswa);
            redirectAttrs.addFlashAttribute("alert", "editSuccess");
            return "redirect:/siswa/" + idSiswa;
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "editFail");
            return "redirect:/siswa/" + idSiswa;
        }
    }

    @GetMapping("/siswa/delete/{idSiswa}")
    public String deleteSiswa(
            @PathVariable Long idSiswa,
            RedirectAttributes redirectAttrs
    ) {
        try {
            siswaService.deleteSiswa(idSiswa);
            redirectAttrs.addFlashAttribute("alert", "delSuccess");
            return "redirect:/siswa";

        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/siswa";

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "delFail");
            return "redirect:/siswa";
        }
    }

    @GetMapping("/siswa/statistik")
    public String statistikSiswa(Model model) {
        List<HashMap> chart = new ArrayList();
        int totalLunas = siswaService.getAllSiswaByPembayaran(1).size();
        int totalBelumLunas = siswaService.getAllSiswaByPembayaran(0).size();

        HashMap<String, String> objLunas = new HashMap<String, String>();
        objLunas.put("label", "lunas");
        objLunas.put("value", Integer.toString(totalLunas));
        chart.add(objLunas);

        HashMap<String, String> objBelumLunas = new HashMap<String, String>();
        objBelumLunas.put("label", "belum lunas");
        objBelumLunas.put("value", Integer.toString(totalBelumLunas));
        chart.add(objBelumLunas);

        model.addAttribute("chart", chart);
        return "siswa-statistik";
    }

    @GetMapping("/program")
    public String viewProgram(Model model) {
        List<ProgramModel> listProgram = programService.getAllProgram();
        model.addAttribute("listProgram", listProgram);

        return "program";
    }

    @GetMapping("/program/add/")
    public String addProgram(Model model) {
        ProgramModel program = new ProgramModel();
        model.addAttribute("program", program);
        return "form-addProgram";
    }

    @PostMapping("/program/add/")
    public String programAdded(@ModelAttribute ProgramModel program, Model model) {
        programService.addProgram(program);
        return "redirect:/program/";
    }
}
