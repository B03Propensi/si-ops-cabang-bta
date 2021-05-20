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
@Table(name = "status_pembayaran")
public class StatusPembayaranModel {

    @Id
    @Column(name = "id_pembayaran")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPembayaran;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_status", nullable = false)
    private String namaStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_siswa", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SiswaModel siswaStatus;

    public Long getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Long idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getNamaStatus() {
        return namaStatus;
    }

    public void setNamaStatus(String namaStatus) {
        this.namaStatus = namaStatus;
    }

    public SiswaModel getSiswaStatus() {
        return siswaStatus;
    }

    public void setSiswaStatus(SiswaModel siswaStatus) {
        this.siswaStatus = siswaStatus;
    }
}
