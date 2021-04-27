package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.repository.KelasDb;
import bta.cabang.operasional.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public KelasModel getKelas(Long idKelas) {
        return kelasDb.findByIdKelas(idKelas).get();
    }

    @Override
    public KelasModel getKelasByNamaKelas(String namaKelas) {
        return kelasDb.findByNamaKelas(namaKelas).get();
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

    @Override
    public List<List<KelasModel>> getRowKelas(List<List<KelasModel>> allKelas, String waktu) {
        List<KelasModel> kelasSenin = new ArrayList<>();
        List<KelasModel> kelasSelasa = new ArrayList<>();
        List<KelasModel> kelasRabu = new ArrayList<>();
        List<KelasModel> kelasKamis = new ArrayList<>();
        List<KelasModel> kelasJumat = new ArrayList<>();
        List<KelasModel> kelasSabtu = new ArrayList<>();

        List<KelasModel> kelasPerJam = kelasDb.findAllByWaktu(java.sql.Time.valueOf(waktu));
        for (KelasModel kelas : kelasPerJam) {
            if (kelas.getHari().equals("Senin")) {
                kelasSenin.add(kelas);
            } else if (kelas.getHari().equals("Selasa")) {
                kelasSelasa.add(kelas);
            } else if (kelas.getHari().equals("Rabu")) {
                kelasRabu.add(kelas);
            } else if (kelas.getHari().equals("Kamis")) {
                kelasKamis.add(kelas);
            } else if (kelas.getHari().equals("Jumat")) {
                kelasJumat.add(kelas);
            } else if (kelas.getHari().equals("Sabtu")) {
                kelasSabtu.add(kelas);
            }
        }

        allKelas.add(kelasSenin);
        allKelas.add(kelasSelasa);
        allKelas.add(kelasRabu);
        allKelas.add(kelasKamis);
        allKelas.add(kelasJumat);
        allKelas.add(kelasSabtu);

        return allKelas;
    }

    @Override
    public List<List<KelasModel>> getAllCells() {
        List<List<KelasModel>> allKelas = new ArrayList<>();

        allKelas = getRowKelas(allKelas, "08:00:00");
        allKelas = getRowKelas(allKelas, "09:00:00");
        allKelas = getRowKelas(allKelas, "10:00:00");
        allKelas = getRowKelas(allKelas, "11:00:00");
        allKelas = getRowKelas(allKelas, "12:00:00");
        allKelas = getRowKelas(allKelas, "13:00:00");
        allKelas = getRowKelas(allKelas, "14:00:00");
        allKelas = getRowKelas(allKelas, "15:00:00");
        allKelas = getRowKelas(allKelas, "16:00:00");
        allKelas = getRowKelas(allKelas, "17:00:00");

        return allKelas;
    }
}
