package com.xdcplus.vendorperm.common.pojo.dto.auth;

import com.xdcplus.vendorperm.constants.Oauth2Constant;
import lombok.Data;

import java.io.Serializable;

@Data
public class Oauth2TokenDTO implements Serializable {

    private String grant_type;

    private String client_id;

    private String client_secret;

    private String username;

    private String password;

    public Oauth2TokenDTO(String username, String password) {
        this.client_id = Oauth2Constant.OAUTH2_CLIENT;
        this.client_secret = Oauth2Constant.OAUTH2_CLIENT_SECRET;
        this.grant_type = Oauth2Constant.AUTHORIZED_GRANT_TYPES[0];
        this.username = username;
        this.password = password;
    }


}
