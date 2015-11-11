package etu.upmc.fr.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import etu.upmc.fr.account.Account;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String line1;
    private String line2;

    @NotBlank
    private String city;

    @Length(min = 5, max = 5)
    @Pattern(regexp = "^(7[578]|9[1-5])[0-9]{3}$")
    private String zip;

    private String country = "FR";
    private Boolean main;

    @JsonIgnore
    @ManyToOne
    private Account account;

    public Address() {
        name = "New address";
    }

    public Long getId() {
        return id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    @Override
    public String toString() {
        String s = line1 + " ";

        if ( ! line2.isEmpty()) {
            s += line2 + " ";
        }

        s += city + " " + zip + " " + country;

        return s;
    }
}
