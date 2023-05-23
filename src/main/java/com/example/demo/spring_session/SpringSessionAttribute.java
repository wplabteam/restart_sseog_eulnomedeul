package com.example.demo.spring_session;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
public class SpringSessionAttribute  implements Serializable {

    @EmbeddedId
    private SpringSessionAttributeId id;

    @Lob
    @Column(name = "ATTRIBUTE_BYTES", nullable = false)
    private byte[] attributeBytes;

    public SpringSessionAttribute() {}

    public SpringSessionAttribute(SpringSessionAttributeId id, byte[] attributeBytes) {
        this.id = id;
        this.attributeBytes = attributeBytes;
    }

    public SpringSessionAttributeId getId() {
        return id;
    }

    public void setId(SpringSessionAttributeId id) {
        this.id = id;
    }

    public byte[] getAttributeBytes() {
        return attributeBytes;
    }

    public void setAttributeBytes(byte[] attributeBytes) {
        this.attributeBytes = attributeBytes;
    }
}

