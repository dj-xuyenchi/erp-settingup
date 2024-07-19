package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "APP_NGUOIDUNG")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "NGUOIDUNG_ID")
    private Long nguoiDungId;
    @Column(name = "CODE")
    private String code;
//    @Column(name = "EMAIL")
//    private String email;
    @Column(name = "HOVATENDEM")
    private String hoVaTenDem;
    @Column(name = "TEN")
    private String ten;
    @Column(name = "SODIENTHOAICANHAN")
    private String soDienThoaiCaNhan;
    @Column(name = "SODIENTHOAIIP")
    private String soDienThoaiIP;
    @Column(name = "DIACHICHITIET")
    private String diaChiChiTiet;
}
