package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.model.PresensiModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.CutiService;
import bta.cabang.operasional.service.KelasService;
import bta.cabang.operasional.service.PresensiService;
import bta.cabang.operasional.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;
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

    @Autowired
    CutiService cutiService;

    @GetMapping("/presensi")
    public String viewAllpresensi(Model model) {
        UserModel currentUser = authService.getCurrentLoggedInUserByUsername();
        Long role = currentUser.getRole().getIdRole();
        Long id_user = currentUser.getIdUser();

        if(currentUser.getRole().getNamaRole().equals("Direktur Operasional")) {
            return "redirect:/statistik-presensi";
        }

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
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
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
        model.addAttribute("isKoordinator", role==3);
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
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
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
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
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

    @GetMapping("/presensi/update/{id_presensi}")
    private String updatePresensiForm(@PathVariable Integer id_presensi, Model model){

        PresensiModel presensiModel = presensiService.getPresensibyId(id_presensi);
        model.addAttribute("presensi", presensiModel );
        return "update-presensi-form";
    }

    @PostMapping("/presensi/update")
    private String updateCabangSubmit(@RequestParam Integer id_presensi, @ModelAttribute PresensiModel presensiModel, Model model, RedirectAttributes redirectAttrs){
        try{
            presensiService.updatePresensi(id_presensi,presensiModel);
            redirectAttrs.addFlashAttribute("alert", "updateSuccess");
            return "redirect:/presensi";
        }catch (Exception e){
            redirectAttrs.addFlashAttribute("alert", "updateFail");
            return "redirect:/presensi";
        }
    }



    @RequestMapping(value="/statistik-presensi")
    public String viewAllPresensi(
        @RequestParam(value = "id_cabang", required=false) Long id_cabang,
        Model model
        ){
        CabangModel cabangModel = new CabangModel();
        if(id_cabang != null) {
            cabangModel = cabangService.getCabangbyId(id_cabang);
        }
        UserModel currentUser = authService.getCurrentLoggedInUserByUsername();
        if(currentUser.getRole().getNamaRole().equals("Direktur Operasional")) {
            List<UserModel> listPegawaibaru = userService.findObjekPresensi(1);
            List<UserModel> listPegawai = new ArrayList<UserModel>();
            if(cabangModel.getNama_cabang() != null) {
                for(UserModel pegawai:listPegawaibaru) {
                    if(pegawai.getListPresensi() != null) {
                        if(!pegawai.getListPresensi().isEmpty() && pegawai.getListPresensi().get(pegawai.getListPresensi().size()-1).getLokasi().equals(cabangModel)) {
                            listPegawai.add(pegawai);
                        }
                    }
                }
            } else {
                for(UserModel pegawai:listPegawaibaru) {
                        if(pegawai.getListPresensi() != null) {
                            if(!pegawai.getListPresensi().isEmpty()) {
                            listPegawai.add(pegawai);
                        }
                    }
                }
            }

            List<HashMap<String, String>> chart = new ArrayList<HashMap<String, String>>();
            int totalCuti = 0;
            int totalPenuh = 0;
            int totalTerlambat = 0;
            int totalAbsen = 0;

            String[][] list = new String[listPegawai.size()][8];
            int i = 0;
            for(UserModel pegawai : listPegawai) {
                if(!(pegawai.getListPresensi()==null)) {
                    if(!pegawai.getListPresensi().isEmpty()) {
                        list[i][0] = pegawai.getNamaUser();
                        list[i][1] = pegawai.getRole().getNamaRole();
                        list[i][2] = "Bekerja";

                        int terlambat = 0;
                        int absen = 0;
                        int hadir = 0;
                        for(PresensiModel presensi : pegawai.getListPresensi()) {
                            if(presensi.getStatus().equals(0)) {
                                terlambat++;
                            } else if(presensi.getStatus().equals(1)) {
                                hadir++;
                            }
                        }

                        PresensiModel awalPresensi = pegawai.getListPresensi().get(0);
                        PresensiModel latestPresensi = pegawai.getListPresensi().get(pegawai.getListPresensi().size()-1);
                        LocalDate newDate = awalPresensi.getDate().toLocalDateTime().toLocalDate();
                        LocalDate lateDate = latestPresensi.getDate().toLocalDateTime().toLocalDate();

                        List<CutiModel> listCuti = cutiService.getAllCutiByUser(pegawai.getIdUser());
                        List<LocalDate> holidays = new ArrayList<LocalDate>();
                        for(CutiModel objekCuti : listCuti) {
                            if(objekCuti.getStatus() !=0) {
                                LocalDate start = new java.sql.Date(objekCuti.getTanggal_mulai().getTime()).toLocalDate();
                                LocalDate end = new java.sql.Date(objekCuti.getTanggal_selesai().getTime()).toLocalDate();
                                holidays.addAll(getDatesBetween(start, end));
                            }
                        }
                        long hariPresensi = countBusinessDaysBetween(newDate, lateDate, holidays);
                        List<LocalDate> emptyList = new ArrayList<>();
                        long hariCuti = 0L;
                        if(!holidays.isEmpty()) {
                            hariCuti = countBusinessDaysBetween(holidays.get(0), holidays.get(holidays.size()-1 ), emptyList);
                        }

                        absen = pegawai.getListPresensi().size() - ((int) hariPresensi) - ((int) hariCuti);

                        list[i][3] = String.valueOf(hariPresensi);
                        list[i][4] = Integer.toString(terlambat);
                        list[i][5] = String.valueOf(hariCuti);
                        list[i][6] = Integer.toString(absen);
                        list[i][7] = Integer.toString(hadir);
                        i++;

                        totalCuti += hariCuti;
                        totalAbsen += absen;
                        totalPenuh += hadir;
                        totalTerlambat += terlambat;
                    }
                }
            }

            HashMap<String, String> objekCuti = new HashMap<String, String>();
            objekCuti.put("label", "cuti");
            objekCuti.put("value", Integer.toString(totalCuti));
            chart.add(objekCuti);
            HashMap<String, String> objekTerlambat = new HashMap<String, String>();
            objekTerlambat.put("label", "terlambat");
            objekTerlambat.put("value", Integer.toString(totalTerlambat));
            chart.add(objekTerlambat);
            HashMap<String, String> objekAbsen = new HashMap<String, String>();
            objekAbsen.put("label", "absen");
            objekAbsen.put("value", Integer.toString(totalAbsen));
            chart.add(objekAbsen);
            HashMap<String, String> objekPenuh = new HashMap<String, String>();
            objekPenuh.put("label", "penuh");
            objekPenuh.put("value", Integer.toString(totalPenuh));
            chart.add(objekPenuh);

            model.addAttribute("chart", chart);
            model.addAttribute("list", list);
            model.addAttribute("listCabang", cabangService.getCabangList());
            model.addAttribute("cabangModel", cabangModel);
            return "daftar-presensi";
        } else if(currentUser.getRole().getNamaRole().equals("Koordinator Bidang Studi")) {
            List<UserModel> listPegawaibaru = userService.findObjekPresensi(2);

            List<UserModel> listPegawai = new ArrayList<UserModel>();
            if(cabangModel.getNama_cabang() != null) {
                System.out.println("happen" +"----------------------------" + cabangModel.getNama_cabang());
                for(UserModel pegawai:listPegawaibaru) {
                    if(pegawai.getListPresensi() != null) {
                        if(!pegawai.getListPresensi().isEmpty() && pegawai.getListPresensi().get(pegawai.getListPresensi().size()-1).getLokasi().equals(cabangModel)) {
                            listPegawai.add(pegawai);
                        }
                    }
                }
            } else {
                for(UserModel pegawai:listPegawaibaru) {
                    if(pegawai.getListPresensi() != null) {
                        if(!pegawai.getListPresensi().isEmpty()) {
                            listPegawai.add(pegawai);
                        }
                    }
                }
            }
            String[][] list = new String[listPegawai.size()][8];
            int i = 0;

            List<HashMap<String, String>> chart = new ArrayList<HashMap<String, String>>();
            int totalCuti = 0;
            int totalPenuh = 0;
            int totalTerlambat = 0;
            int totalAbsen = 0;
            for(UserModel pegawai : listPegawai) {
                if(!(pegawai.getListPresensi() == null)) {
                    if(!pegawai.getListPresensi().isEmpty()) {
                        list[i][0] = pegawai.getNamaUser();
                        list[i][1] = pegawai.getRole().getNamaRole();
                        list[i][2] = String.valueOf(pegawai.getKelasPengajar().size());

                        int terlambat = 0;
                        int absen = 0;
                        int hadir = 0;
                        for(PresensiModel presensi : pegawai.getListPresensi()) {
                            if(presensi.getStatus().equals(0)) {
                                terlambat++;
                            } else if(presensi.getStatus().equals(1)) {
                                hadir++;
                            }
                        }

                        PresensiModel awalPresensi = pegawai.getListPresensi().get(0);
                        PresensiModel latestPresensi = pegawai.getListPresensi().get(pegawai.getListPresensi().size()-1);
                        LocalDate newDate = awalPresensi.getDate().toLocalDateTime().toLocalDate();
                        LocalDate lateDate = latestPresensi.getDate().toLocalDateTime().toLocalDate();

                        List<CutiModel> listCuti = cutiService.getAllCutiByUser(pegawai.getIdUser());
                        List<LocalDate> holidays = new ArrayList<LocalDate>();
                        for(CutiModel objekCuti : listCuti) {
                            if(objekCuti.getStatus() !=0) {
                                LocalDate start = new java.sql.Date(objekCuti.getTanggal_mulai().getTime()).toLocalDate();
                                LocalDate end = new java.sql.Date(objekCuti.getTanggal_selesai().getTime()).toLocalDate();
                                holidays.addAll(getDatesBetween(start, end));
                            }
                        }
                        long hariPresensi = countBusinessDaysBetween(newDate, lateDate, holidays);
                        List<LocalDate> emptyList = new ArrayList<>();
                        Long hariCuti = 0L;
                        if(!holidays.isEmpty()) {
                            hariCuti = countBusinessDaysBetween(holidays.get(0), holidays.get(holidays.size()-1), emptyList);
                        }

                        absen = pegawai.getListPresensi().size() - ((int) hariPresensi) -  hariCuti.intValue();

                        list[i][3] = String.valueOf(hariPresensi);
                        list[i][4] = Integer.toString(terlambat);
                        list[i][5] = String.valueOf(hariCuti);
                        list[i][6] = Integer.toString(absen);
                        list[i][7] = Integer.toString(hadir);
                        i++;

                        totalCuti += hariCuti;
                        totalAbsen += absen;
                        totalPenuh += hadir;
                        totalTerlambat += terlambat;
                    }
                }
            }

            HashMap<String, String> objekCuti = new HashMap<String, String>();
            objekCuti.put("label", "cuti");
            objekCuti.put("value", Integer.toString(totalCuti));
            chart.add(objekCuti);
            HashMap<String, String> objekTerlambat = new HashMap<String, String>();
            objekTerlambat.put("label", "terlambat");
            objekTerlambat.put("value", Integer.toString(totalTerlambat));
            chart.add(objekTerlambat);
            HashMap<String, String> objekAbsen = new HashMap<String, String>();
            objekAbsen.put("label", "absen");
            objekAbsen.put("value", Integer.toString(totalAbsen));
            chart.add(objekAbsen);
            HashMap<String, String> objekPenuh = new HashMap<String, String>();
            objekPenuh.put("label", "penuh");
            objekPenuh.put("value", Integer.toString(totalPenuh));
            chart.add(objekPenuh);

            model.addAttribute("chart", chart);
            model.addAttribute("list", list);
            model.addAttribute("listCabang", cabangService.getCabangList());
            model.addAttribute("cabangModel", cabangModel);
            return "daftar-presensi-pengajar";
        } else {
            return "error/403";
        }
    }

    private static long countBusinessDaysBetween(LocalDate startDate, LocalDate endDate, List<LocalDate> holidays) {
        Predicate<LocalDate> isHoliday = date -> !holidays.isEmpty() ? holidays.contains(date) : false;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        endDate = endDate.plusDays(1);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        long businessDays = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween)
                .filter(isHoliday.or(isWeekend).negate()).count();
        return businessDays;
    }

    public static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {
        endDate = endDate.plusDays(1);
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}
