package com.example.cinemamanagementsystem;
import java.util.*;
public class Movie {
  public int movieID;
  public String status;
  public String title;
  public String genre;
  public int duration;
  public String language;
  public String rating;
  public ArrayList<Showtime> showtimes;
  public ArrayList<String> actors;
  public String director;
  public Date releaseDate;

  public Movie(String title, String genre, int duration, String language, String rating, Date releaseDate, String director, String status) {
      this.title = title;
      this.genre = genre;
      this.duration = duration;
      this.language = language;
      this.rating = rating;
      this.director = director;
      this.releaseDate = releaseDate;
      this.status = status;
      showtimes = new ArrayList<>();
      actors = new ArrayList<>();
  }

    /*public boolean insert() {
        String query = "INSERT INTO MOVIE ( Title, Genre, Duration, Rating, Director, ReleaseDate, Status, Actors) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        String actorsString = String.join(",", actors);
        // Calling DatabaseConnection to insert the data
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,title );
            statement.setString(2,genre );
            statement.setInt(3,duration );
            statement.setDouble(4,rating );
            statement.setString(5,director );
            statement.setDate(6,new java.sql.Date(releaseDate.getTime()) );
            statement.setString(7,status );
            statement.setString(8,actorsString );
            statement.executeUpdate();
            return true; // Successfully saved
        } catch (SQLException e) {
            System.out.println("Error saving movie: " + e.getMessage());
            return false; // Error saving
        }
    }*/

}
