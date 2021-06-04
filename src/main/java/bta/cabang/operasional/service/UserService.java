package bta.cabang.operasional.service;

import bta.cabang.operasional.model.RoleModel;
import bta.cabang.operasional.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel findByUsername(String username);
    List<UserModel> findObjekPresensi();

}
