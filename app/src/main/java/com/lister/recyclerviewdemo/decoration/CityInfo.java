
package com.lister.recyclerviewdemo.decoration;

public class CityInfo {

    private String mCityName;
    private String mGroup;
    private boolean mIsFirstInGroup;
    private boolean mIsLastInGroup;

    public CityInfo(String cityName, String group, boolean isFirstInGroup, boolean isLastInGroup) {
        this.mCityName = cityName;
        this.mGroup = group;
        this.mIsFirstInGroup = isFirstInGroup;
        this.mIsLastInGroup = isLastInGroup;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String mGroup) {
        this.mGroup = mGroup;
    }

    public boolean isFirstInGroup() {
        return mIsFirstInGroup;
    }

    public void setIsFirstInGroup(boolean mIsFirstInGroup) {
        this.mIsFirstInGroup = mIsFirstInGroup;
    }

    public boolean isLastInGroup() {
        return mIsLastInGroup;
    }

    public void setIsLastInGroup(boolean mIsLastInGroup) {
        this.mIsLastInGroup = mIsLastInGroup;
    }
}
