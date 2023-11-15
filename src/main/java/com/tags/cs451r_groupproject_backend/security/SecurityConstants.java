package com.tags.cs451r_groupproject_backend.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public static final int SECONDS_IN_MILLISECOND = 1000;
    private static final int NUMBER_OF_MINS_TOKEN_IS_VALID_FOR = 1_440 * 7;
    //Token should be valid for a full week
    public static final int TOKEN_VALID_DURATION = NUMBER_OF_MINS_TOKEN_IS_VALID_FOR * 60;
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int BEARER_PREFIX_START_INDEX = 7;

}
