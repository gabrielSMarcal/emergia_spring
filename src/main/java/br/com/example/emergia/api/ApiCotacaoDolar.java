package br.com.example.emergia.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class ApiCotacaoDolar {

    private static final String API_URL = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=";

    private static String getApiKey() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/config.properties")) {
            properties.load(fis);
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
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Processa a resposta JSON como string
            String responseBody = response.toString();
            String searchKey = "\"USD\":";
            int startIndex = responseBody.indexOf(searchKey) + searchKey.length();
            int endIndex = responseBody.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = responseBody.indexOf("}", startIndex);
            }

            // Extrai e converte o valor da cotação
            String cotacaoString = responseBody.substring(startIndex, endIndex).trim();
            cotacaoString = cotacaoString.replace("\"", "");
            return Double.parseDouble(cotacaoString);
        } else {
            throw new IOException("Erro ao obter cotação do dólar: " + respondeCode);
        }
    }
}
