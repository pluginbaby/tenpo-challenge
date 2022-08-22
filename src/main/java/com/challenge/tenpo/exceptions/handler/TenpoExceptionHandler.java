package com.challenge.tenpo.exceptions.handler;

import com.challenge.tenpo.exceptions.dto.ErrorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class TenpoExceptionHandler {

    /**
     * Handler for an unexpected exception.
     *
     * @param ex The exception that caused the error.
     * @return The HTTP response with the status and error message.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDTO> handleGeneralExceptions(Exception ex) {
        log.error("Something went wrong. StackTrace for details", ex);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO(INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    /**
     * Handle 400 Missing Parameter failures here
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingParameter(MissingServletRequestParameterException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponseDTO(BAD_REQUEST.value(), exception.getMessage()));
    }

    /**
     * Handle 400 Missing Parameter failures here
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Check request controller validator failed:[{}]", exception.getParameter().getContainingClass());
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        Iterator<ObjectError> it = errors.iterator();
        ArrayList<String> array = new ArrayList<>();
        while (it.hasNext()) {
            ObjectError error = it.next();
            Object[] arguments = error.getArguments();
            DefaultMessageSourceResolvable some = (DefaultMessageSourceResolvable) arguments[0];
            array.add(some.getCode() + ":" + error.getDefaultMessage());
        }
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponseDTO(BAD_REQUEST.value(), array.toString()));
    }

}
