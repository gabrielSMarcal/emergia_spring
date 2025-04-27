package br.com.emergia.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class ApiCotacaoDolar {

    private static final String API_URL = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=";

    private static String getApiKey() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = ApiCotacaoDolar.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IOException("Arquivo config.properties não encontrado no classpath.");
            }
            properties.load(inputStream);
        }
        String apiKey = properties.getProperty("API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IOException("API_KEY não encontrada no arquivo de configuração.");
        }
        return apiKey;
    }

    public static double getCotacaoDolar() throws IOException {
        String apiKey = getApiKey();
        String urlString = API_URL + apiKey + "&symbols=USD";
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int respondeCode = connection.getResponseCode();
        if (respondeCode == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = objectMapper.readTree(connection.getInputStream());

            JsonNode usdNode = responseJson.path("rates").path("USD");
            if (usdNode.isMissingNode() || !usdNode.isTextual()) {
                throw new IOException("Erro ao encontrar campo 'USD'");
            }

            return Double.parseDouble(usdNode.asText());
        } else {
            throw new IOException("Erro ao obter cotação do dólar: " + respondeCode);
        }
    }
}
