package etu.upmc.fr.entity;

import etu.upmc.fr.annotations.MyDateTime;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zomboris on 11/11/15.
 */


@Entity
@Table(name = "verificationToken")
@NamedQuery(name = VerificationToken.FIND_BY_TOKEN, query = "select a from VerificationToken a where a.token = :token")
public class VerificationToken implements java.io.Serializable {
    public static final String FIND_BY_TOKEN = "VerificationToken.findByToken";
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    @MyDateTime
    private Date expiryDate;

    public VerificationToken(){
    }

    public VerificationToken(String token, Account account) {
        this.token = token;
        this.account = account;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // standard getters and setters
}