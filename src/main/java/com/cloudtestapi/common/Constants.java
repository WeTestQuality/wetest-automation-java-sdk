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

    public static class DeviceState {
        public static final Integer UNINITALIZED = 1;
        public static final Integer FREE = 2;
        public static final Integer TESTING = 3;
        public static final Integer CLEANING = 4;
    }

    public static class InstallProgress {
        public static final Integer DOWNLOADING = 1;
        public static final Integer PUSHING = 2;
        public static final Integer INSTALLING = 3;
    }

    public static class DeviceType {
        public static final Integer ANDROID = 0;
        public static final Integer iOS = 1;
    }

    public static class ResignStatus {
        public static final Integer RESIGNING = 0;
        public static final Integer SUCCESS = 1;
        public static final Integer FAILED = 2;
        public static final Integer EXPIRED = 3;
    }
}
