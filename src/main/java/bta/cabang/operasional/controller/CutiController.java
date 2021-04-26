package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.CutiService;
import bta.cabang.operasional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CutiController {
    @Autowired
    private CutiService cutiService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/cuti")
    public String viewAllCuti(Model model) {
//        UserModel currentUser = authService.getCurrentLoggedInUserByUsername();
//        Long role = currentUser.getRole().getIdRole();
//        Long id = currentUser.getIdUser();
//
//        List<CutiModel> listCuti = new ArrayList<>();
//        if (role == 1 || role == 2) {
//            listCuti = cutiService.getAllCuti();
//        } else {
//            listCuti = cutiService.getAllCutiByUser(id);
//        }

        List<CutiModel> listCuti = cutiService.getAllCuti();

        model.addAttribute("listCuti", listCuti);
//        model.addAttribute("isAbleToAddCuti", role == 3 || role == 4 || role ==5);
        model.addAttribute("alert", null);

        return "cuti";
    }

    @GetMapping("/cuti/add")
    public String addCutiForm(Model model) {
        model.addAttribute("cuti", new CutiModel());

        return "form-addCuti";
    }

    @PostMapping("/cuti/add")
    public String addCutiSubmit(@ModelAttribute CutiModel cuti, Model model){
        try {
            cuti.setPengaju(userService.findByUsername("vina"));
            cutiService.addCuti(cuti);
            model.addAttribute("alert", "addSuccess");
            return "cuti";
        } catch (Exception e) {
            model.addAttribute("alert", "addFail");
            return "cuti";
        }

    }

    @GetMapping("/cuti/view/{id}")
    public String detailCuti(@PathVariable Long id, Model model) {
        try {
            CutiModel cuti = cutiService.getCutiByIdCuti(id);
            model.addAttribute("cuti", cuti);

            return "view-cuti";
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("alert", "notFound");
            return "cuti";
        }
    }

    @GetMapping("gaji/delete/{id}")
    private String deleteCuti(
            @PathVariable Long id,
            Model model
    ) {
        try {
            cutiService.deleteCuti(id);
            model.addAttribute("alert", "delSuccess");
            return "cuti";
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("alert", "notFound");
            return "cuti";
        } catch (Exception e) {
            model.addAttribute("alert", "delFail");
            return "cuti";
        }

    }
}
