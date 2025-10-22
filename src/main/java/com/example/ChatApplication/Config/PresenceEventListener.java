package com.example.ChatApplication.Config;



import com.example.ChatApplication.model.User;
import com.example.ChatApplication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tracks online users by username -> sessionId set (simple approach).
 * This is in-memory; for clustering use Redis or DB-backed presence.
 */
@Component
@RequiredArgsConstructor
public class PresenceEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(PresenceEventListener.class);

    // map username -> sessionId -> true
    private final Map<String, ConcurrentHashMap.KeySetView<String, Boolean>> online = new ConcurrentHashMap<>();

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getUser() != null ? sha.getUser().getName() : null;
        String sessionId = sha.getSessionId();
        if (username != null) {
            online.computeIfAbsent(username, u -> ConcurrentHashMap.newKeySet()).add(sessionId);
            LOG.info("User connected: {} (session {})", username, sessionId);
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getUser() != null ? sha.getUser().getName() : null;
        String sessionId = sha.getSessionId();
        if (username != null) {
            var set = online.get(username);
            if (set != null) {
                set.remove(sessionId);
                if (set.isEmpty()) {
                    online.remove(username);
                    LOG.info("User disconnected: {} (last session {})", username, sessionId);
                } else {
                    LOG.info("User session removed: {} (session {})", username, sessionId);
                }
            }
        }
    }

    // Utility for controllers
    public boolean isOnline(String username) {
        return online.containsKey(username);
    }

    public java.util.Set<String> getOnlineUsers() {
        return online.keySet();
    }
}

