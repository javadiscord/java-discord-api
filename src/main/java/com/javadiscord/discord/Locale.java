package com.javadiscord.discord;

public enum Locale {
    id("Indonesian", "Bahasa Indonesia"),
    da("Danish", "Dansk"),
    de("German", "Deutsch"),
    en_GB("English, UK", "English, UK"),
    en_US("English, US", "English, US"),
    es_ES("Spanish", "Español"),
    es_419("Spanish, LATAM", "Español, LATAM"),
    fr("French", "Français"),
    hr("Croatian", "Hrvatski"),
    it("Italian", "Italiano"),
    lt("Lithuanian", "Lietuviškai"),
    hu("Hungarian", "Magyar"),
    nl("Dutch", "Nederlands"),
    no("Norwegian", "Norsk"),
    pl("Polish", "Polski"),
    pt_BR("Portuguese, Brazilian", "Português do Brasil"),
    ro("Romanian, Romania", "Română"),
    fi("Finnish", "Suomi"),
    sv_SE("Swedish", "Svenska"),
    vi("Vietnamese", "Tiếng Việt"),
    tr("Turkish", "Türkçe"),
    cs("Czech", "Čeština"),
    el("Greek", "Ελληνικά"),
    bg("Bulgarian", "български"),
    ru("Russian", "Pусский"),
    uk("Ukrainian", "Українська"),
    hi("Hindi", "हिन्दी"),
    th("Thai", "ไทย"),
    zh_CN("Chinese, China", "中文"),
    ja("Japanese", "日本語"),
    zh_TW("Chinese, Taiwan", "繁體中文"),
    ko("Korean", "한국어");

    private final String languageName;
    private final String nativeName;

    Locale(String languageName, String nativeName) {
        this.languageName = languageName;
        this.nativeName = nativeName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getNativeName() {
        return nativeName;
    }
}
