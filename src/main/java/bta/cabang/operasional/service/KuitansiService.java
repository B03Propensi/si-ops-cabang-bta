package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;

import java.util.List;

public interface KuitansiService {
    KuitansiModel getKuitansi(Long idKuitansi);
    List<KuitansiModel> getAllKuitansi();
}
