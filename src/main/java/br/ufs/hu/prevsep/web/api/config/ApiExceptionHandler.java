package br.ufs.hu.prevsep.web.api.config;
import br.ufs.hu.prevsep.web.api.dto.fault.ErrorDetailDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.exception.PrevSepException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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