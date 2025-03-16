package cl.ubiobio_gps.plantilla_gps.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
   
    
    public static ResponseEntity<Map<String, Object>> handleSuccess(
            String message,
            HttpStatus statusCode,
            Object Data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", Data);

        return new ResponseEntity<>(response, statusCode);
    }
    public static ResponseEntity<Map<String, Object>> handleErrorClient(
            String message,
            HttpStatus statusCode,
            Object Data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", Data);

        return new ResponseEntity<>(response, statusCode);
    }
    public static ResponseEntity<Map<String, Object>> handleErrorServer(
            String message,
            HttpStatus statusCode,
            Object Data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", statusCode);
        response.put("message", message);
        response.put("data", Data);

        return new ResponseEntity<>(response, statusCode);
    }
}
