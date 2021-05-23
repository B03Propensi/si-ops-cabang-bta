package bta.cabang.operasional.repository;

import bta.cabang.operasional.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDb extends JpaRepository<InvoiceModel, Long>{
    InvoiceModel findByInvoiceId (Long idInvoice);
}
