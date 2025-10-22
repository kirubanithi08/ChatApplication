package com.example.ChatApplication.Controller;



import com.example.ChatApplication.Config.PresenceEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/presence")
@RequiredArgsConstructor
public class PresenceController {
    private final PresenceEventListener presence;

    @GetMapping("/online")
    public Set<String> onlineUsers() {
        return presence.getOnlineUsers();
    }

    @GetMapping("/isOnline/{username}")
    public boolean isOnline(@PathVariable String username) {
        return presence.isOnline(username);
    }
}

