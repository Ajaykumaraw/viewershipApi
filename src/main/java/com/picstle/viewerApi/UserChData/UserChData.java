package com.picstle.viewerApi.UserChData;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserChData {
    @Id
    private String userName;
    private String chName;
    private String channelId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChannelId() {
        return channelId;
    }

    @Override
    public String toString() {
        return "UserChData{" +
                "userName='" + userName + '\'' +
                ", chName='" + chName + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}
