package bta.cabang.operasional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cuti")
@JsonIgnoreProperties(value={"pengaju"},allowSetters = true)
public class CutiModel implements Serializable {
    @Id
    @Column(name = "id_cuti")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuti;

    @NotNull
    @Column(name = "tanggal_diajukan", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggal_diajukan = new Date();

    @NotNull
    @Column(name = "tanggal_mulai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggal_mulai;

    @NotNull
    @Column(name = "tanggal_selesai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Jakarta")
    private Date tanggal_selesai;

    @NotNull
    @Column(name = "alasan", nullable = false)
    private String alasan;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status = 0;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel pengaju;

    public Long getIdCuti() {
        return idCuti;
    }

    public void setIdCuti(Long idCuti) {
        this.idCuti = idCuti;
    }

    public Date getTanggal_diajukan() {
        return tanggal_diajukan;
    }

    public void setTanggal_diajukan(Date tanggal_diajukan) {
        this.tanggal_diajukan = tanggal_diajukan;
    }

    public Date getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public Date getTanggal_selesai() {
        return tanggal_selesai;
    }

    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public UserModel getPengaju() {
        return pengaju;
    }

    public void setPengaju(UserModel pengaju) {
        this.pengaju = pengaju;
    }
}
