package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface KelasService {
    KelasModel getKelas(Long idKelas);
    KelasModel getKelasByNamaKelas(String namaKelas);
    List<KelasModel> getAllKelas();
    void addKelas(KelasModel kelas);
    void addJadwal(KelasModel kelas) throws Exception;
    KelasModel editJadwal(Long idKelas, KelasModel kelasUpdate) throws Exception;
    KelasModel editKelas(Long idKelas, KelasModel kelas) throws Exception;
    void deleteKelas(Long idKelas);
    KelasModel deleteJadwal(Long idKelas);
    KelasModel deletePengajar(Long idKelas);
    List<UserModel> getAllPengajar();
    KelasModel editPengajar(Long idKelas, KelasModel kelasUpdate);
    List<List<KelasModel>> getRowKelas(List<List<KelasModel>> allKelas, String waktu) throws Exception;
    List<List<KelasModel>> getAllCells() throws Exception;
    List<Date> getListWaktu() throws Exception;
    Date generateWaktu(Date waktuMulai) throws Exception;

}
