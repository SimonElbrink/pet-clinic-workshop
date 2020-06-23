package se.lexicon.petclinic.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Owner {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String OwnerId;

    private String firstName;
    private String LastName;
    private String address;

    @Column(unique = true)
    private String telephone;

    public Owner() {
    }

    public Owner(String OwnerId, String firstName, String lastName, String address, String telephone) {
        this.OwnerId = OwnerId;
        this.firstName = firstName;
        LastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(OwnerId, owner.OwnerId) &&
                Objects.equals(firstName, owner.firstName) &&
                Objects.equals(LastName, owner.LastName) &&
                Objects.equals(address, owner.address) &&
                Objects.equals(telephone, owner.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OwnerId, firstName, LastName, address, telephone);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "OwnerId='" + OwnerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
