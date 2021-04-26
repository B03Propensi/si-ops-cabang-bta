package bta.cabang.operasional.service;

import bta.cabang.operasional.model.RoleModel;
import bta.cabang.operasional.repository.RoleDb;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

    @Override
    public RoleModel getRoleById(Integer id) {
        return roleDb.findByIdRole(id);
    }
}