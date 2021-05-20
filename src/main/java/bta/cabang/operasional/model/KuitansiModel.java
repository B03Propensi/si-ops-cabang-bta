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
@Table(name = "kuitansi")
public class KuitansiModel {

    @Id
    @Column(name = "id_kuitansi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKuitansi;

    @NotNull
    @Column(name = "nominal_kuitansi", nullable = false)
    private Integer nominalKuitansi;

    @NotNull
    @Column(name = "tanggal_kuitansi", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalKuitansi = new Date();

    @NotNull
    @Size(max = 50)
    @Column(name = "bank_tujuan", nullable = false)
    private String bankTujuan;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_siswa", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SiswaModel siswaKui;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_invoice", referencedColumnName = "id_invoice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InvoiceModel invoiceKui;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel pembuatKui;

    public Long getIdKuitansi() {
        return idKuitansi;
    }

    public Integer getNominalKuitansi() {
        return nominalKuitansi;
    }

    public void setNominalKuitansi(Integer nominalKuitansi) {
        this.nominalKuitansi = nominalKuitansi;
    }

    public void setIdKuitansi(Long idKuitansi) {
        this.idKuitansi = idKuitansi;
    }

    public Date getTanggalKuitansi() {
        return tanggalKuitansi;
    }

    public void setTanggalKuitansi(Date tanggalKuitansi) {
        this.tanggalKuitansi = tanggalKuitansi;
    }

    public String getBankTujuan() {
        return bankTujuan;
    }

    public void setBankTujuan(String bankTujuan) {
        this.bankTujuan = bankTujuan;
    }

    public SiswaModel getSiswaKui() {
        return siswaKui;
    }

    public void setSiswaKui(SiswaModel siswaKui) {
        this.siswaKui = siswaKui;
    }

    public InvoiceModel getInvoiceKui() {
        return invoiceKui;
    }

    public void setInvoiceKui(InvoiceModel invoiceKui) {
        this.invoiceKui = invoiceKui;
    }

    public UserModel getPembuatKui() {
        return pembuatKui;
    }

    public void setPembuatKui(UserModel pembuatKui) {
        this.pembuatKui = pembuatKui;
    }
}
