package annotation;

public class Person extends Entity {
    @Attribute(attrId = ClientAttributes.EMAIL)
    private String email;
    @Attribute(attrId = ClientAttributes.AUTHORITY)
    protected Integer rating;
    @Attribute(attrId = ClientAttributes.LAST_NAME)
    private String lastName;
    @Attribute(attrId = ClientAttributes.PHONE_NUMBER)
    private Long phone;

    public long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
