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
@Table(name = "siswa")
public class SiswaModel implements Serializable {

    @Id
    @Column(name = "id_siswa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSiswa;

    @NotNull
    @Column(name = "tanggal_daftar", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalDaftar = new Date();

    @NotNull
    @Size(max=255)
    @Column(name="nama_siswa", nullable = false)
    private String namaSiswa;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalLahir;

    @NotNull
    @Size(max=255)
    @Column(name="asal_sekolah", nullable = false)
    private String asalSekolah;

    @NotNull
    @Size(max=255)
    @Column(name="email_siswa", nullable = false)
    private String emailSiswa;

    @NotNull
    @Size(max=255)
    @Column(name="hp_siswa", nullable = false)
    private String hpSiswa;

    @NotNull
    @Size(max=255)
    @Column(name="alamat_siswa", nullable = false)
    private String alamatSiswa;

    @NotNull
    @Size(max=255)
    @Column(name="nama_ortu", nullable = false)
    private String namaOrtu;

    @NotNull
    @Size(max=255)
    @Column(name="hp_ortu", nullable = false)
    private String hpOrtu;

    @NotNull
    @Column(name = "status_pembayaran", nullable = false)
    private Integer statusPembayaran = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cabang_siswa", referencedColumnName = "id_cabang")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CabangModel cabangSiswa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pembuat_siswa", referencedColumnName = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel pembuatSiswa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_siswa", referencedColumnName = "id_program")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProgramModel program;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_siswa", referencedColumnName = "id_invoice")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private InvoiceModel invoice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kuitansi_siswa", referencedColumnName = "id_kuitansi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KuitansiModel kuitansi;


    public Long getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(Long idSiswa) {
        this.idSiswa = idSiswa;
    }

    public Date getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(Date tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAsalSekolah() {
        return asalSekolah;
    }

    public void setAsalSekolah(String asalSekolah) {
        this.asalSekolah = asalSekolah;
    }

    public String getEmailSiswa() {
        return emailSiswa;
    }

    public void setEmailSiswa(String emailSiswa) {
        this.emailSiswa = emailSiswa;
    }

    public String getHpSiswa() {
        return hpSiswa;
    }

    public void setHpSiswa(String hpSiswa) {
        this.hpSiswa = hpSiswa;
    }

    public String getAlamatSiswa() {
        return alamatSiswa;
    }

    public void setAlamatSiswa(String alamatSiswa) {
        this.alamatSiswa = alamatSiswa;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public String getHpOrtu() {
        return hpOrtu;
    }

    public void setHpOrtu(String hpOrtu) {
        this.hpOrtu = hpOrtu;
    }

    public UserModel getPembuatSiswa() {
        return pembuatSiswa;
    }

    public void setPembuatSiswa(UserModel pembuatSiswa) {
        this.pembuatSiswa = pembuatSiswa;
    }

    public ProgramModel getProgram() {
        return program;
    }

    public void setProgram(ProgramModel program) {
        this.program = program;
    }

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }

    public KuitansiModel getKuitansi() {
        return kuitansi;
    }

    public void setKuitansi(KuitansiModel kuitansi) {
        this.kuitansi = kuitansi;
    }

    public CabangModel getCabangSiswa() {
        return cabangSiswa;
    }

    public void setCabangSiswa(CabangModel cabangSiswa) {
        this.cabangSiswa = cabangSiswa;
    }

    public Integer getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(Integer statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
}