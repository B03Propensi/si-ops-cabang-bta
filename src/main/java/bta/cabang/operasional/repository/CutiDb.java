package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.CutiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CutiDb extends JpaRepository<CutiModel, Long> {
    Optional<CutiModel> findByIdCuti(Long id);
    List<CutiModel> findAllByPengaju_IdUser(Long idUser);
    List<CutiModel> findAllByStatus(Integer status);

}
