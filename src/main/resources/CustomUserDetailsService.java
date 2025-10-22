import com.example.ChatApplication.Repository.UserRepository;
import com.example.ChatApplication.model.User;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    public CustomUserDetailsService(UserRepository repo){ this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user: " + username));
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
}

