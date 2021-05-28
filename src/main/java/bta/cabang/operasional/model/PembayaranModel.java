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
@Table(name = "pembayaran")
public class PembayaranModel implements Serializable {

    @Id
    @Column(name = "id_pembayaran")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPembayaran;

    @NotNull
    @Size(max=255)
    @Column(name="nama_pembayaran", nullable = false)
    private String namaPembayaran;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_status", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SiswaModel> siswaStatus;

    public Long getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getNamaPembayaran() {
        return namaPembayaran;
    }

    public void setNamaPembayaran(String namaPembayaran) {
        this.namaPembayaran = namaPembayaran;
    }

    public List<SiswaModel> getSiswaStatus() {
        return siswaStatus;
    }

    public void setSiswaStatus(List<SiswaModel> siswaStatus) {
        this.siswaStatus = siswaStatus;
    }
}