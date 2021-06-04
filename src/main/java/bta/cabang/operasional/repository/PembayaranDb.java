package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PembayaranDb extends JpaRepository<PembayaranModel, Long> {
    PembayaranModel findPembayaranByIdPembayaran(Long idPembayaran);
    PembayaranModel findPembayaranByNamaPembayaran(String namaPembayaran);
}