package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KuitansiDb extends JpaRepository<KuitansiModel, Long> {
    Optional<KuitansiModel> findKuitansiByIdKuitansi(Long idKuitansi);
    Optional<KuitansiModel> findKuitansiBySiswaKuitansi_IdSiswa(Long idSiswa);
    void deleteByIdKuitansi(Long idKuitansi);
}