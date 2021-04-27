package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.repository.UserDb;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cabang")
public class CabangController {
    @Autowired
    private CabangService cabangService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("")
    private String viewallcabang (Model model){
        List<CabangModel> cabangList = cabangService.getCabangList();
        model.addAttribute("cabangList", cabangList);
        return "view-all-cabang";
    }




}
