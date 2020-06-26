package xyz.gabear.learn.ssm.enums;

public enum DataStateEnum {
    INVALID(0, "无效"),
    VALID(1, "有效");

    private int code;
    private String description;

    DataStateEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static DataStateEnum stateOf(int code) {
        for (DataStateEnum state : DataStateEnum.values()) {
            if (state.code == code) {
                return state;
            }
        }
        return null;
    }

    public static DataStateEnum[] getAllStates() {
        return DataStateEnum.values();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
