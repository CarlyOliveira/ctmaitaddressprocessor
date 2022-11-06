package br.com.ctmait.addressprocessor.domain.models;

import java.util.Arrays;

public enum Provider {
    CTMAIT("CTMAIT"),
    OTHER("OTHER");

    private String code;

    Provider(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Provider getByCode(String code){
        return Arrays.stream(values())
                .filter(provider -> provider.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "code='" + code + '\'' +
                '}';
    }
}
