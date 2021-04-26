package bta.cabang.operasional.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/jadwal")
    public String jadwal() {
        return "jadwal";
    }

    @RequestMapping("/presensi")
    public String presensi() {
        return "presensi";
    }

    @RequestMapping("/daftar-presensi")
    public String daftarPresensi(){
        return "daftar-presensi";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
