package edu.tsu.ge.salesman.core.input;

public enum InputType {
    XML("xml"),
    EXCEL("xlsx");

    private final String type;

    InputType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static InputType getInputType(String symbol) {
        for (InputType type : InputType.values()) {
            if (type.getType().equalsIgnoreCase(symbol.trim())) {
                return type;
            }
        }
        return null;
    }
}
