package com.picpaysimplicado.infra;

import com.picpaysimplicado.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

 @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
     ExceptionDTO exceptionDTO = new ExceptionDTO("Usuario já cadastrado", "400");
     return ResponseEntity.badRequest().body(exceptionDTO);
 }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(DataIntegrityViolationException exception){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionDTO> handleClientError(HttpClientErrorException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                "Erro na autorização: " + e.getStatusCode().toString(),
                String.valueOf(e.getStatusCode().value())
        );
        return ResponseEntity.status(e.getStatusCode()).body(exceptionDTO);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ExceptionDTO> handleServerError(HttpServerErrorException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                "Erro no servidor ao tentar autorizar: " + e.getStatusCode().toString(),
                String.valueOf(e.getStatusCode().value())
        );
        return ResponseEntity.status(e.getStatusCode()).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
