package bta.cabang.operasional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "kelas")
public class KelasModel implements Serializable {
    @Id
    @Column(name = "id_kelas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKelas;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_kelas", nullable = false)
    private String namaKelas;

    @NotNull
    @Size(max = 50)
    @Column(name = "bidang", nullable = false)
    private String bidang;

    @NotNull
    @Size(max = 6)
    @Column(name = "hari", nullable = false)
    private String hari;

    @NotNull
    @Column(name = "waktu", nullable = false)
    private Time waktu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cabang", referencedColumnName = "id_cabang", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CabangModel cabang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel pengajar;

    public Long getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Long idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Time getWaktu() {
        return waktu;
    }

    public void setWaktu(Time waktu) {
        this.waktu = waktu;
    }

    public CabangModel getCabang() {
        return cabang;
    }

    public void setCabang(CabangModel cabang) {
        this.cabang = cabang;
    }

    public UserModel getPengajar() {
        return pengajar;
    }

    public void setPengajar(UserModel pengajar) {
        this.pengajar = pengajar;
    }
}
