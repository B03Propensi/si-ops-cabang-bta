package bta.cabang.operasional.service;

import bta.cabang.operasional.model.UserModel;

public interface UserService {
    UserModel findByUsername(String username);
}
