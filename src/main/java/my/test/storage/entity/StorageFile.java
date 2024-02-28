package my.test.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
@Table(name = "T_FILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    String name;
    @Column(name = "INFO")
    String info;
    @Column(name = "DATA")
    @Lob
    private byte[] data;
    @Column(name = "DATE")
    @CreationTimestamp
    private LocalDateTime created;

    public String getCreatedMediumString() {
        return DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM)
                .format(created);
    }
}
