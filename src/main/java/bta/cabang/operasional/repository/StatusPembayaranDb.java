package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPembayaranDb extends JpaRepository<StatusPembayaranModel, Long> {
    StatusPembayaranModel findByNamaStatus(String namaStatus);
    StatusPembayaranModel findPembayaranByIdPembayaran(Long idPembayaran);
}
