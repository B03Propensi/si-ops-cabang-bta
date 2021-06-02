package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDb extends JpaRepository<InvoiceModel, Long> {
    Optional<InvoiceModel> findInvoiceByIdInvoice(Long idInvoice);
    Optional<InvoiceModel> findInvoiceBySiswaInvoice_IdSiswa(Long idSiswa);
}
