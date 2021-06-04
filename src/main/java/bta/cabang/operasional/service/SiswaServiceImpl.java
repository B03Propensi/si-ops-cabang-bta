package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.repository.InvoiceDb;
import bta.cabang.operasional.repository.KuitansiDb;
import bta.cabang.operasional.repository.SiswaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Qualifier
@Service
@Transactional
public class SiswaServiceImpl implements SiswaService{
    @Autowired
    SiswaDb siswaDb;

    @Autowired
    InvoiceDb invoiceDb;

    @Autowired
    KuitansiDb kuitansiDb;

    @Override
    public void addSiswa(SiswaModel siswa) { siswaDb.save(siswa); }

    @Override
    public List<SiswaModel> getAllSiswa() {
        return siswaDb.findAll();
    }

    @Override
    public SiswaModel getSiswa(Long idSiswa) {
        return siswaDb.findSiswaByIdSiswa(idSiswa).get();
    }

    @Override
    public SiswaModel editSiswa(Long idSiswa, SiswaModel siswaUpdate) {
        SiswaModel siswa = getSiswa(idSiswa);
        siswa.setNamaSiswa(siswaUpdate.getNamaSiswa());
        siswa.setAsalSekolah(siswaUpdate.getAsalSekolah());
        siswa.setProgram(siswaUpdate.getProgram());
        siswa.setTanggalLahir(siswaUpdate.getTanggalLahir());
        siswa.setHpSiswa(siswaUpdate.getHpSiswa());
        siswa.setEmailSiswa(siswaUpdate.getEmailSiswa());
        siswa.setAlamatSiswa(siswaUpdate.getAlamatSiswa());
        siswa.setNamaOrtu(siswaUpdate.getNamaOrtu());
        siswa.setHpOrtu(siswaUpdate.getHpOrtu());
        return siswaDb.save(siswa);
    }

    @Override
    public void deleteSiswa(Long idSiswa) {
        SiswaModel siswa = getSiswa(idSiswa);
        InvoiceModel invoice = siswa.getInvoice();
        KuitansiModel kuitansi = siswa.getKuitansi();
        if (kuitansi != null) {
            kuitansiDb.deleteByIdKuitansi(kuitansi.getIdKuitansi());
        }
        if (invoice != null) {
            invoiceDb.deleteByIdInvoice(invoice.getIdInvoice());
        }
        siswaDb.deleteByIdSiswa(idSiswa);
    }
}
