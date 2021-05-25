package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiswaDb extends JpaRepository<SiswaModel, Long> {
    Optional<SiswaModel> getSiswaById(Long idSiswa);
    Optional<SiswaModel> getSiswaByNamaSiswa(String namaSiswa);
    List<InvoiceModel> findAllByInvoiceSiswa(Long idInvoice);
    List<KuitansiModel> findAllByKuitansi(Long idKuitansi);
    List<ProgramKelasModel> findAllByProgram(Long idProgram);
    List<StatusPembayaranModel> findAllByPembayaran(Long idPembayaran);

}