package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusPembayaranServiceImpl implements StatusPembayaranService {

    @Autowired
    StatusPembayaranDb statusPembayaranDb;

    @Override
    public StatusPembayaranModel findPembayaranByIdPembayaran(Long idPembayaran) {
        return statusPembayaranDb.findPembayaranByIdPembayaran(idPembayaran);
    }
}
