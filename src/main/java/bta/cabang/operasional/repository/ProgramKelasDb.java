package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.ProgramKelasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramKelasDb extends JpaRepository<ProgramKelasModel, Long> {
    Optional<ProgramKelasModel> findProgramByProgramId(Long idProgram);

    ProgramKelasModel findProgramByNamaProgram(String namaProgram);
}
