package org.csu.mypetstore.dto;

public class PreferencesDTO {
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;
    private String bannerName;

    public PreferencesDTO(
            String favouriteCategoryId,
            String languagePreference,
            boolean listOption,
            boolean bannerOption,
            String bannerName
    ) {
        this.favouriteCategoryId = favouriteCategoryId;
        this.languagePreference = languagePreference;
        this.listOption = listOption;
        this.bannerOption = bannerOption;
        this.bannerName = bannerName;
    }

    public String getFavouriteCategoryId() {
        return favouriteCategoryId;
    }

    public String getLanguagePreference() {
        return languagePreference;
    }

    public boolean isListOption() {
        return listOption;
    }

    public boolean isBannerOption() {
        return bannerOption;
    }

    public String getBannerName() {
        return bannerName;
    }
}
