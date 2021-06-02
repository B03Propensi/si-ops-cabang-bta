package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramDb extends JpaRepository<ProgramModel, Long>{
    RoleModel findProgramByIdProgram(Long idProgram);
    RoleModel findProgramByNamaProgram(String namaProgram);
}
