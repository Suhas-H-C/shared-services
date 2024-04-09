package com.shared.info.dto;

import com.shared.info.annotations.Header;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class InternetProtocol implements Serializable {

    @Header(header = "Id")
    public int id;
    @Header(header = "First Name")
    public String first_name;
    @Header(header = "Last Name")
    public String last_name;
    @Header(header = "Email Id")
    public String email;
    @Header(header = "Gender")
    public String gender;
    @Header(header = "Ip Address")
    public String ip_address;
}
