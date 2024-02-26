package my.test.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "T_ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageRole implements GrantedAuthority {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME", unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
