package bta.cabang.operasional.service;

import bta.cabang.operasional.model.ProgramModel;
import bta.cabang.operasional.model.SiswaModel;

import java.util.List;

public interface ProgramService {
    List<ProgramModel> getAllProgram();
    void addProgram(ProgramModel program);
}
