package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresensiDb extends JpaRepository<PresensiModel, Long> {
    Optional<PresensiModel> findById(Long id);
    List<PresensiModel> findAllByUser(UserModel user);
    List<PresensiModel> findByLokasi(CabangModel lokasi);
    List<PresensiModel> findByUserAndLokasi(UserModel user, CabangModel lokasi);
}
