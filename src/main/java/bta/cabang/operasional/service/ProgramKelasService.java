package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;

import java.util.List;

public interface ProgramKelasService {
    ProgramKelasModel findProgramByIdProgram (Long idProgram);
    ProgramKelasModel findProgramByNamaProgram(String namaProgram);
    List<ProgramKelasModel> getAllProgram();
}
