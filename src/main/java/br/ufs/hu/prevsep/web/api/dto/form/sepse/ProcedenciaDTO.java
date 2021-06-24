package br.ufs.hu.prevsep.web.api.dto.form.sepse;

public enum ProcedenciaDTO {
    CLINICA_MEDICA_1(1),
    CLINICA_MEDICA_2(2),
    CLINICA_CIRURGICA_1(3),
    CLINICA_CIRURGICA_2(4),
    PEDIATRIA(5),
    ONCOLOGIA(6),
    UTI(7),
    AMBULATORIO(8),
    DESCONHECIDA(9);

    private final int value;

    ProcedenciaDTO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProcedenciaDTO fromValue(Integer value) {
        if (value == null || value < 1 || value > 8)
            return DESCONHECIDA;

        return ProcedenciaDTO.values()[value - 1];
    }

    public static Integer toValue(ProcedenciaDTO procedenciaDTO) {
        if (procedenciaDTO == null)
            return 9;

        return procedenciaDTO.value;
    }
}
