package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    RoleModel findByNamaRole(String namaRole);
    RoleModel findByIdRole(Integer id);
}
