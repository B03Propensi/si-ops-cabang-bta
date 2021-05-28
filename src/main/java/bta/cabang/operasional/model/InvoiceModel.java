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
@Table(name = "invoice")
public class InvoiceModel implements Serializable {

    @Id
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;

    @NotNull
    @Column(name = "tanggal_invoice", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalInvoice = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pembuat_invoice", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel pembuatInvoice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_invoice", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SiswaModel siswaInvoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_invoice", referencedColumnName = "id_program", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProgramModel programInvoice;

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getTanggalInvoice() {
        return tanggalInvoice;
    }

    public void setTanggalInvoice(Date tanggalInvoice) {
        this.tanggalInvoice = tanggalInvoice;
    }

    public UserModel getPembuatInvoice() {
        return pembuatInvoice;
    }

    public void setPembuatInvoice(UserModel pembuatInvoice) {
        this.pembuatInvoice = pembuatInvoice;
    }

    public SiswaModel getSiswaInvoice() {
        return siswaInvoice;
    }

    public void setSiswaInvoice(SiswaModel siswaInvoice) {
        this.siswaInvoice = siswaInvoice;
    }

    public ProgramModel getProgramInvoice() {
        return programInvoice;
    }

    public void setProgramInvoice(ProgramModel programInvoice) {
        this.programInvoice = programInvoice;
    }
}