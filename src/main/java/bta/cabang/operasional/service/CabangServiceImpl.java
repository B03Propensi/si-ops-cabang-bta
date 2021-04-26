package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;

@Service
@Transactional

public class CabangServiceImpl implements CabangService{
    @Autowired
    CabangDb cabangDb;

    @Override
    public void addCabang(CabangModel cabangModel) {
        cabangDb.save(cabangModel);
    }

    @Override
    public void deleteCabang(CabangModel cabangModel) {
        cabangDb.delete(cabangModel);

    }

    @Override
    public CabangModel ubahCabang(Long id_cabang, CabangModel cabangUpdate) {
        CabangModel targetCabang = cabangDb.findById(id_cabang).get();
        try {
            targetCabang.setAlamat(cabangUpdate.getAlamat());
            targetCabang.setKode_cabang(cabangUpdate.getKode_cabang());
            targetCabang.setNama_cabang(cabangUpdate.getNama_cabang());
            cabangDb.save(targetCabang);
            return targetCabang;
        }
        catch (NullPointerException nullPointerException){
            return null;
        }
    }
    @Override
    public List<CabangModel> getCabangList() {
        return cabangDb.findAll();
    }

    @Override
    public CabangModel getCabangbyId(Long id_cabang) {
        return cabangDb.findById(id_cabang).get();
    }
}
