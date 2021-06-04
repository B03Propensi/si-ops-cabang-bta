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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
        if (role == 3 || role == 4 || role == 5) {
            listpresensi = presensiService.getAllPresensiByUser(id_user);
        } else {
            listpresensi = presensiService.getPresensiList();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy",new java.util.Locale("id"));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
        Date d = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String dayOfTheWeek = dateFormat.format(d);
            String tanggal = dateFormat.format(timestamp);
        String waktu = dateFormat2.format(timestamp);
        System.out.println(dayOfTheWeek);
        System.out.println("COBAIN TIMESTAMP : " + tanggal);
        System.out.println("COBAIN Waktu : " + waktu);

        if(timestamp.getHours() > 8 ){
            System.out.println("Telattttt");
        }
        else if(timestamp.getHours() == 8 && timestamp.getMinutes() > 0){
            System.out.println("Telattttt");
        }
        else {
            System.out.println("hadiir");
        }
        model.addAttribute("listPresensi", listpresensi);
        model.addAttribute("isPegawai", role == 3 || role == 4 );
        model.addAttribute("isPengajar", role == 5);
        model.addAttribute("isAbleToAddPresensi", role == 3 || role == 4 || role == 5);

        return "view-all-presensi-pegawai";
    }

    Timestamp timestamp;
    UserModel currentUser;
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy",new java.util.Locale("id"));
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");

    @GetMapping("/presensi/add")
    public String addPresensiForm(Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMMM yyyy",new java.util.Locale("id"));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
        timestamp = new Timestamp(System.currentTimeMillis());
        String tanggal = dateFormat.format(timestamp);
        String waktu = dateFormat2.format(timestamp);
        Integer status;
        currentUser = authService.getCurrentLoggedInUserByUsername();
        Long role = currentUser.getRole().getIdRole();

        if(timestamp.getHours() > 8 ){
            status = 0;
        }
        else if(timestamp.getHours() == 8 && timestamp.getMinutes() > 0){
            status = 0;
        }
        else {
            status = 1;
        }

        model.addAttribute("isPengajar", role == 5);
        model.addAttribute("listKelas", kelasService.getAllKelas());
        model.addAttribute("listCabang", cabangService.getCabangList());
        model.addAttribute("tanggal", tanggal);
        model.addAttribute("waktu",waktu);
        model.addAttribute("status", status);
        model.addAttribute("presensi", new PresensiModel());
        return "form-addPresensi";
    }
    @PostMapping("/presensi/add")
    public String addPresensiSubmit(@ModelAttribute PresensiModel presensiModel, RedirectAttributes redirectAttrs){
        try {
            presensiModel.setDate(timestamp);
            presensiModel.setUser(authService.getCurrentLoggedInUserByUsername());
            presensiModel.setJabatan(currentUser.getRole().getNamaRole());
            presensiModel.setTanggal(dateFormat.format(timestamp));
            presensiModel.setWaktu(dateFormat2.format(timestamp));

            if(timestamp.getHours() > 8 ){
                presensiModel.setStatus(0);
                System.out.println("Telattttt");
            }
            else if(timestamp.getHours() == 8 && timestamp.getMinutes() > 0){
                presensiModel.setStatus(0);
                System.out.println("Telattttt");
            }
            else {
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
    public String viewDetailPresensi (@PathVariable Integer id_presensi, Model model, RedirectAttributes redirectAttrs){
        try {
            PresensiModel presensi = presensiService.getPresensibyId(id_presensi);
            UserModel currentUser = authService.getCurrentLoggedInUserByUsername();
            Long role = currentUser.getRole().getIdRole();

            model.addAttribute("presensi", presensi);;
            model.addAttribute("isPegawai", role == 3 || role == 4 );
            model.addAttribute("isPengajar", role == 5 );
            return "view-presensi";

        } catch (NoSuchElementException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/presensi";
        }
    }
}
