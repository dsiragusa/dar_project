package etu.upmc.fr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import etu.upmc.fr.entity.Account;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "address")
public class Address {
    private static final String ZIP_MSG = "Ce service est limité à la region l'Ile de France";
    private static final String NOTBLANK_MSG = "Veuillez saisir des données pour ce champ";

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = NOTBLANK_MSG)
    private String name;

    @NotBlank(message = NOTBLANK_MSG)
    private String line1;
    private String line2;

    @NotBlank(message = NOTBLANK_MSG)
    private String city;

    @Pattern(regexp = "^(7[578]|9[1-5])[0-9]{3}$", message = ZIP_MSG)
    private String zip;

    private String country = "FR";
    private Boolean main;

    @JsonIgnore
    @ManyToOne
    private Account account;

    public Address() {
        name = "Mon adresse";
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

        s += city + " " + zip + " France";

        return s;
    }
}
