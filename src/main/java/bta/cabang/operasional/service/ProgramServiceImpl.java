package bta.cabang.operasional.service;

import bta.cabang.operasional.model.ProgramModel;
import bta.cabang.operasional.repository.ProgramDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    ProgramDb programDb;

    @Override
    public List<ProgramModel> getAllProgram() { return programDb.findAll(); }
}
