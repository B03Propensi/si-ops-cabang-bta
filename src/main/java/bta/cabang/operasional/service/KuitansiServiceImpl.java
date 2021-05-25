package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class KuitansiServiceImpl implements KuitansiService {

    @Autowired
    KuitansiDb kuitansiDb;

    @Override
    public KuitansiModel findByKuitansiId(Long idKuitansi) {
        return kuitansiDb.findByKuitansiId(idKuitansi);
    }
}
