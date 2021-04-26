package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.PresensiModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.PresensiService;
import bta.cabang.operasional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PresensiController {
    @Autowired
    PresensiService service;

    @Autowired
    UserService user;

    @GetMapping(value = "/daftar-presensi")
    public String viewAllPresensi(Model model){
        List<PresensiModel> list = service.getAllPresensi();
        model.addAttribute("list", list);
        return "daftar-presensi";
    }
}