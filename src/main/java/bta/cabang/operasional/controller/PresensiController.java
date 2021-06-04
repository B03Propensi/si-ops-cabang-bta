package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.model.PresensiModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.KelasService;
import bta.cabang.operasional.service.PresensiService;
import bta.cabang.operasional.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.String;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PresensiController {
    @Autowired
    PresensiService presensiService;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    CabangService cabangService;

    @Autowired
    KelasService kelasService;

    @GetMapping("/presensi")
    public String viewAllpresensi(Model model) {
        UserModel currentUser = authService.getCurrentLoggedInUserByUsername();
        Long role = currentUser.getRole().getIdRole();
        Long id_user = currentUser.getIdUser();

        List<PresensiModel> listpresensi = new ArrayList<>();
        String tanggal_terakhir;
        if (role == 3 || role == 4 || role == 5) {
            listpresensi = presensiService.getAllPresensiByUser(id_user);

        } else if (role == 2){
            listpresensi = listpresensi;
        }else{
            listpresensi = presensiService.getPresensiList();
        }
        if(listpresensi.size() != 0){
            tanggal_terakhir = listpresensi.get(listpresensi.size()-1).getTanggal();
        }else{
            tanggal_terakhir = "";
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy", new java.util.Locale("id"));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
        Date d = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String dayOfTheWeek = dateFormat.format(d);
        String tanggal = dateFormat.format(timestamp);
        String waktu = dateFormat2.format(timestamp);
        dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        System.out.println(dayOfTheWeek);
        System.out.println("COBAIN TIMESTAMP : " + tanggal);
        System.out.println("COBAIN Waktu : " + waktu);

        if (timestamp.getHours() > 8) {
            System.out.println("Telattttt");
        } else if (timestamp.getHours() == 8 && timestamp.getMinutes() > 0) {
            System.out.println("Telattttt");
        } else {
            System.out.println("hadiir");
        }
        model.addAttribute("tanggal", tanggal);
        model.addAttribute("tanggal_terakhir", tanggal_terakhir);
        System.out.println(tanggal);
        System.out.println(tanggal_terakhir);

        model.addAttribute("listPresensi", listpresensi);
        model.addAttribute("isDirop", role == 2);
        model.addAttribute("isPegawai", role == 3 || role == 4);
        model.addAttribute("isPengajar", role == 5);
        model.addAttribute("isAbleToAddPresensi", role == 3 || role == 4 || role == 5);
        model.addAttribute("DifferentDate", tanggal.compareTo(tanggal_terakhir));

        return "view-all-presensi-pegawai";
    }

    Timestamp timestamp;
    UserModel currentUser;
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy", new java.util.Locale("id"));
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm", Locale.getDefault());

    @GetMapping("/presensi/add")
    public String addPresensiForm(Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy", new java.util.Locale("id"));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm",Locale.getDefault());
        dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        timestamp = new Timestamp(System.currentTimeMillis());
        String tanggal = dateFormat.format(timestamp);
        String waktu = dateFormat2.format(timestamp);
        Integer status;
        currentUser = authService.getCurrentLoggedInUserByUsername();
        Long role = currentUser.getRole().getIdRole();

        if (timestamp.getHours() > 8) {
            status = 0;
        } else if (timestamp.getHours() == 8 && timestamp.getMinutes() > 0) {
            status = 0;
        } else {
            status = 1;
        }

        model.addAttribute("isPengajar", role == 5);
        model.addAttribute("listKelas", currentUser.getKelasPengajar());
        model.addAttribute("listCabang", cabangService.getCabangList());
        model.addAttribute("tanggal", tanggal);
        model.addAttribute("waktu", waktu);
        model.addAttribute("status", status);
        model.addAttribute("presensi", new PresensiModel());
        return "form-addPresensi";
    }

    @PostMapping("/presensi/add")
    public String addPresensiSubmit(@ModelAttribute PresensiModel presensiModel, RedirectAttributes redirectAttrs) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy", new java.util.Locale("id"));
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm",Locale.getDefault());
            dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            timestamp = new Timestamp(System.currentTimeMillis());

                presensiModel.setDate(timestamp);
                presensiModel.setUser(authService.getCurrentLoggedInUserByUsername());
                presensiModel.setJabatan(currentUser.getRole().getNamaRole());
                presensiModel.setTanggal(dateFormat.format(timestamp));
                presensiModel.setWaktu(dateFormat2.format(timestamp));



                if (timestamp.getHours() > 8) {
                    presensiModel.setStatus(0);
                    System.out.println("Telattttt");
                } else if (timestamp.getHours() == 8 && timestamp.getMinutes() > 0) {
                    presensiModel.setStatus(0);
                    System.out.println("Telattttt");
                } else {
                    presensiModel.setStatus(1);
                    System.out.println("hadiir");
                }

                presensiService.addPresensi(presensiModel);
                redirectAttrs.addFlashAttribute("alert", "addSuccess");
                return "redirect:/presensi";



        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "addFail");
            return "redirect:/presensi";
        }

    }

    @GetMapping("presensi/view/{id_presensi}")
    public String viewDetailPresensi(@PathVariable Integer id_presensi, Model model, RedirectAttributes redirectAttrs) {
        try {
            PresensiModel presensi = presensiService.getPresensibyId(id_presensi);
            UserModel currentUser = authService.getCurrentLoggedInUserByUsername();
            Long role = currentUser.getRole().getIdRole();

            model.addAttribute("presensi", presensi);
            ;
            model.addAttribute("isPegawai", role == 3 || role == 4);
            model.addAttribute("isPengajar", role == 5);
            return "view-presensi";

        } catch (NoSuchElementException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/presensi";
        }
    }
}
