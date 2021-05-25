package bta.cabang.operasional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice")
public class InvoiceModel implements Serializable{

    @Id
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;

    @NotNull
    @Column(name = "nominal_invoice", nullable = false)
    private Integer nominalInvoice;

    @NotNull
    @Column(name = "tanggal_invoice", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalInvoice = new Date();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_siswa", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SiswaModel siswaInv;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kuitansi", referencedColumnName = "id_kuitansi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KuitansiModel kuitansiInv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel pembuatInv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_program", referencedColumnName = "id_program", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProgramKelasModel programInv;

    public ProgramKelasModel getProgramInv() {
        return programInv;
    }

    public void setProgramInv(ProgramKelasModel programInv) {
        this.programInv = programInv;
    }

    public UserModel getPembuatInv() {
        return pembuatInv;
    }

    public void setPembuatInv(UserModel pembuatInv) {
        this.pembuatInv = pembuatInv;
    }

    public KuitansiModel getKuitansiInv() {
        return kuitansiInv;
    }

    public void setKuitansiInv(KuitansiModel kuitansiInv) {
        this.kuitansiInv = kuitansiInv;
    }

    public SiswaModel getSiswaInv() {
        return siswaInv;
    }

    public void setSiswaInv(SiswaModel siswaInv) {
        this.siswaInv = siswaInv;
    }

    public Date getTanggalInvoice() {
        return tanggalInvoice;
    }

    public void setTanggalInvoice(Date tanggalInvoice) {
        this.tanggalInvoice = tanggalInvoice;
    }

    public Integer getNominalInvoice() {
        return nominalInvoice;
    }

    public void setNominalInvoice(Integer nominalInvoice) {
        this.nominalInvoice = nominalInvoice;
    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }
}
