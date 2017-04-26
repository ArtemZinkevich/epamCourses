package com.rmznk.xmltask.entity;

public enum SoilType {

    PODSOLIC("Podsolic"),
    GROUND("Ground"),
    SOD_PODSOLIC("Sod-Podsolic");

    private String value;

    SoilType(String v) {
        value = v;
    }

    public static SoilType fromValue(String v) {
        SoilType result = null;
        for (SoilType c : SoilType.values()) {
            if (c.value.equals(v)) {
                result = c;
            }
        }
        return result;
    }

    public String value() {
        return value;
    }

}
