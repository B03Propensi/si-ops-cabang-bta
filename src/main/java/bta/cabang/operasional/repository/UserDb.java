package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {
    List<UserModel> findAllByRole_IdRole(Long idRole);
}
