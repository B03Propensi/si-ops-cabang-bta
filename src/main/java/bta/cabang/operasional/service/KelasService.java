package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;

import java.sql.Time;
import java.util.List;

public interface KelasService {
    KelasModel getKelas(Long idKelas);
    KelasModel getKelasByNamaKelas(String namaKelas);
    List<KelasModel> getAllKelas();
    void addKelas(KelasModel kelas);
    void addJadwal(KelasModel kelas);
    KelasModel editKelas(Long idKelas, KelasModel kelas);
    void deleteKelas(Long idKelas);
    List<UserModel> getAllPengajar();
    KelasModel editPengajar(Long idKelas, KelasModel kelasUpdate);
    List<List<KelasModel>> getRowKelas(List<List<KelasModel>> allKelas, String waktu);
    List<List<KelasModel>> getAllCells();
    List<Time> getListWaktu();
    Time generateWaktu(Time waktuMulai);

}
