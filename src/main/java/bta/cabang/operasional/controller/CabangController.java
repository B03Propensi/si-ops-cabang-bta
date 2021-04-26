package bta.cabang.operasional.controller;

import bta.cabang.operasional.repository.UserDb;
import bta.cabang.operasional.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cabang")
public class CabangController {
    @Autowired
    private CabangService cabangService;

    @Autowired
    private  UserDb userDb;



}
