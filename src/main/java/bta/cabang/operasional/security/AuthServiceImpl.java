package bta.cabang.operasional.security;

import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@Service
//@Transactional
//public class AuthServiceImpl implements AuthService {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserModel getCurrentLoggedInUserByUsername() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = auth.getName();
//        return userService.findByUsername(currentUsername);
//    }
//}
