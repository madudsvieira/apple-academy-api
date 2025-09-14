package br.senac.sp.appleacademyapi.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.senac.sp.appleacademyapi.cohort.Cohort;
import br.senac.sp.appleacademyapi.mentor.Expertise;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private Expertise expertise;

    @Builder.Default()
    private Boolean active = true;

    private String studentId;

    @Enumerated(EnumType.STRING)
    private DeliveryMode deliveryMode;

    @Enumerated(EnumType.STRING)
    private Program program;

    private LocalDate birthDate;

    @Builder.Default()
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @ManyToOne
    private Cohort cohort;
    
}
