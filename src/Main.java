import annotation.Client;
import annotation.EntityManager;
import annotation.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client =new Client();
        client.setEmail("olegdobrev@gmail.com");
        client.setDefaultLocation("Odessa");
        client.setLastName("Dobrev");
        //client.setName("Oleg");
        client.setPhone(970535827L);
        //client.setRating(5);
        List<Client> list=new ArrayList<Client>();
        Client client1=new Client();
        client1.setId(2L);
        list.add(client1);
        list.add(new Client());
        client.setClient(list);
        EntityManager<Client> entityManager=new RequestBuilder<Client>();
        entityManager.save(client);

    }
}
