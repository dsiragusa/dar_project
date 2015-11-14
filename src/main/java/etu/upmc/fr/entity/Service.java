package etu.upmc.fr.entity;

import etu.upmc.fr.annotations.MyDateTime;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service implements java.io.Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "{notBlank.message}")
    @Length(min = 5, max = 100, message = "{length.message}")
    private String title;

    @NotBlank(message = "{notBlank.message}")
    @Length(min = 10, message = "{length.message}")
    private String description;

    @ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "{notBlank.message}")
    private Category category;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<State> states;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account requestor;

    @ManyToMany(targetEntity = Account.class, fetch = FetchType.EAGER)
    private Set<Account> offerors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account contractor;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "{notBlank.message}")
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{notBlank.message}")
    private Date biddingDeadline;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{notBlank.message}")
    private Date serviceDeadline;

    public Service() {

        setOfferors(new HashSet<Account>());
    }

    public Long getId() {
        return id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);

        tag.addService(this);
    }

    public List<State> getStates() {
        return states;
    }

    public State getCurrentState() {
        return states.get(states.size() - 1);
    }

    public boolean canBid(Account offeror) {
        if (offeror.equals(requestor)) {
            return false;
        }

        if ( ! getCurrentState().getCode().equals(State.BIDDING)) {
            return false;
        }

        return true;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Account getRequestor() {
        return requestor;
    }

    public void setRequestor(Account requestor) {
        this.requestor = requestor;
    }

    public Set<Account> getOfferors() {
        return offerors;
    }

    public void setOfferors(Set<Account> offerors) {
        this.offerors = offerors;
    }

    public void addOfferor(Account offeror) throws Exception {
        if ( ! canBid(offeror)) {
            throw new Exception("Cannot bid for this request");
        }

        this.offerors.add(offeror);
    }

    public Account getContractor() {
        return contractor;
    }

    public void setContractor(Account contractor) throws Exception {
        if (contractor.equals(requestor)) {
            throw new Exception("Contractor cannot be the same account as requestor");
        }

        this.contractor = contractor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    public void setBiddingDeadline(Date biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
    }

    public Date getServiceDeadline() {
        return serviceDeadline;
    }

    public void setServiceDeadline(Date serviceDeadline) {
        this.serviceDeadline = serviceDeadline;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
