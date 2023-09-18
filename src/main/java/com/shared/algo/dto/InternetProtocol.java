package com.shared.algo.dto;

import com.shared.algo.annotations.FiledHeader;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class InternetProtocol implements Serializable {

    @FiledHeader(header = "Id")
    public int id;
    @FiledHeader(header = "First Name")
    public String first_name;
    @FiledHeader(header = "Last Name")
    public String last_name;
    @FiledHeader(header = "Email Id")
    public String email;
    @FiledHeader(header = "Gender")
    public String gender;
    @FiledHeader(header = "Ip Address")
    public String ip_address;
}
