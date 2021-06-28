package RiaInsuretech.acme.exam.ConfigSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import RiaInsuretech.acme.exam.MyRepositories.ExaminerRepository;
import RiaInsuretech.acme.exam.MyRepositories.MyUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private ExaminerRepository examinerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.equals("ACME_ADMIN")) {
            return User.withUsername("ACME_ADMIN").password("admin").roles("ADMIN").build();

        } else if (myUserRepository.existsById(s)) {
            String password = myUserRepository.findById(s).get().getPassword();
            return User.withUsername(s).password(password).roles("USER").build();
        } else if (examinerRepository.existsById(s)) {
            String password = examinerRepository.findById(s).get().getPassword();
            return User.withUsername(s).password(password).roles("EXAMINER").build();
        } else {
            throw new UsernameNotFoundException("Username not Found");
        }

    }
}
