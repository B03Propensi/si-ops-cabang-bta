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
@Table(name = "kuitansi")
public class KuitansiModel implements Serializable {

    @Id
    @Column(name = "id_kuitansi")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKuitansi;

    @NotNull
    @Size(max=255)
    @Column(name="bank_tujuan", nullable = false)
    private String bankTujuan;

    @NotNull
    @Size(max=255)
    @Column(name="nominal_kuitansi", nullable = false)
    private String nominalKuitansi;

    @NotNull
    @Column(name = "tanggal_pembayaran", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalPembayaran;

    @NotNull
    @Column(name = "tanggal_kuitansi", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggalKuitansi = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pembuat_kuitansi", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel pembuatKuitansi;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_kuitansi", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SiswaModel siswaKuitansi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_kuitansi", referencedColumnName = "id_program", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProgramModel programKuitansi;


    public Long getIdKuitansi() {
        return idKuitansi;
    }

    public void setIdKuitansi(Long idKuitansi) {
        this.idKuitansi = idKuitansi;
    }

    public String getBankTujuan() {
        return bankTujuan;
    }

    public void setBankTujuan(String bankTujuan) {
        this.bankTujuan = bankTujuan;
    }

    public String getNominalKuitansi() {
        return nominalKuitansi;
    }

    public void setNominalKuitansi(String nominalKuitansi) {
        this.nominalKuitansi = nominalKuitansi;
    }

    public Date getTanggalKuitansi() {
        return tanggalKuitansi;
    }

    public void setTanggalKuitansi(Date tanggalKuitansi) {
        this.tanggalKuitansi = tanggalKuitansi;
    }

    public UserModel getPembuatKuitansi() {
        return pembuatKuitansi;
    }

    public void setPembuatKuitansi(UserModel pembuatKuitansi) {
        this.pembuatKuitansi = pembuatKuitansi;
    }

    public SiswaModel getSiswaKuitansi() {
        return siswaKuitansi;
    }

    public void setSiswaKuitansi(SiswaModel siswaKuitansi) {
        this.siswaKuitansi = siswaKuitansi;
    }

    public ProgramModel getProgramKuitansi() {
        return programKuitansi;
    }

    public void setProgramKuitansi(ProgramModel programKuitansi) {
        this.programKuitansi = programKuitansi;
    }

    public Date getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(Date tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }
}