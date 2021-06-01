package bta.cabang.operasional.controller;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.repository.UserDb;
import bta.cabang.operasional.security.AuthService;
import bta.cabang.operasional.service.CabangService;
import bta.cabang.operasional.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/add")
    private String addCabangForm(Model model){
        model.addAttribute("cabang", new CabangModel());
        return "add-cabang-form";
    }
    @PostMapping("/add")
    private String addCabangSubmit(@ModelAttribute CabangModel cabang, @RequestParam(value= "attachment") MultipartFile file, RedirectAttributes redirectAttrs){
        try{
            if (file.isEmpty()) {
                cabang.setLogo(null);
            } else {
                byte[] logo = file.getBytes();
                cabang.setLogo(logo);
            }
            cabangService.addCabang(cabang);
            redirectAttrs.addFlashAttribute("alert", "addSuccess");
            return "redirect:/cabang";
        }catch (Exception e){
            redirectAttrs.addFlashAttribute("alert", "addFail");
            return "redirect:/cabang";
        }
    }
    @GetMapping("/update/{id_cabang}")
    private String updateCabangForm(@PathVariable Long id_cabang, Model model){

        CabangModel cabang = cabangService.getCabangbyId(id_cabang);
        model.addAttribute("cabang", cabang );
        return "update-cabang-form";
    }

    @PostMapping("/update")
    private String updateCabangSubmit(@RequestParam Long id_cabang, @ModelAttribute CabangModel cabangModel, Model model, RedirectAttributes redirectAttrs){
        try{
            cabangService.ubahCabang(id_cabang,cabangModel);
            redirectAttrs.addFlashAttribute("alert", "updateSuccess");
            return "redirect:/cabang";
        }catch (Exception e){
            redirectAttrs.addFlashAttribute("alert", "updateFail");
            return "redirect:/cabang";
        }
    }

    @GetMapping("/delete/{id_cabang}")
    private String deleteCabang(@PathVariable Long id_cabang, Model model, RedirectAttributes redirectAttrs){
        try {
            cabangService.deleteCabang(cabangService.getCabangbyId(id_cabang));
            redirectAttrs.addFlashAttribute("alert", "delSuccess");
            return "redirect:/cabang";

        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/cabang";

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("alert", "delFail");
            return "redirect:/cabang";
        }

    }
    @GetMapping("/view/{id_cabang}")
    private String detailCabang(@PathVariable Long id_cabang, Model model, RedirectAttributes redirectAttrs){
        try {
            CabangModel cabangModel = cabangService.getCabangbyId(id_cabang);
            String base64EncodedImage = Base64.encodeBase64String(cabangModel.getLogo());

            model.addAttribute("cabang",cabangModel);
            model.addAttribute("image", base64EncodedImage);

            return "view-cabang";
        }catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("alert", "notFound");
            return "redirect:/cabang";
        }
    }

}
