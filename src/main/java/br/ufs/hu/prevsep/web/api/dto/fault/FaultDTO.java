package br.ufs.hu.prevsep.web.api.dto.fault;

public class FaultDTO {
    private Integer statusCode;
    private String message;
    private String error;
    private ErrorDetailDTO errorDetail;

    public FaultDTO(int statusCode, String message, String error) {
        this.setStatusCode(statusCode);
        this.setMessage(message);
        this.setError(error);
    }

    public FaultDTO() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorDetailDTO getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetailDTO errorDetail) {
        this.errorDetail = errorDetail;
    }
}
