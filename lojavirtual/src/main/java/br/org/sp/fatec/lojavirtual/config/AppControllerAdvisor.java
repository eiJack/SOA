package br.org.sp.fatec.lojavirtual.config;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppControllerAdvisor {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ProblemDetail> tratarErroDeValidacao(ValidationException excecao) {
        var mensagem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        mensagem.setDetail(excecao.getMessage());
        return ResponseEntity.badRequest().body(mensagem);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ProblemDetail> tratarErroDeNaoExiste(NoSuchElementException excecao) {
        var mensagem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        mensagem.setDetail(excecao.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }
}
