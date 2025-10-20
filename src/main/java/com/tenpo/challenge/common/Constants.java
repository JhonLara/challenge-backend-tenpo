package com.tenpo.challenge.common;

public final class Constants {
    private Constants() {
    }

    // Cache
    public static final String CACHE_PERCENTAGE = "percentageCache";
    public static final String CACHE_KEY_PERCENTAGE = "percentage";

    // Endpoints
    public static final String ENDPOINT_CALC = "/calc";
    public static final String ENDPOINT_HISTORY = "/history";

    // Request param names
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_SIZE = "size";

    // Response markers
    public static final String PERCENTAGE_SOURCE_CACHE_OR_EXTERNAL = "cache_or_external";

    // Error codes
    public static final String ERR_PERCENTAGE_UNAVAILABLE = "PERCENTAGE_UNAVAILABLE";
    public static final String ERR_VALIDATION = "VALIDATION_ERROR";
    public static final String ERR_BAD_REQUEST = "BAD_REQUEST";
    public static final String ERR_INTERNAL = "INTERNAL_ERROR";

    // Messages
    public static final String MSG_PERCENTAGE_NOT_AVAILABLE = "Percentage not available";
    public static final String MSG_NO_CACHED_AND_EXTERNAL_FAILED =
            "No cached percentage and external failed";
    public static final String MSG_CACHE_NOT_CONFIGURED = "Cache not configured";
    public static final String MSG_INVALID_REQUEST = "Invalid request";
    public static final String MSG_UNEXPECTED_ERROR = "Unexpected error";
}
