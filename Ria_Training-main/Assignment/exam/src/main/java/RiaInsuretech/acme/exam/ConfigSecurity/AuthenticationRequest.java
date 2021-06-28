package RiaInsuretech.acme.exam.ConfigSecurity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class AuthenticationRequest implements Serializable {
    @Size(max = 6)
    private String username;
    @Size(max = 6)
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
