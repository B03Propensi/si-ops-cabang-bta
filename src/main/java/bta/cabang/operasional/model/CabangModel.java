package bta.cabang.operasional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

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

//    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Size(max = 50)
    @Column(name = "nomor_telepon", nullable = true, unique = true)
    private String nomor_telepon;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column(name = "logo", nullable = true, length = 10485760)
    private byte[] logo;

    @OneToMany(mappedBy = "cabang", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<KelasModel> listKelas;

    @Size(max = 30)
    @Column(name = "nomor_rekening",unique = true)
    private String nomor_rekening;

    @Column(name = "bank_cabang")
    private String bank_cabang;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }


    public List<KelasModel> getListKelas() {
        return listKelas;
    }

    public void setListKelas(List<KelasModel> listKelas) {
        this.listKelas = listKelas;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getNomor_rekening() {
        return nomor_rekening;
    }

    public void setNomor_rekening(String nomor_rekening) {
        this.nomor_rekening = nomor_rekening;
    }

    public String getBank_cabang() {
        return bank_cabang;
    }

    public void setBank_cabang(String bank_cabang) {
        this.bank_cabang = bank_cabang;
    }
}
