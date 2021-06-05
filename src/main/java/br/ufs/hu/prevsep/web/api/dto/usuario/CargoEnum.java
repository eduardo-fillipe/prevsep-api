package br.ufs.hu.prevsep.web.api.dto.usuario;

public enum CargoEnum {

    GESTOR(1), MEDICO(2), ENFERMEIRO(3);

    private final Integer id;

    CargoEnum(Integer id) {
        this.id = id;
    }

    public static CargoEnum fromId(Integer value) {
        switch (value) {
            case 1:
                return GESTOR;
            case 2:
                return MEDICO;
            case 3:
                return ENFERMEIRO;
            default:
                return null;
        }
    }

    public Integer getId() {
        return id;
    }
}
