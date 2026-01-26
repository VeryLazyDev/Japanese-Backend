package com.dev.japanese_app.common.constant;

public class SecurityConstants {
    public static String[] WHITE_LISTS_URL = {
            "/japanese-app/swagger/**",
            "/japanese-app/api/auth/**",
            "/favicon.ico",
            "/.well-known/**"
    };
}
