package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;

import java.util.List;

public interface KelasService {
    KelasModel getKelas(Long idKelas);
    KelasModel getKelasByNamaKelas(String namaKelas);
    List<KelasModel> getAllKelas();
    void addKelas(KelasModel kelas);
    KelasModel editKelas(Long idKelas, KelasModel kelas);
    void deleteKelas(Long idKelas);
    List<UserModel> getAllPengajar(Long idRolePengajar);
    KelasModel editPengajar(Long idKelas, KelasModel kelasUpdate);
    List<List<KelasModel>> getRowKelas(List<List<KelasModel>> allKelas, String waktu);
    List<List<KelasModel>> getAllCells();
}
