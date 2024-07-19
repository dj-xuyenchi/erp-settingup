package app.au.authentication.repository;

import entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INguoiDungRepository extends JpaRepository<NguoiDung,Long> {
}
