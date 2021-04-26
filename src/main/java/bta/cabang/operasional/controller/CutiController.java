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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "cuti";
    }

    @GetMapping("/cuti/add")
    public String addCutiForm(Model model) {
        model.addAttribute("cuti", new CutiModel());

        return "form-addCuti";
    }

    @PostMapping("/cuti/add")
    public String addCutiSubmit(@ModelAttribute CutiModel cuti, RedirectAttributes redirectAttrs){
        try {
            cuti.setPengaju(userService.findByUsername("vina"));
            cutiService.addCuti(cuti);
//            model.addAttribute("alert", "addSuccess");
            redirectAttrs.addFlashAttribute("alert", "addSuccess");
            return "redirect:/cuti";
        } catch (Exception e) {
//            model.addAttribute("alert", "addFail");
            redirectAttrs.addFlashAttribute("alert", "addFail");
            return "redirect:/cuti";
        }

    }

    @GetMapping("/cuti/view/{id}")
    public String detailCuti(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        try {
            CutiModel cuti = cutiService.getCutiByIdCuti(id);
            model.addAttribute("cuti", cuti);

            return "view-cuti";
        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/cuti";
        }
    }

    @GetMapping("cuti/delete/{id}")
    private String deleteCuti(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            cutiService.deleteCuti(id);
            redirectAttrs.addFlashAttribute("alert", "delSuccess");
            return "redirect:/cuti";
        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/cuti";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "delFail");
            return "redirect:/cuti";
        }

    }
}
