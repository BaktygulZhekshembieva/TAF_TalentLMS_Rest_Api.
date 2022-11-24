package com.digital_nomads.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id;
    private String login;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    @JsonProperty("restrict_email")
    private String restrictEmail;
    @JsonProperty("user_type")
    private String userType;
    private String timezone;
    private String language;
    private String status;
    @JsonProperty("deactivation_date")
    private String deactivationDate;
    private String level;
    private String points;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("last_updated_timestamp")
    private String lastUpdatedTimestamp;
    private String avatar;
    private String bio;
    @JsonProperty("login_key")
    private String loginKey;
}
