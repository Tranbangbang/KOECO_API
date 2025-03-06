package com.example.koeco_api.utils;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsFunction {
    public static boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Kiểm tra chuỗi email với biểu thức chính quy
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches(); // Trả về true nếu email hợp lệ, false nếu không hợp lệ
    }

    public static LocalDateTime getVietNameTimeNow() {
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime vietnamTime = ZonedDateTime.now(vietnamZone);

        // Chuyển đổi thành LocalDateTime
        return vietnamTime.toLocalDateTime();
    }

    public static String convertParamToString(String param) {
        if (param == null)
            return null;
        param = param.trim();
        if (param.isEmpty())
            return null;
        return param;
    }

    public static Double convertParamToDouble(String param) {
        if (param == null)
            return null;
        param = param.trim();
        if (param.isEmpty())
            return null;

        return Double.parseDouble(param);
    }

    public static Boolean notNull(Object object) {
        // Kiểm tra nếu đối tượng là null
        if (object == null) {
            return false;  // Nếu đối tượng là null, trả về false
        }

        // Kiểm tra nếu đối tượng là một List và danh sách có size bằng 0
        if (object instanceof List) {
            return !((List<?>) object).isEmpty();  // Trả về false nếu List rỗng
        }

        // Kiểm tra nếu đối tượng là mảng và mảng có rỗng không
        if (object.getClass().isArray()) {
            return Array.getLength(object) > 0;  // Trả về true nếu mảng không rỗng
        }

        // Nếu không phải mảng hay List, trả về true (đối tượng không null và không rỗng)
        return true;
    }

    public static String saveLogo(MultipartFile file, String UPLOAD_DIR) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

        } catch (IOException e) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

}
