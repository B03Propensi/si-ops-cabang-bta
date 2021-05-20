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
@Table(name = "siswa")
public class SiswaModel implements Serializable{

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
    @Size(max = 50)
    @Column(name = "nama_siswa", nullable = false)
    private String namaSiswa;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalLahir;

    @NotNull
    @Size(max = 50)
    @Column(name = "asal_sekolah", nullable = false)
    private String asalSekolah;

    @NotNull
    @Size(max = 50)
    @Column(name = "email_siswa", nullable = false)
    private String emailSiswa;

    @NotNull
    @Column(name = "handphone_siswa", nullable = false)
    private Long handphoneSiswa;

    @NotNull
    @Column(name = "alamat_siswa", nullable = false)
    private String alamatSiswa;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_ortu", nullable = false)
    private String namaOrtu;

    @NotNull
    @Column(name = "handphone_ortu", nullable = false)
    private Long handphoneOrtu;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_invoice", referencedColumnName = "id_invoice", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InvoiceModel invoice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kuitansi", referencedColumnName = "id_kuitansi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KuitansiModel kuitansi;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_program", referencedColumnName = "id_program", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProgramKelasModel program;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id_status", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StatusPembayaranModel statusPembayaran;

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

    public Long getHandphoneSiswa() {
        return handphoneSiswa;
    }

    public void setHandphoneSiswa(Long handphoneSiswa) {
        this.handphoneSiswa = handphoneSiswa;
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

    public Long getHandphoneOrtu() {
        return handphoneOrtu;
    }

    public void setHandphoneOrtu(Long handphoneOrtu) {
        this.handphoneOrtu = handphoneOrtu;
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

    public ProgramKelasModel getProgram() {
        return program;
    }

    public void setProgram(ProgramKelasModel program) {
        this.program = program;
    }

    public StatusPembayaranModel getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(StatusPembayaranModel statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public void setKuitansi(KuitansiModel kuitansi) {
        this.kuitansi = kuitansi;
    }
}
