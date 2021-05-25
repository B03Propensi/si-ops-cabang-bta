package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Qualifier
@Service
@Transactional
public class ProgramKelasServiceImpl implements ProgramKelasService{

    @Autowired
    ProgramKelasDb programKelasDb;

    @Override
    public Optional<ProgramKelasModel> findProgramByProgramId(Long idProgram) {
        return programKelasDb.findProgramByProgramId(idProgram);
    }

    @Override
    public ProgramKelasModel findProgramByNamaProgram(String namaProgram) {
        return programKelasDb.findProgramByNamaProgram(namaProgram);
    }
}
