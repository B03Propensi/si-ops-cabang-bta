package bta.cabang.operasional.service;

import bta.cabang.operasional.model.InvoiceModel;
import bta.cabang.operasional.repository.InvoiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Qualifier
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceDb invoiceDb;

    @Override
    public void addInvoice(InvoiceModel invoice) {
        invoiceDb.save(invoice); }
}
