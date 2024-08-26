package HoyoJava.Clients;

import java.util.ArrayList;
import HoyoJava.API.Language;
import HoyoJava.API.MihomoAPI;

public class ClientManager {
    private static ArrayList<Client> clients = new ArrayList<>();
    public static final MihomoAPI API = new MihomoAPI(Language.en);

    public static void add(Client client) {
        clients.add(client);
    }

    public static boolean contains(Client client) {
        return clients.contains(client);
    }

    public static void removeClient(Client client) {
        clients.remove(client);
    }
}