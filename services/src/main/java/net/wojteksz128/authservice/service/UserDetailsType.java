package net.wojteksz128.authservice.service;

public enum UserDetailsType {
    FULL_NAME("Imię i nazwisko użytkownika"),
    BIRTH_DATE("Data urodzenia"),
    E_MAIL("Adres e-mail"),
    CONTACT_DATA("Dane kontaktowe");

    private final String scopeName;
    private final String displayName;

    UserDetailsType(String displayName) {
        this.scopeName = "scope." + this.name();
        this.displayName = displayName;
    }

    public String getScopeName() {
        return scopeName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
