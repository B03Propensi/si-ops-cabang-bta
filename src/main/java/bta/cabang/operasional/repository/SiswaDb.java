package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiswaDb extends JpaRepository<SiswaModel, Long>{
    Optional<SiswaModel> findSiswaByIdSiswa(Long idSiswa);
    Optional<SiswaModel> findSiswaByNamaSiswa(String namaSiswa);
    List<SiswaModel> findSiswaByInvoice(InvoiceModel invoice);
    List<SiswaModel> findSiswaByKuitansi(KuitansiModel kuitansi);
    List<SiswaModel> findAllSiswaByPembayaran(PembayaranModel pembayaran);
    List<SiswaModel> findAllSiswaByCabangSiswa(CabangModel cabangSiswa);
    List<SiswaModel> findAllSiswaByProgram(ProgramModel program);
    void deleteByIdSiswa(Long idSiswa);
}
