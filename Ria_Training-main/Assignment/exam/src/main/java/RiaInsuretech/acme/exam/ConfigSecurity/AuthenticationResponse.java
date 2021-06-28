package RiaInsuretech.acme.exam.ConfigSecurity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class AuthenticationResponse implements Serializable {
    @Size(max = 6000)
    private final String Jwt;

    public AuthenticationResponse(String jwt) {
        Jwt = jwt;
    }

    public String getJwt() {
        return Jwt;
    }

}
