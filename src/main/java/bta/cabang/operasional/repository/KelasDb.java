package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.KelasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KelasDb extends JpaRepository<KelasModel, Long> {
    Optional<KelasModel> findByIdKelas(Long id);
}
