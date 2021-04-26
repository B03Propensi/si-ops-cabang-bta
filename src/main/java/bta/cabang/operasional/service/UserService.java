package bta.cabang.operasional.service;

import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.model.RoleModel;
import java.util.List;

public interface UserService {
    UserModel findByUsername(String username);
    UserModel addUser(UserModel user);
    String encrypt(String password);
    RoleModel getRole(String namaRole);
    List<RoleModel> getAllRole();
    RoleModel addRole(RoleModel role);
}
