package com.javadiscord.jdi.core.models.guild;

public enum WidgetStyleOptions {
    SHIELD("shield"),
    BANNER_1("banner1"),
    BANNER_2("banner2"),
    BANNER_3("banner3"),
    BANNER_4("banner4");

    private final String style;

    private WidgetStyleOptions(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
