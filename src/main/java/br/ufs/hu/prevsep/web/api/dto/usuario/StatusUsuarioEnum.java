package br.ufs.hu.prevsep.web.api.dto.usuario;

public enum StatusUsuarioEnum {

    ATIVO(1), DESATIVADO(2), EM_FERIAS(3), BLOQUEADO(4);

    private final Integer status;

    StatusUsuarioEnum(Integer status) {
        this.status = status;
    }

    public static StatusUsuarioEnum fromId(Integer value) {
        if (value == null)
            return null;

        switch (value) {
            case 1:
                return ATIVO;
            case 2:
                return DESATIVADO;
            case 3:
                return EM_FERIAS;
            default:
                return BLOQUEADO;
        }
    }

    public Integer getStatus() {
        return status;
    }
}
