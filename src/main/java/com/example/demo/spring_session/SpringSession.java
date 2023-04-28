package com.example.demo.spring_session;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "SPRING_SESSION")
public class SpringSession {

    @Id
    @Column(name = "PRIMARY_ID")
    private String primaryId;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "CREATION_TIME")
    private Long creationTime;

    @Column(name = "LAST_ACCESS_TIME")
    private Long lastAccessTime;

    @Column(name = "MAX_INACTIVE_INTERVAL")
    private Integer maxInactiveInterval;

    @Column(name = "EXPIRY_TIME")
    private Long expiryTime;

    @Column(name = "PRINCIPAL_NAME")
    private String principalName;

    @Lob
    @Column(name = "SESSION_BYTES", nullable = false)
    private byte[] sessionBytes = new byte[0];

    // getters and setters
}



