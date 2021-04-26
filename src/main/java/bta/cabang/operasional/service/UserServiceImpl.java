package bta.cabang.operasional.service;

import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel findByUsername(String username) {
        return userDb.findByUsername(username);
    }
}
