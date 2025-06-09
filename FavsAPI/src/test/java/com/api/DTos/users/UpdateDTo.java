package com.api.DTos.users;

public class UpdateDTo {
    private UserDTO user;
    public String twitter_username;
    public String facebook_username;
    public String pic;
    public boolean profanity_filter;

    public UpdateDTo() {
    }

    public UpdateDTo(UserDTO user, String twitter_username, String facebook_username, String pic, boolean profanity_filter) {
        this.user = user;
        this.twitter_username = twitter_username;
        this.facebook_username = facebook_username;
        this.pic = pic;
        this.profanity_filter = profanity_filter;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public void setTwitter_username(String twitter_username) {
        this.twitter_username = twitter_username;
    }

    public String getFacebook_username() {
        return facebook_username;
    }

    public void setFacebook_username(String facebook_username) {
        this.facebook_username = facebook_username;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isProfanity_filter() {
        return profanity_filter;
    }

    public void setProfanity_filter(boolean profanity_filter) {
        this.profanity_filter = profanity_filter;
    }
}
