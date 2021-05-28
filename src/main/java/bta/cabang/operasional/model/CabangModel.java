package bta.cabang.operasional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cabang")

public class CabangModel implements Serializable {
    @Id
    @Column(name = "id_cabang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cabang;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama_cabang", nullable = false)
    private String nama_cabang;

    @NotNull
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_cabang", nullable = false , unique = true)
    private String kode_cabang;

    @OneToMany(mappedBy = "cabang", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<KelasModel> listKelas;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_cabang", referencedColumnName = "id_siswa", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SiswaModel siswaCabang;


    public Long getId_cabang() {
        return id_cabang;
    }

    public void setId_cabang(Long id_cabang) {
        this.id_cabang = id_cabang;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKode_cabang() {
        return kode_cabang;
    }

    public void setKode_cabang(String kode_cabang) {
        this.kode_cabang = kode_cabang;
    }
}
