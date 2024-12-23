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
}
