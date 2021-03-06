package br.ufs.hu.prevsep.web.api.config;

import br.ufs.hu.prevsep.web.api.dto.fault.ErrorDetailDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FieldErrorDTO;
import br.ufs.hu.prevsep.web.api.exception.PrevSepException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PrevSepException.class)
    public ResponseEntity<Object> handlePrevSepException(PrevSepException ex) {
        FaultDTO fault = new FaultDTO();
        fault.setStatusCode(ex.getHttpStatus().value());
        fault.setMessage(ex.getMessage());
        fault.setError(ex.getClass().getSimpleName());

        ErrorDetailDTO detailDTO = new ErrorDetailDTO();
        fault.setErrorDetail(detailDTO);
        detailDTO.setExceptionClass(ex.getClass().getCanonicalName());
        detailDTO.setErrorDetail(ex.getDetailedMessage());
        detailDTO.setFieldErrors(ex.getFieldErrors());

        return new ResponseEntity<>(fault, ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleBindException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FaultDTO faultDTO = new FaultDTO();
        faultDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
        faultDTO.setError(ex.getClass().getSimpleName());
        faultDTO.setMessage("Sua solicita????o foi rejeitada devido a um erro de valida????o.");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        faultDTO.setErrorDetail(errorDetailDTO);
        errorDetailDTO.setExceptionClass(ex.getClass().getName());
        errorDetailDTO.setErrorDetail("Resolva o(s) erro(s) acima de " + ex.getFieldErrorCount() + " e envie sua solicita????o novamente.");
        List<FieldErrorDTO> fieldErrorDTOS = new ArrayList<>();
        errorDetailDTO.setFieldErrors(fieldErrorDTOS);

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrorDTOS.add(new FieldErrorDTO(fieldName, errorMessage));
        });

        return new ResponseEntity<>(faultDTO, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FaultDTO faultDTO = new FaultDTO();
        faultDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
        faultDTO.setError(ex.getClass().getSimpleName());
        faultDTO.setMessage("A solicita????o n??o p??de ser lida.");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setErrorDetail(ex.getMessage());
        faultDTO.setErrorDetail(errorDetailDTO);
        errorDetailDTO.setExceptionClass(ex.getClass().getName());
        if (ex.getMostSpecificCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = ((InvalidFormatException)ex.getMostSpecificCause());
            List<FieldErrorDTO> fieldErrorDTOS = new ArrayList<>();
            StringBuilder fieldPath = new StringBuilder(cause.getPath().get(0).getFieldName());
            for (int i = 1; i < cause.getPath().size(); i++) {
                fieldPath.append(".");
                fieldPath.append(cause.getPath().get(i).getFieldName());
            }
            fieldErrorDTOS.add(new FieldErrorDTO(fieldPath.toString(), "O valor n??o p??de ser lido: " + cause.getValue().toString()));
            errorDetailDTO.setFieldErrors(fieldErrorDTOS);
        }

        return new ResponseEntity<>(faultDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedError(AccessDeniedException ex) {
        FaultDTO fault = new FaultDTO();
        fault.setStatusCode(HttpStatus.FORBIDDEN.value());
        fault.setMessage(ex.getMessage());
        fault.setError(ex.getClass().getSimpleName());

        ErrorDetailDTO detailDTO = new ErrorDetailDTO();
        fault.setErrorDetail(detailDTO);
        detailDTO.setExceptionClass(ex.getClass().getCanonicalName());
        detailDTO.setErrorDetail(ex.getLocalizedMessage());

        return new ResponseEntity<>(fault, HttpStatus.valueOf(fault.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerError(Exception ex) {
        FaultDTO fault = new FaultDTO();
        fault.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        fault.setMessage("Um erro ocorreu durante sua solicita????o. Por favor, tente novamente mais tarde.");
        fault.setError(ex.getClass().getSimpleName());

        ErrorDetailDTO detailDTO = new ErrorDetailDTO();
        fault.setErrorDetail(detailDTO);
        detailDTO.setExceptionClass(ex.getClass().getCanonicalName());
        detailDTO.setErrorDetail(ex.getMessage());
        ex.printStackTrace();

        return new ResponseEntity<>(fault, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}