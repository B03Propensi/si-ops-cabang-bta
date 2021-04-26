package bta.cabang.operasional.service;

import bta.cabang.operasional.model.RoleModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.repository.UserDb;
import bta.cabang.operasional.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Autowired
    private RoleDb roleDb;

    @Override
    public UserModel findByUsername(String username) {
        return userDb.findByUsername(username);
    }

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
    public RoleModel addRole(RoleModel role) {
        return roleDb.save(role);
    }

    @Override
    public List<RoleModel> getAllRole() {
        return roleDb.findAll();
    }

    @Override
    public RoleModel getRole(String namaRole) {
        return roleDb.findByNamaRole(namaRole);
    }
}
