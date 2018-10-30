package com.grug.threadLocal;

import com.alibaba.fastjson.JSON;

import java.util.UUID;

/**
 * Created by feichen on 2018/7/29.
 */
public class SessionHandler {
    public static ThreadLocal<Session> session = new ThreadLocal<Session>();

    public static class Session {
        private String id;
        private String user;
        private String status;

        public Session() {
            this.id = UUID.randomUUID().toString();
            System.out.println(this.getId());
            this.user = "test";
            this.status = "create";
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public void createSession() {
        session.set(new Session());
    }

    public String getId() {
        return session.get().getId();
    }

    public String getUser() {
        return session.get().getUser();
    }

    public String getStatus() {
        return session.get().getStatus();
    }

    public void setStatus(String status) {
        session.get().setStatus(status);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SessionHandler sessionHandler = new SessionHandler();
                sessionHandler.createSession();
                System.out.println(JSON.toJSONString(SessionHandler.session.get()));
            }).start();
        }
    }
}

