package bta.cabang.operasional.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "program")
public class ProgramModel implements Serializable {

    @Id
    @Column(name = "id_program")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgram;

    @NotNull
    @Size(max=255)
    @Column(name="nama_program", nullable = false)
    private String namaProgram;

    @NotNull
    @Size(max=255)
    @Column(name="biaya_program", nullable = false)
    private String biayaProgram;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_program", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SiswaModel siswaProgram;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_program", referencedColumnName = "id_invoice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private InvoiceModel invoiceProgram;

    public Long getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Long idProgram) {
        this.idProgram = idProgram;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getBiayaProgram() {
        return biayaProgram;
    }

    public void setBiayaProgram(String biayaProgram) {
        this.biayaProgram = biayaProgram;
    }

    public SiswaModel getSiswaProgram() {
        return siswaProgram;
    }

    public void setSiswaProgram(SiswaModel siswaProgram) {
        this.siswaProgram = siswaProgram;
    }

    public InvoiceModel getInvoiceProgram() {
        return invoiceProgram;
    }

    public void setInvoiceProgram(InvoiceModel invoiceProgram) {
        this.invoiceProgram = invoiceProgram;
    }
}