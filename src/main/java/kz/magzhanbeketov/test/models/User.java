package kz.magzhanbeketov.test.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 255)
    private String username;

    @Column(nullable = false,name = "full_name", length = 500)
    private String fullName;

    @Column(nullable = false, columnDefinition = "text")
    private String password;

    @Column(name = "updated_at", columnDefinition = "timestamp")
    private Calendar updatedAt;

    @Column(name = "created_at", columnDefinition = "timestamp",nullable = false)
    private Calendar createdAt;
}
