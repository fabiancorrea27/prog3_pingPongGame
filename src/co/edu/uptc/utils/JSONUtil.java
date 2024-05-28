package co.edu.uptc.utils;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.pojos.ClientPojo;

public class JSONUtil {

    public static ClientPojo loadClientPojo(String jsonString) {
        ClientPojo clientPojo = new ClientPojo();
        Gson gson = new Gson();
        Type type = new TypeToken<ClientPojo>() {
        }.getType();
        clientPojo = gson.fromJson(jsonString, type);
        return clientPojo;
    }

    public static String writeClientPojo(ClientPojo clientPojo) {
        Gson gson = new Gson();
        String json = gson.toJson(clientPojo);
        return json;
    }
}
