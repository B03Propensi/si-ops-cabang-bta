package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.repository.CutiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class CutiServiceImpl implements CutiService{
    @Autowired
    CutiDb cutiDb;

    @Override
    public CutiModel getCutiByIdCuti(Long id) { return cutiDb.findByIdCuti(id).get(); }

    @Override
    public List<CutiModel> getAllCuti() { return cutiDb.findAll(); }

    @Override
    public List<CutiModel> getAllCutiByUser(Long idUser) { return cutiDb.findAllByPengaju_IdUser(idUser); }

    @Override
    public List<CutiModel> getAllCutiByStatus(Integer status) { return cutiDb.findAllByStatus(status); }

    @Override
    public void addCuti(CutiModel cuti){ cutiDb.save(cuti); }

    @Override
    public void updateCuti(CutiModel cuti){ cutiDb.save(cuti); }

    @Override
    public void deleteCuti(Long id) {  cutiDb.deleteById(id); }
}
