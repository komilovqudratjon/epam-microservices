package com.epam.upskill.authenticationservice.model;

import com.epam.upskill.authenticationservice.model.AbsEntity;
import com.epam.upskill.authenticationservice.model.RoleName;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: Entity class for TrainingType.
 * @date: 08 November 2023 $
 * @time: 5:17 AM 26 $
 * @author: Qudratjon Komilov
 */

@Entity
@Table(name = "user_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSession extends AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id", nullable = false, unique = true)
    private String sessionId;

//    @ManyToOne
    private Long userId;

    private Date lastAccessOnline;

    @Column(columnDefinition = "TEXT")
    private String accessToken;

    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

//    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    private RoleName role = RoleName.ROLE_USER;
}
