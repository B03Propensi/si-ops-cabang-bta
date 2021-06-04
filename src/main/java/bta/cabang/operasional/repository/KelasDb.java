package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.KelasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface KelasDb extends JpaRepository<KelasModel, Long> {
    Optional<KelasModel> findByIdKelas(Long id);
    Optional<KelasModel> findByNamaKelas(String namaKelas);
    List<KelasModel> findAllByWaktu(Date waktu);
    void deleteByIdKelas(Long idKelas);
}
