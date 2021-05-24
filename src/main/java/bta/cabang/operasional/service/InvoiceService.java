package bta.cabang.operasional.service
        ;
import bta.cabang.operasional.model.*;

import java.util.List;

public interface InvoiceService {
    InvoiceModel getInvoice(Long idInvoice);
    List<InvoiceModel> getAllInvoice();
}
