package com.example.demo.spring_session;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class SpringSessionAttributeId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "SESSION_PRIMARY_ID", nullable = false)
    private String sessionPrimaryId;

    @Column(name = "ATTRIBUTE_NAME", nullable = false)
    private String attributeName;

    public SpringSessionAttributeId() {}

    public SpringSessionAttributeId(String sessionPrimaryId, String attributeName) {
        this.sessionPrimaryId = sessionPrimaryId;
        this.attributeName = attributeName;
    }

    public String getSessionPrimaryId() {
        return sessionPrimaryId;
    }

    public void setSessionPrimaryId(String sessionPrimaryId) {
        this.sessionPrimaryId = sessionPrimaryId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringSessionAttributeId that = (SpringSessionAttributeId) o;
        return Objects.equals(sessionPrimaryId, that.sessionPrimaryId) && Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionPrimaryId, attributeName);
    }
}

