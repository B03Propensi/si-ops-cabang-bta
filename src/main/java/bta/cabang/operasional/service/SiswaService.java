package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;

import java.util.List;
import java.util.Optional;

public interface SiswaService {
    void addSiswa(SiswaModel siswaModel);

    void deleteSiswa(SiswaModel siswaModel);

    SiswaModel ubahSiswa(Long idSiswa, SiswaModel siswa);

    List<SiswaModel> getAllSiswa();
    Optional<SiswaModel> getSiswaById(Long idSiswa);
    Optional<SiswaModel> getSiswaByNamaSiswa(String namaSiswa);
    List<InvoiceModel> getAllInvoice(Long idInvoice);
    List<KuitansiModel> getAllKuitansi(Long idKuitansi);
    List<ProgramKelasModel> getAllProgram(Long idProgram);
    List<StatusPembayaranModel> getAllStatusPembayaran(Long idStatus);

}


