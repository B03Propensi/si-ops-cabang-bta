package bta.cabang.operasional.service;

import bta.cabang.operasional.model.*;
import bta.cabang.operasional.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Qualifier
@Service
@Transactional
public class SiswaServiceImpl implements SiswaService{

    @Autowired
    SiswaDb siswaDb;


    @Override
    public void addSiswa(SiswaModel siswaModel) {

    }

    @Override
    public void deleteSiswa(SiswaModel siswaModel) {

    }

    @Override
    public SiswaModel ubahSiswa(Long idSiswa, SiswaModel siswa) {
        return null;
    }

    @Override
    public List<SiswaModel> getAllSiswa() {
        return siswaDb.findAll();
    }

    @Override
    public Optional<SiswaModel> getSiswaById(Long idSiswa) {
        return siswaDb.getSiswaById(idSiswa);
    }

    @Override
    public Optional<SiswaModel> getSiswaByNamaSiswa(String namaSiswa) {
        return siswaDb.getSiswaByNamaSiswa(namaSiswa);
    }

    @Override
    public List<InvoiceModel> getAllInvoice(Long idInvoice) {
        return siswaDb.findAllByInvoiceSiswa(idInvoice);
    }

    @Override
    public List<KuitansiModel> getAllKuitansi(Long idKuitansi) {
        return siswaDb.findAllByKuitansi(idKuitansi);
    }

    @Override
    public List<ProgramKelasModel> getAllProgram(Long idProgram) {
        return siswaDb.findAllByProgram(idProgram);
    }

    @Override
    public List<StatusPembayaranModel> getAllStatusPembayaran(Long idPembayaran) {
        return siswaDb.findAllByPembayaran(idPembayaran);
    }
}