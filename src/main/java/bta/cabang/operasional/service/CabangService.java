package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;

import java.util.List;

public interface CabangService {
    void addCabang(CabangModel cabangModel);

    void deleteCabang(CabangModel cabangModel);

    CabangModel ubahCabang(Long id_cabang, CabangModel cabangModel);

    List<CabangModel> getCabangList();

    CabangModel getCabangbyId(Long id_cabang);
}
