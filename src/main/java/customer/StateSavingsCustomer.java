package customer;

import com.fexco.prizebonds.customer.util.CustomerUtils;
import com.fsg.prizebonds.common.domain.customer.ContactDetails;
import com.fsg.prizebonds.common.domain.customer.CustomerNumber;
import com.fsg.prizebonds.common.domain.name.PersonName;
import com.fsg.prizebonds.common.domain.person.Gender;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "state_saving_customer")
public class StateSavingsCustomer implements Serializable {

    private static final long serialVersionUID = 2628568688296055596L;

    private Long id;

    private Date timestamp;
    private Date validationDate;

    private Long sourceId;

    private String status;
    private String ppsNumber;

    private Date dateOfBirth;

    private String title;
    private String firstName;
    private String middleName;
    private String surname;

    private PostalAddress address;

    private String phoneNumber;
    private String emailAddress;

    public StateSavingsCustomer() {
        // default constructor for hibernate
        this.address = new PostalAddress();
    }

    public StateSavingsCustomer(Long id, String firstName, String surname, String ppsNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.ppsNumber = ppsNumber;
        this.address = new PostalAddress();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "object_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Column(name = "validation_date")
    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    @Column(name = "source_object_id")
    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "pps_number")
    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Embedded
    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    @Transient
    public CustomerNumber getCustomerNumber() {
        return CustomerNumber.unknown();
    }

    @Override
    @Transient
    public PersonName getFullName() {
        return new PersonName(title, firstName, middleName, null, surname, null);
    }

    @Override
    @Transient
    public ContactDetails getContactDetails() {
        return new ContactDetails(emailAddress, phoneNumber,null);
    }

    @Override
    @Transient
    public LocalDate checkValidationDate() {
        return validationDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    @Transient
    public Gender getGender() {
        // No gender is recorded in the state savings table so derive from customer title
        return CustomerUtils.deriveGenderFromTitle(title);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(id)
                .append(title)
                .append(firstName)
                .append(middleName)
                .append(surname)
                .append(address)
                .toString();
    }

}
