package bta.cabang.operasional.service;

import bta.cabang.operasional.model.RoleModel;
import java.util.List;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel getRoleById(Integer id);
}