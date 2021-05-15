package com.example.infocovid.datalayer.model;

import com.example.infocovid.R;

public class MySettings {
    private boolean allowLocation;
    private boolean allowNotifications;
    private boolean enableWidget;

    /**
     *
     */
    public MySettings() {
        this.allowLocation = false;
        this.allowNotifications = true;
        this.enableWidget = false;
    }

    /**
     *
     * @param allowLocation
     * @param allowNotifications
     * @param enableWidget
     */
    public MySettings(boolean allowLocation, boolean allowNotifications, boolean enableWidget) {
        this.allowLocation = allowLocation;
        this.allowNotifications = allowNotifications;
        this.enableWidget = enableWidget;
    }

    /**
     *
     * @return
     */
    public boolean getAllowLocation() {
        return allowLocation;
    }

    /**
     *
     * @param useMyLocation
     */
    public void setAllowLocation(boolean useMyLocation) {
        this.allowLocation = useMyLocation;
    }

    /**
     *
     * @return
     */
    public boolean getAllowNotifications() {
        return allowNotifications;
    }

    /**
     *
     * @param allowNotifications
     */
    public void setAllowNotifications(boolean allowNotifications) {
        this.allowNotifications = allowNotifications;
    }

    /**
     *
     * @return
     */
    public boolean getEnableWidget() {
        return enableWidget;
    }

    /**
     *
     * @param enableWidget
     */
    public void setEnableWidget(boolean enableWidget) {
        this.enableWidget = enableWidget;
    }
}
