package bta.cabang.operasional.security;

import bta.cabang.operasional.model.UserModel;

public interface AuthService {
    UserModel getCurrentLoggedInUserByUsername();
}
