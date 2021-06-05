package bta.cabang.operasional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="presensi")
public class PresensiModel implements Serializable {

	@Id
	@Column(name="id_presensi")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPresensi;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    UserModel user;

	@NotNull
	@Column(name = "date")
    Timestamp date;

//	@NotNull
	@Column(name = "jabatan")
    String jabatan;

	@NotNull
	@Column(name = "status")
    Integer status;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cabang", referencedColumnName = "id_cabang", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    CabangModel lokasi;

	@Column(name = "alasan")
	private String alasan;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_kelas", referencedColumnName = "id_kelas", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	KelasModel kelas;

	@Column(name = "tanggal")
	private String tanggal;

	@Column(name = "waktu")
	private String waktu;

	public Integer getIdPresensi(){
        return this.idPresensi;
    }

    public void setIdPresensi(Integer idPresensi) {
        this.idPresensi = idPresensi;
    }

	public UserModel getUser() {
		return this.user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getJabatan() {
		return this.jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CabangModel getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(CabangModel lokasi) {
		this.lokasi = lokasi;
	}

	public String getAlasan() {
		return alasan;
	}

	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}

	public KelasModel getKelas() {
		return kelas;
	}

	public void setKelas(KelasModel kelas) {
		this.kelas = kelas;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getWaktu() {
		return waktu;
	}

	public void setWaktu(String waktu) {
		this.waktu = waktu;
	}
}
