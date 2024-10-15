package com.sparta.jpaschedule.entity;

public enum AuthorityEnum {
    MEMBER(Authority.MEMBER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    AuthorityEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MEMBER = "AUTH_MEMBER";
        public static final String ADMIN = "AUTH_ADMIN";
    }


}
