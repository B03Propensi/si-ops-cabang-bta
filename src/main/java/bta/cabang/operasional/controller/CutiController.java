package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.service.CutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CutiController {
    @Autowired
    private CutiService cutiService;

    @GetMapping("/cuti")
    public String viewAllCuti(Model model) {
//        UserModel currentUser = user;
//        int uRole = currentUser.role; Long uId = currentUser.id;
//        List<CutiModel> listCuti = new ArrayList<>();
//        if (uRole == 1){       // DirOp
//            listCuti = cutiService.getAllCuti();
//        }
//        else {                      // Pegawai
//            listCuti = cutiService.getAllCutiByUser(uId);
//        }
//
//        model.addAttribute("listCuti", listCuti);
//        model.addAttribute("isAbleToAddCuti", uRole == 2);
//        model.addAttribute("alert", null);

        return "viewAll-cuti";
    }

    @GetMapping("/cuti/add")
    public String addCutiForm(Model model) {
        model.addAttribute("cuti", new CutiModel());

        return "form-addCuti";
    }

    @PostMapping("/cuti/add")
    public String addCutiSubmit(@ModelAttribute CutiModel cuti, Model model){
        try {
            cutiService.addCuti(cuti);
            model.addAttribute("alert", "addSuccess");
            return "viewAll-cuti";
        } catch (Exception e) {
            model.addAttribute("alert", "addFail");
            return "viewAll-cuti";
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
            return "viewAll-cuti";
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
            return "viewAll-cuti";
        } catch (EmptyResultDataAccessException e) {
            model.addAttribute("alert", "notFound");
            return "viewAll-cuti";
        } catch (Exception e) {
            model.addAttribute("alert", "delFail");
            return "viewAll-cuti";
        }

    }
}
