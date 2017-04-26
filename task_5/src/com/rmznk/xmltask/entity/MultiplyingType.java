package com.rmznk.xmltask.entity;

public enum MultiplyingType {
    LEAVES("Leaves"),
    CUTTINGS("Cuttings"),
    SEEDS("Seeds");
    private String value;

    MultiplyingType(String v) {
        value = v;
    }

    public static MultiplyingType fromValue(String v) {
        MultiplyingType result = null;
        for (MultiplyingType c : MultiplyingType.values()) {
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
