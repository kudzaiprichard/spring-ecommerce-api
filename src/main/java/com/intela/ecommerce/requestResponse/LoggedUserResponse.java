package com.intela.ecommerce.requestResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobileNumber;
}
