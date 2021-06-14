package br.ufs.hu.prevsep.web.api.dto.form;

public enum FormStatus {
    SAVED(1), CREATED(2), PENDING(3), FINISHED(4), CANCELLED(5);

    private final int value;

    FormStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FormStatus fromValue(Integer value) {
        if (value == null)
            return null;

        switch (value){
            case 1:
                return SAVED;
            case 2:
                return CREATED;
            case 3:
                return PENDING;
            case 4:
                return FINISHED;
            case 5:
                return CANCELLED;
            default:
                return null;
        }
    }
}
