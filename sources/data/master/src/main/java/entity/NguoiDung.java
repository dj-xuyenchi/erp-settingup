package entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NguoiDung {
    private Long nguoiDungId;
    private String code;
    private String email;
    private String hoVaTenDem;
    private String ten;
    private String soDienThoaiCaNhan;
    private String soDienThoaiIP;
    private String diaChiChiTiet;
}
