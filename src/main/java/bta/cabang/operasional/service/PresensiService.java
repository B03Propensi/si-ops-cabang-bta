package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.CutiModel;
import bta.cabang.operasional.model.PresensiModel;

import java.util.List;

public interface PresensiService {
    void addPresensi(PresensiModel presensiModel);

    void deletePresensi(PresensiModel presensiModel) ;

    PresensiModel updatePresensi(Integer id_presensi, PresensiModel presensiModel);

    List<PresensiModel> getPresensiList();
    List<PresensiModel> getAllPresensiByUser(Long idUser);

    PresensiModel getPresensibyId(Integer id_presensi);
}
