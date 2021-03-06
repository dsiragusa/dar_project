package etu.upmc.fr.entity;

import javax.persistence.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import etu.upmc.fr.annotations.MyDateTime;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "account")
@DynamicInsert
public class Account implements java.io.Serializable {
	private static final String EMAIL_MSG = "L'\''adresse saisi n'\''est pas bien formatté";
	private static final String NOTBLANK_MSG = "Veuillez saisir des données pour ce champ";

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
    @NotBlank(message = NOTBLANK_MSG)
    @Email(message = EMAIL_MSG)
	private String email;

	@Column(columnDefinition = "boolean default false")
	private boolean emailValidated;

	@JsonIgnore
    @NotBlank(message = NOTBLANK_MSG)
	private String password;

	@Column(nullable = false)
	private String role = "ROLE_USER";

    @NotBlank(message = NOTBLANK_MSG)
	private String firstName;

    @NotBlank(message = NOTBLANK_MSG)
	private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @MyDateTime
    private Date signupDate;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @NotEmpty
    @Valid
	private List<Address> addresses;

    @OneToMany(mappedBy = "requestor", fetch = FetchType.EAGER)
    private Set<Service> requests;

    @ManyToMany(mappedBy = "offerors", targetEntity = Service.class, fetch = FetchType.EAGER)
    private Set<Service> offers;

    @OneToMany(mappedBy = "contractor", fetch = FetchType.EAGER)
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

	public boolean isEmailValidated() {
		return emailValidated;
	}

	public void setEmailValidated(boolean emailValidated) {
		this.emailValidated = emailValidated;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public int hashCode() {
		if (id == null && email == null) {
			return -1;
		}

		return email.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ( ! (o instanceof Account)) {
			return false;
		}

		return email.equals(((Account)o).getEmail());
	}

}
