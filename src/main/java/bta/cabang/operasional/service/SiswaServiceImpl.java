package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.SiswaModel;
import bta.cabang.operasional.repository.SiswaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class SiswaServiceImpl implements SiswaService{
    @Autowired
    SiswaDb siswaDb;

    @Override
    public void addSiswa(SiswaModel siswa) { siswaDb.save(siswa); }

    @Override
    public List<SiswaModel> getAllSiswa() {
        return siswaDb.findAll();
    }

    @Override
    public SiswaModel getSiswa(Long idSiswa) {
        return siswaDb.findSiswaByIdSiswa(idSiswa).get();
    }

    @Override
    public List<SiswaModel> getAllSiswaByPembayaran(Integer statusPembayaran) {
        return siswaDb.findAllSiswaByStatusPembayaran(statusPembayaran); }
}
