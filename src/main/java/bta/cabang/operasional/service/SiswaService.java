package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;

import java.sql.Time;
import java.util.List;

public interface SiswaService {
    void addSiswa(SiswaModel siswaModel);

    void deleteSiswa(SiswaModel siswaModel);

    SiswaModel ubahSiswa(Long idSiswa, SiswaModel siswa);

    List<SiswaModel> getAllSiswa();
    SiswaModel getSiswaById(Long idSiswa);
    SiswaModel getSiswaByNamaSiswa(String namaSiswa);
    List<InvoiceModel> getAllInvoice(Long idInvoice);
    List<KuitansiModel> getAllKuitansi(Long idKuitansi);
    List<ProgramKelasModel> getAllProgram(Long idProgram);
    List<StatusPembayaranModel> getAllStatusPembayaran(Long idStatus);

}
