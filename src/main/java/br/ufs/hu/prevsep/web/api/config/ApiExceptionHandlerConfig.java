package br.ufs.hu.prevsep.web.api.config;
import br.ufs.hu.prevsep.web.api.dto.fault.ErrorDetailDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FieldErrorDTO;
import br.ufs.hu.prevsep.web.api.exception.PrevSepException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

        return new ResponseEntity<>(fault, ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FaultDTO faultDTO = new FaultDTO();
        faultDTO.setStatusCode(HttpStatus.BAD_REQUEST.value());
        faultDTO.setError(ex.getClass().getSimpleName());
        faultDTO.setMessage("Your request was rejected due to an validation error.");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        faultDTO.setErrorDetail(errorDetailDTO);
        errorDetailDTO.setExceptionClass(ex.getClass().getName());
        errorDetailDTO.setErrorDetail("Solve the " + ex.getFieldErrorCount() + " above error(s) and send your request again.");
        List<FieldErrorDTO> fieldErrorDTOS = new ArrayList<>();
        errorDetailDTO.setFieldErrors(fieldErrorDTOS);

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrorDTOS.add(new FieldErrorDTO(fieldName, errorMessage));
        });

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
    public ResponseEntity<Object> handleInternalError(Exception ex) {
        FaultDTO fault = new FaultDTO();
        fault.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        fault.setMessage(ex.getMessage());
        fault.setError(ex.getClass().getSimpleName());

        ErrorDetailDTO detailDTO = new ErrorDetailDTO();
        fault.setErrorDetail(detailDTO);
        detailDTO.setExceptionClass(ex.getClass().getCanonicalName());
        detailDTO.setErrorDetail(ex.getLocalizedMessage());

        return new ResponseEntity<>(fault, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}