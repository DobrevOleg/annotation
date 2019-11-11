package annotation;

import java.util.Date;
import java.util.List;

@ObjProperties(objType = ClientAttributes.OBJECT_TYPE_ID,parentId = 1)
public class Client extends Person{
    @Attribute(attrId = ClientAttributes.DEFAULT_LOCATION)
    private String defaultLocation;
    @Attribute(attrId = ClientAttributes.PASSWORD)
    private Date registrationDate;
    @AttList(attrId =ClientAttributes.AUTHORITY)
    private String authority="Client";
    @AttReference(attrId = 6)
    private List<Client> client;

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
