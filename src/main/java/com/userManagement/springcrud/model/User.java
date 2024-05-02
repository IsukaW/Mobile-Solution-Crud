package com.userManagement.springcrud.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_db")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Guaranteed to be sequential
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "Application_key", nullable = false)
    private String appKey;

    @Column(name = "Application_Name", nullable = false)
    private String appName;

    @Column(name = "Creation_Date", updatable = false)
    private LocalDateTime creationDate; // Use LocalDateTime for time zone awareness

    @PrePersist // Automatically update creationDate before persisting a new user
    public void setCreationDate() {
        if (this.creationDate == null) { // Only set creationDate if it hasn't been set before
            this.creationDate = LocalDateTime.now();
        }
    }

    @Column(name = "Last_Modified_Date")
    private LocalDateTime lastModifiedDate;

    @PreUpdate // Update lastModifiedDate on entity update
    public void setLastModifiedDate() {
        this.lastModifiedDate = LocalDateTime.now();
    }

}
