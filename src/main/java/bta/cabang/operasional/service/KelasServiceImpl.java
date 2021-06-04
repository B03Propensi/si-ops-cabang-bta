package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;
import bta.cabang.operasional.repository.KelasDb;
import bta.cabang.operasional.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public KelasModel editKelas(Long idKelas, KelasModel kelasUpdate) throws Exception {
        KelasModel kelas = getKelas(idKelas);
        kelas.setNamaKelas(kelasUpdate.getNamaKelas());
        kelas.setBidang(kelasUpdate.getBidang());
        kelas.setCabang(kelasUpdate.getCabang());
        kelas.setHari(kelasUpdate.getHari());
        kelas.setWaktuMulai(kelasUpdate.getWaktuMulai());
        kelas.setWaktuSelesai(kelasUpdate.getWaktuSelesai());
        kelas.setPengajar(kelasUpdate.getPengajar());
        kelas.setWaktu(generateWaktu(kelasUpdate.getWaktuMulai()));
        return kelasDb.save(kelas);
    }

    @Override
    public KelasModel editJadwal(Long idKelas, KelasModel kelasUpdate) throws Exception {
        KelasModel kelas = getKelas(idKelas);
        kelas.setNamaKelas(kelasUpdate.getNamaKelas());
        kelas.setHari(kelasUpdate.getHari());
        kelas.setWaktuMulai(kelasUpdate.getWaktuMulai());
        kelas.setWaktuSelesai(kelasUpdate.getWaktuSelesai());
        kelas.setWaktu(generateWaktu(kelasUpdate.getWaktuMulai()));
        return kelasDb.save(kelas);
    }

    @Override
    public void addJadwal(KelasModel kelasUpdate) throws Exception {
        KelasModel kelas = getKelas(kelasUpdate.getIdKelas());
        kelas.setHari(kelasUpdate.getHari());
        kelas.setWaktuMulai(kelasUpdate.getWaktuMulai());
        kelas.setWaktuSelesai(kelasUpdate.getWaktuSelesai());
        kelas.setWaktu(generateWaktu(kelasUpdate.getWaktuMulai()));
        kelasDb.save(kelas);
    }

    @Override
    public void deleteKelas(Long idKelas) {
        kelasDb.deleteByIdKelas(idKelas);
    }

    @Override
    public KelasModel deleteJadwal(Long idKelas) {
        KelasModel kelas = getKelas(idKelas);
        kelas.setHari(null);
        kelas.setWaktu(null);
        kelas.setWaktuMulai(null);
        kelas.setWaktuSelesai(null);
        return kelasDb.save(kelas);
    }

    @Override
    public KelasModel deletePengajar(Long idKelas) {
        KelasModel kelas = getKelas(idKelas);
        kelas.setPengajar(null);
        return kelasDb.save(kelas);
    }

    @Override
    public List<UserModel> getAllPengajar() {
        return userDb.findAllByRole_IdRole(5L);
    }

    @Override
    public KelasModel editPengajar(Long idKelas, KelasModel kelasUpdate) {
        KelasModel kelas = getKelas(idKelas);
        kelas.setPengajar(kelasUpdate.getPengajar());
        return kelasDb.save(kelas);
    }

    @Override
    public List<List<KelasModel>> getRowKelas(List<List<KelasModel>> allKelas, String waktu) throws Exception {
        List<KelasModel> kelasSenin = new ArrayList<>();
        List<KelasModel> kelasSelasa = new ArrayList<>();
        List<KelasModel> kelasRabu = new ArrayList<>();
        List<KelasModel> kelasKamis = new ArrayList<>();
        List<KelasModel> kelasJumat = new ArrayList<>();
        List<KelasModel> kelasSabtu = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        List<KelasModel> kelasPerJam = kelasDb.findAllByWaktu(formatter.parse(waktu));
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
    public List<List<KelasModel>> getAllCells() throws Exception {
        List<List<KelasModel>> allKelas = new ArrayList<>();

        allKelas = getRowKelas(allKelas, "08:00");
        allKelas = getRowKelas(allKelas, "09:00");
        allKelas = getRowKelas(allKelas, "10:00");
        allKelas = getRowKelas(allKelas, "11:00");
        allKelas = getRowKelas(allKelas, "12:00");
        allKelas = getRowKelas(allKelas, "13:00");
        allKelas = getRowKelas(allKelas, "14:00");
        allKelas = getRowKelas(allKelas, "15:00");
        allKelas = getRowKelas(allKelas, "16:00");
        allKelas = getRowKelas(allKelas, "17:00");

        return allKelas;
    }

    @Override
    public List<Date> getListWaktu() throws Exception {
        List<Date> listWaktu = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        listWaktu.add(formatter.parse("08:00"));
        listWaktu.add(formatter.parse("09:00"));
        listWaktu.add(formatter.parse("10:00"));
        listWaktu.add(formatter.parse("11:00"));
        listWaktu.add(formatter.parse("12:00"));
        listWaktu.add(formatter.parse("13:00"));
        listWaktu.add(formatter.parse("14:00"));
        listWaktu.add(formatter.parse("15:00"));
        listWaktu.add(formatter.parse("16:00"));
        listWaktu.add(formatter.parse("17:00"));

        return listWaktu;
    }

    @Override
    public Date generateWaktu(Date waktuMulai) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        if (formatter.parse("17:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("17:00");
        } else if (formatter.parse("16:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("16:00");
        } else if (formatter.parse("15:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("15:00");
        } else if (formatter.parse("14:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("14:00");
        } else if (formatter.parse("13:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("13:00");
        } else if (formatter.parse("12:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("12:00");
        } else if (formatter.parse("11:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("11:00");
        } else if (formatter.parse("10:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("10:00");
        } else if (formatter.parse("09:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("09:00");
        } else if (formatter.parse("08:00").compareTo(waktuMulai) <= 0) {
            return formatter.parse("08:00");
        }
        return null;
    }
}
