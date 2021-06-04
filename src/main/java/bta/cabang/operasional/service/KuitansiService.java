package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KuitansiModel;

public interface KuitansiService {
    void addKuitansi(KuitansiModel kuitansi);

    KuitansiModel getKuitansiByIdSiswa(Long idSiswa);

}
