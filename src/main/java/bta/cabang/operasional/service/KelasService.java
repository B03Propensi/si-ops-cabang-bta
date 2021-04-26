package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.UserModel;

import java.util.List;

public interface KelasService {
    KelasModel getKelas(Long idKelas);
    List<KelasModel> getAllKelas();
    void addKelas(KelasModel kelas);
    KelasModel editKelas(Long idKelas, KelasModel kelas);
    void deleteKelas(Long idKelas);
    List<UserModel> getAllPengajar(Long idRolePengajar);
    KelasModel editPengajar(Long idKelas, KelasModel kelasUpdate);
}
