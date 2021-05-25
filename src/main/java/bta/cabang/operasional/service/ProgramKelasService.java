package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;

import java.util.Optional;

public interface ProgramKelasService {
    Optional<ProgramKelasModel> findProgramByProgramId(Long idProgram);
    ProgramKelasModel findProgramByNamaProgram(String namaProgram);
}
