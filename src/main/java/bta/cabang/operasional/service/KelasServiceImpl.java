package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.repository.KelasDb;
import bta.cabang.operasional.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class KelasServiceImpl implements KelasService {
    @Autowired
    KelasDb kelasDb;

    @Autowired
    UserDb userDb;

    @Override
    public KelasModel getKelas(Long id) {
        return kelasDb.findByIdKelas(id).get();
    }

    @Override
    public List<KelasModel> getAllKelas() {
        return kelasDb.findAll();
    }

    @Override
    public void addKelas(KelasModel kelas) {
        kelasDb.save(kelas);
    }

    @Override
    public KelasModel editKelas(Long idKelas, KelasModel kelasUpdate) {
        KelasModel kelas = getKelas(idKelas);
        kelas.setNamaKelas(kelasUpdate.getNamaKelas());
        kelas.setBidang(kelasUpdate.getBidang());
        kelas.setCabang(kelasUpdate.getCabang());
        kelas.setHari(kelasUpdate.getHari());
        kelas.setWaktu(kelasUpdate.getWaktu());
        return kelasDb.save(kelas);
    }

    @Override
    public void deleteKelas(Long id) {
        kelasDb.deleteById(id);
    }

    @Override
    public List<UserModel> getAllPengajar(Long idRolePengajar) {
        return userDb.findAllByRole_IdRole(idRolePengajar);
    }

    @Override
    public KelasModel editPengajar(Long idKelas, KelasModel kelasUpdate) {
        KelasModel kelas = getKelas(idKelas);
        kelas.setPengajar(kelasUpdate.getPengajar());
        return kelasDb.save(kelas);
    }
}
