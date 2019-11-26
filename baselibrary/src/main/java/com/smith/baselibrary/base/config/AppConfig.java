package com.smith.baselibrary.base.config;

public class AppConfig {
    private AppConfig() {
    }

    private static class AppConfigInstanceHolder {
        private static AppConfig INSTANCE = new AppConfig();
    }

    public AppConfig getInstance() {
        return AppConfigInstanceHolder.INSTANCE;
    }




}
