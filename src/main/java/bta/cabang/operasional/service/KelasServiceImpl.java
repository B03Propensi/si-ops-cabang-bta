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

    @Override
    public List<List<String>> getRowKelas(List<List<String>> allKelas, String waktu) {
        List<String> kelasSenin = new ArrayList<>();
        List<String> kelasSelasa = new ArrayList<>();
        List<String> kelasRabu = new ArrayList<>();
        List<String> kelasKamis = new ArrayList<>();
        List<String> kelasJumat = new ArrayList<>();
        List<String> kelasSabtu = new ArrayList<>();

        List<KelasModel> kelasPerJam = kelasDb.findAllByWaktu(java.sql.Time.valueOf(waktu));
        for (KelasModel kelas : kelasPerJam) {
            if (kelas.getHari().equals("Senin")) {
                kelasSenin.add(kelas.getNamaKelas());
            } else if (kelas.getHari().equals("Selasa")) {
                kelasSelasa.add(kelas.getNamaKelas());
            } else if (kelas.getHari().equals("Rabu")) {
                kelasRabu.add(kelas.getNamaKelas());
            } else if (kelas.getHari().equals("Kamis")) {
                kelasKamis.add(kelas.getNamaKelas());
            } else if (kelas.getHari().equals("Jumat")) {
                kelasJumat.add(kelas.getNamaKelas());
            } else if (kelas.getHari().equals("Sabtu")) {
                kelasSabtu.add(kelas.getNamaKelas());
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
    public List<List<String>> getAllCells() {
        List<List<String>> allKelas = new ArrayList<>();

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
