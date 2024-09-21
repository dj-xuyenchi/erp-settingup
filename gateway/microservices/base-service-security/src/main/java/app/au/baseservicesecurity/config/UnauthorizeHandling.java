package app.au.baseservicesecurity.config;

import api.ResponseApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class UnauthorizeHandling {
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse response) throws IOException {
        ResponseApi<String> responseApi = new ResponseApi<>();
        responseApi.setMessage("Bạn không có quyền truy cập vào API này!");
        responseApi.setData(null);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK); // Trả về mã 200
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseApi));
        response.getWriter().flush();
    }

}
