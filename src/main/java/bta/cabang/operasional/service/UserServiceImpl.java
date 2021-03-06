package bta.cabang.operasional.service;

import bta.cabang.operasional.model.RoleModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.repository.RoleDb;
import bta.cabang.operasional.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public List<UserModel> findObjekPresensi(int i) {
        List<UserModel> list = new ArrayList<>();
        if(i == 1) {
            list.addAll(userDb.findAllByRole_IdRole(3L));
            list.addAll(userDb.findAllByRole_IdRole(4L));
            list.addAll(userDb.findAllByRole_IdRole(5L));
        } else if(i == 2) {
            list.addAll(userDb.findAllByRole_IdRole(5L));
        }
        return list;
    }
}
