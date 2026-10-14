package com.educonnect.educonnectbackend.dto;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String city;
    private String state;
    private String pincode;

}
