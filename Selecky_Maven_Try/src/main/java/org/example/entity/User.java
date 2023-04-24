package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(name = "getAllUsers",query = "SELECT * FROM susers")
@NamedNativeQuery(name = "deleteAllUsers", query = "DELETE  FROM susers")
@Table(name = "SUSERS")
@Entity
public class User {
    @Id
    @Column(name = "USER_ID", unique = true)
    private Integer id;
    @Column(name = "USER_GUID", unique = true)
    private String guid;
    @Column(name = "USER_NAME")
    private String name;
}
