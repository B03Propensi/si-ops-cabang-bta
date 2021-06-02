package bta.cabang.operasional.service;

import bta.cabang.operasional.model.KuitansiModel;
import bta.cabang.operasional.repository.KuitansiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Qualifier
@Service
@Transactional
public class KuitansiServiceImpl implements KuitansiService {
    @Autowired
    KuitansiDb kuitansiDb;

    @Override
    public void addKuitansi(KuitansiModel kuitansi) {
        kuitansiDb.save(kuitansi);
    }

    @Override
    public KuitansiModel getKuitansiByIdSiswa(Long idSiswa) {
        return kuitansiDb.findKuitansiBySiswaKuitansi_IdSiswa(idSiswa).get();
    }
}
