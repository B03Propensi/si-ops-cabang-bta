package bta.cabang.operasional.service;

import bta.cabang.operasional.model.RoleModel;

import java.util.List;

public interface RoleService {
    void addRole(RoleModel role);
    List<RoleModel> findAll();
    RoleModel getRole(String namaRole);
}
