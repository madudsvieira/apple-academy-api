package br.senac.sp.appleacademyapi.mentor;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @NotBlank(message = "field required")
    private String name;

    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private Expertise expertise;

    @Builder.Default()
    private Boolean active = true;

    @Builder.Default()
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

}
