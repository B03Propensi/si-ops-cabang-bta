package bta.cabang.operasional.service;

import bta.cabang.operasional.model.CabangModel;
import bta.cabang.operasional.model.PresensiModel;
import bta.cabang.operasional.repository.PresensiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Qualifier
@Service
@Transactional
public class PresensiServiceImpl implements PresensiService {
    @Autowired
    PresensiDb presensiDb;

    @Override
    public void addPresensi(PresensiModel presensiModel) {
        presensiDb.save(presensiModel);
    }

    @Override
    public void deletePresensi(PresensiModel presensiModel) {
        presensiDb.delete(presensiModel);
    }

    @Override
    public PresensiModel updatePresensi(Integer idPresensi, PresensiModel presensiModel) {
        PresensiModel targetpresensi = presensiDb.findById(idPresensi).get();
        try {
            targetpresensi.setAlasan(presensiModel.getAlasan());
            presensiDb.save(targetpresensi);
            return targetpresensi;
        }
        catch (NullPointerException nullPointerException){
            return null;
        }
    }

    @Override
    public List<PresensiModel> getPresensiList() {
       return presensiDb.findAll();
    }

    @Override
    public PresensiModel getPresensibyId(Integer id_presensi) {
        return presensiDb.findById(id_presensi).get();
//       return presensiDb.findById(Long.valueOf(id_presensi)).get();
    }

    @Override
    public List<PresensiModel> getAllPresensiByUser(Long idUser) {
        return presensiDb.findAllByUser_IdUser(idUser);
    }
}
