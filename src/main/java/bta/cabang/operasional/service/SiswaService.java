package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KelasModel;
import bta.cabang.operasional.model.SiswaModel;

import java.util.List;

public interface SiswaService {
    void addSiswa(SiswaModel siswa);
    List<SiswaModel> getAllSiswa();
    SiswaModel getSiswa(Long idSiswa);
}
