package se.lexicon.petclinic.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Visit {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String visitId;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private Pet pet;
    private LocalDate visitDate;
    private String description;

    public Visit() {
    }

    public Visit(String visitId, Pet pet, LocalDate visitDate, String description) {
        this.visitId = visitId;
        this.pet = pet;
        this.visitDate = visitDate;
        this.description = description;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(visitId, visit.visitId) &&
                Objects.equals(pet, visit.pet) &&
                Objects.equals(visitDate, visit.visitDate) &&
                Objects.equals(description, visit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, pet, visitDate, description);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitId='" + visitId + '\'' +
                ", pet=" + pet +
                ", visitDate=" + visitDate +
                ", description='" + description + '\'' +
                '}';
    }
}
