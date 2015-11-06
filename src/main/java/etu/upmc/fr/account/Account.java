package etu.upmc.fr.account;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import etu.upmc.fr.address.Address;
import etu.upmc.fr.service.Service;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@NamedQuery(name = Account.FIND_BY_EMAIL, query = "select a from Account a where a.email = :email")
public class Account implements java.io.Serializable {

	public static final String FIND_BY_EMAIL = "Account.findByEmail";

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
    @NotBlank
    @Email
	private String email;
	
	@JsonIgnore
    @NotBlank
	private String password;

	@Column(nullable = false)
	private String role = "ROLE_USER";

    @NotBlank
	private String firstName;

    @NotBlank
	private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signupDate;

	@OneToMany(mappedBy = "account")
    @NotEmpty
    @Valid
	@JsonIgnore
	private List<Address> addresses;

    @OneToMany(mappedBy = "requestor")
    private Set<Service> requests;

    @ManyToMany(mappedBy = "offerors", targetEntity = Service.class)
    private Set<Service> offers;

    @OneToMany(mappedBy = "contractor")
    private Set<Service> contracts;

    public Account() {
        addresses = new ArrayList<>();
        setSignupDate(new Date());
	}

	public Long getId() {
		return id;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Set<Service> getRequests() {
        return requests;
    }

    public void setRequests(Set<Service> requests) {
        this.requests = requests;
    }

    public Set<Service> getOffers() {
        return offers;
    }

    public void setOffers(Set<Service> offers) {
        this.offers = offers;
    }

    public Set<Service> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Service> contracts) {
        this.contracts = contracts;
    }
}
