package Model;

public enum GameStatusEnum {
    NO_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");

    private String label;

    public String getLabel() {
        return label;
    }

    GameStatusEnum(final String label){
        this.label = label;

    }

}
