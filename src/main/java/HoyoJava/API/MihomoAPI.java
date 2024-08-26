package HoyoJava.API;

/**
 * This file is taken originally from:
 * https://github.com/wuliao97/Mihomo.API-withJava
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.net.*;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;


public class MihomoAPI {
    private final Language Language;
    private final String BASE_URL = "https://api.mihomo.me/sr_info_parsed";
    private final String ASSET_URL = "https://raw.githubusercontent.com/Mar-7th/StarRailRes/master";


    public MihomoAPI(final Language Language) {
        this.Language = Language;
    }


    public static String requests(final String material_url) throws Exception {
        final URL url = new URI(material_url).toURL();
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(false);
        connection.setDoOutput(true);
        connection.connect();

        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();
        }
        throw new Exception();
    }


    public String fetch(final String UID) throws Exception {
        return requests(BASE_URL + "/" + UID + "?lang=" + Language);
    }

    public JsonNode convertToNode(final String src) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(src);
    }

    public StringBuilder getIcon(final String path){
        StringBuilder sb = new StringBuilder(this.ASSET_URL);
        sb.append("/").append(path);
        return sb;
    }

    public JsonNode getDefault(final String UID) throws Exception {
        String src = fetch(UID);
        return new ObjectMapper().readTree(src);
    }

    public JsonNode getPlayer(final JsonNode src){
        return src.get("player");
    }

    public JsonNode getCharacters(final JsonNode src){
        return src.get("characters");
    }

    public JsonNode D_parse(final JsonNode obj, String... keys){
        JsonNode result = obj;
        for (final String key : keys){
            result = result.get(key);
        }
        return result;
    }
}
