package info.schuwan.projectone.JWT;

import javax.persistence.*;

@Table(name = "mm_user", indexes = {
        @Index(name = "mm_user_profile_id_key", columnList = "profile_id", unique = true),
        @Index(name = "mm_user_username_key", columnList = "username", unique = true)
})
@Entity
public class MmUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MmRole role;

    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false)
    private MmProfile profile;

    public MmUser() {
    }

    public MmUser(Integer id, String username, String hash, MmRole role, MmProfile profile) {
        this.id = id;
        this.username = username;
        this.hash = hash;
        this.role = role;
        this.profile = profile;
    }

    public MmProfile getProfile() {
        return profile;
    }

    public void setProfile(MmProfile profile) {
        this.profile = profile;
    }

    public MmRole getRole() {
        return role;
    }

    public void setRole(MmRole role) {
        this.role = role;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}