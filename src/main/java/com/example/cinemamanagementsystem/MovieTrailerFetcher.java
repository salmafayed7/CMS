package com.example.cinemamanagementsystem;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MovieTrailerFetcher {
    private static final String apikey ="0d4f8e4c4b5d1f62404d3b7fa9c56373";
    private static final String baseURL = "https://api.themoviedb.org/3";

    public static String getMovieIdFromName(String movieName) {
        try {
            String apiUrl = baseURL + "/search/movie?api_key=" + apikey + "&query=" + URLEncoder.encode(movieName, StandardCharsets.UTF_8);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            if (results.size() > 0) {
                JsonObject movie = results.get(0).getAsJsonObject();
                String movieId = movie.get("id").getAsString();
                return movieId;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    public static String fetchTrailer(String movieId) {
        try {
            String apiUrl = baseURL + "/movie/" + movieId + "/videos?api_key=" + apikey;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return null;
        }
    }

    public static String extractTrailerUrl(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");

        for (int i = 0; i < results.size(); i++) {
            JsonObject video = results.get(i).getAsJsonObject();
            String type = video.get("type").getAsString();
            String site = video.get("site").getAsString();

            if ("Trailer".equalsIgnoreCase(type) && "YouTube".equalsIgnoreCase(site)) {
                String videoKey = video.get("key").getAsString();
                return "https://www.youtube.com/embed/" + videoKey;
            }

        }

        return null;
    }

    public static String getTrailerUrlByName(String movieName) {
        String movieId = getMovieIdFromName(movieName);

        if (movieId == null) {
            return "Movie not found";
        }

        String jsonResponse = fetchTrailer(movieId);

        if (jsonResponse == null) {
            return "Error fetching trailer";
        }

        String trailerUrl = extractTrailerUrl(jsonResponse);

        if (trailerUrl == null) {
            return "No trailer available";
        }

        return trailerUrl;
    }

}
