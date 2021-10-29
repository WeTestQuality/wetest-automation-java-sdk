package com.cloudtestapi.common;

public class Constants {

    public enum OrderAccountType {
        PERSON("personal"),
        PROJECT("project");
        private final String value;

        OrderAccountType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ModelListFilterType {
        MODEL(1), DEVICE(2);

        private final Integer code;

        ModelListFilterType(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    public enum ParserType {
        PyTest("pytest"), Custom("custom");
        private final String value;

        ParserType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
