package com.xdcplus.scheduling.common.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class Payload implements Serializable {

    private Long id;
    private String jti;
    private String account;
    private List<String> authorities;
    private Byte userLevel;


}
