package br.com.ctmait.addressprocessor.domain.models;

import java.util.Arrays;

public enum State {
    INCLUDED("INCLUDED"),
    UPDATED("UPDATED"),
    EXCLUDED("EXCLUDED"),
    PUBLISHED("PUBLISHED");

    private String code;

    State(String code) {
        this.code = code;
    }

    public static State getByCode(String code){
        return Arrays.stream(values())
                .filter(state -> state.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "State{" +
                "code='" + code + '\'' +
                '}';
    }
}
