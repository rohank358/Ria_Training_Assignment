package RiaInsuretech.acme.exam;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse implements Serializable {
    @JsonProperty("jwt")
    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse() {

    }

    public String getJwt() {
        return jwt;
    }

}
