package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

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

    @GetMapping("/siswa/stats/")
    public String siswaStats(Model model) {
        List<SiswaModel> siswa = siswaService.getAllSiswa();
        model.addAttribute("siswa", siswa);
        return "siswa-stats";
    }


//    @GetMapping("/siswa/filter")
//    public String filterPesawat(
//            @RequestParam(name="idSiswa") Optional<Long> idSiswa,
//            @RequestParam(name="idCabang") Optional<Long> idCabang,
//            @RequestParam(name="idProgram") Optional<Long> idProgram,
//            @RequestParam(name="statusPembayaran") Optional<Integer> statusPembayaran,
//            Model model
//    ){
//        List<SiswaModel> listSiswa = siswaService.getAllSiswa();
//        model.addAttribute("listSiswa", listSiswa);
//        List<CabangModel> listCabang = cabangService.getCabangList();
//        model.addAttribute("listCabang", listCabang);
//        List<ProgramModel> listProgram = programService.getAllProgram();
//        model.addAttribute("listProgram", listProgram);
//
//        Boolean containsObject = false;
//        model.addAttribute("containsObject", containsObject);
//
//        Long idSiswa = Long.parseLong("0");
//        try{
//            idSiswa = idSiswa.get();
//        }catch(Exception e){
//
//        }
//
//        Long idCabang = Long.parseLong("0");
//        try{
//            idCabang = idCabang.get();
//        }catch(Exception e){
//
//        }
//
//        Long idProgram = Long.parseLong("0");
//        try{
//            idProgram = idProgram.get();
//        }catch(Exception e){
//
//        }
//        Boolean bool = ((idPenerbangan!=0) || (idTipe!=0) || (idTeknisi!=0));
//        if(bool){
//            List<PesawatModel> listPesawat2 = pesawatService.getListPesawat();
//            List<PesawatModel> listPesawat = new ArrayList<PesawatModel>();
//            for(PesawatModel pesawat : listPesawat2){
//                listPesawat.add(pesawat);
//            }
//
//            for(PesawatModel pesawat : listPesawat2){
//                if(!(idPenerbangan == 0)){
//                    PenerbanganModel tp = penerbanganService.getPenerbanganById(idPenerbangan);
//                    List<PenerbanganModel> lp = pesawat.getListPenerbangan();
//                    if (!lp.contains(tp)){
//                        listPesawat.remove(pesawat);
//                        continue;
//                    }
//                }
//                if(!(idTipe == 0)){
//                    TipeModel tt = tipeService.getTipeById(idTipe);
//                    TipeModel lt = pesawat.getTipe();
//                    if(lt.getId() != tt.getId()){
//                        listPesawat.remove(pesawat);
//                        continue;
//                    }
//                }
//                if(!(idTeknisi == 0)){
//                    TeknisiModel tt = teknisiService.getTeknisiById(idTeknisi);
//                    List<TeknisiModel> lt = pesawat.getListTeknisi();
//                    if(!lt.contains(tt)){
//                        listPesawat.remove(pesawat);
//                        continue;
//                    }
//                }
//            }
//            if(listPesawat.size()!=0) containsObject = true;
//            model.addAttribute("containsObject", containsObject);
//            model.addAttribute("listPesawat", listPesawat);
//        }
//        return "pesawat-filter";
//
//    }

}
