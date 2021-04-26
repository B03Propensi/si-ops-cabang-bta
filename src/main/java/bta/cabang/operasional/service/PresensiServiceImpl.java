package bta.cabang.operasional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import bta.cabang.operasional.model.PresensiModel;
import bta.cabang.operasional.repository.PresensiDb;
import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class PresensiServiceImpl implements PresensiService {
    @Autowired
    PresensiDb db;

    public List<PresensiModel> getAllPresensi(){
        return db.findAll();
    }
}