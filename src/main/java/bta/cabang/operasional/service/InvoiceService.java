package bta.cabang.operasional.service;

import bta.cabang.operasional.model.InvoiceModel;

public interface InvoiceService {
    void addInvoice(InvoiceModel invoice);
    InvoiceModel getInvoiceByIdSiswa(Long idSiswa);
}
