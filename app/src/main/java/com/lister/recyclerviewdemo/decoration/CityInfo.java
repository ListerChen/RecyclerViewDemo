
package com.lister.recyclerviewdemo.decoration;

public class CityInfo {

    private String mCityName;
    private String mPinYin;
    private String mGroup;
    private String mCityID;
    private boolean mIsFirstInGroup;
    private boolean mIsLastInGroup;

    public CityInfo(String cityName, String pinYin, String cityID,
                    boolean isFirstInGroup, boolean isLastInGroup) {
        this.mCityName = cityName;
        this.mPinYin = pinYin;
        this.mGroup = mPinYin.substring(0, 1).toUpperCase();
        this.mCityID = cityID;
        this.mIsFirstInGroup = isFirstInGroup;
        this.mIsLastInGroup = isLastInGroup;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getPinYin() {
        return mPinYin;
    }

    public void setPinYin(String mGroup) {
        this.mPinYin = mGroup;
    }

    public String getGroup() {
        return mGroup;
    }

    public String getCityID() {
        return mCityID;
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
