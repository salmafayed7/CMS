package com.example.cinemamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Jdbc {
    public static String signUp(String email, String password, String name, String phone, String query) {
       String userId = null;
       SQLConnection sqlconnection = SQLConnection.getInstance();
       try (Connection connection = sqlconnection.getConnection()) {
           if (connection == null) {
               throw new SQLException("Failed to establish connection");
           }

           try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
               preparedStatement.setString(1, email);
               preparedStatement.setString(2, password);
               preparedStatement.setString(3, name);
               preparedStatement.setString(4, phone);

               int result = preparedStatement.executeUpdate();
               if (result > 0) {
                   String selectLastInsertedId = "SELECT ID FROM Person WHERE email = ?";
                   try (PreparedStatement selectStatement = connection.prepareStatement(selectLastInsertedId)) {
                       selectStatement.setString(1, email);
                       try (ResultSet resultSet = selectStatement.executeQuery()) {
                           if (resultSet.next()) {
                               userId = resultSet.getString("ID");
                               System.out.println("Generated userId = " + userId);
                           } else {
                               System.err.println("No matching user found after insert.");
                           }
                       }
                   }
                  String inCustQuery="Insert Into customer (customerid,points) values(?, ?)";
                   try(PreparedStatement inCustStatement = connection.prepareStatement(inCustQuery)){
                       inCustStatement.setString(1,userId);
                       inCustStatement.setInt(2,0);
                       inCustStatement.executeUpdate();

                   }

               } else {
                   System.err.println("Insert failed, no rows affected.");
               }
           }


       } catch (SQLException e) {
           System.err.println("Error during signUp: " + e.getMessage());
           e.printStackTrace();
       }
       return userId;
   }

    public static boolean Updatepassword(String newp, String query, String userid) {
        SQLConnection sqlConnecter = SQLConnection.getInstance();
        try (Connection connection = sqlConnecter.getConnection()) {
            if (connection == null) {
                throw new SQLException("failed to establish connection");
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newp);
                preparedStatement.setString(2, userid);

                int result = preparedStatement.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String validateLogin(String email, String password, String query) {
        String userid = null;

        SQLConnection sqlConnector = SQLConnection.getInstance();
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                // throw new SQLException("Failed to establish a connection to the database.");
                System.out.println("Failed to establish a connection to the database.");
                return null; // Early return if the connection fails
            }
            try (PreparedStatement statement = connection.prepareStatement(query);) {
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    userid = resultSet.getString("ID");
                    //userid="P001";
                    return userid;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }

    public static ArrayList<Showtime> getShowtimes(String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        ArrayList<Showtime> showtimes = new ArrayList<>();
        try (Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Timestamp startTime = resultSet.getTimestamp("StartTime");
                    Timestamp endTime = resultSet.getTimestamp("EndTime");
                    String movieID = resultSet.getString("MovieID");
                    String showtimeID = resultSet.getString("ShowtimeID");

                    Showtime showtime = new Showtime(startTime, endTime, movieID, showtimeID);
                    showtimes.add(showtime);
                }
                if (showtimes.isEmpty()) {
                    System.out.println("No showtimes found in the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showtimes;
    }

    public static ObservableList<Booking> getBookingsByCustomerId(String customerId, String query) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();
        SQLConnection sqlConnecter = SQLConnection.getInstance();
        try (Connection connection = sqlConnecter.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("No bookings found for customer ID: " + customerId);
            } else {
                do {
                    String bookingId = rs.getString("BookingID");
                    String movieID = rs.getString("MovieId");
                    String movieTitle = rs.getString("Title");  // Get movie title from Movie table
                    Timestamp startTime = rs.getTimestamp("StartTime");
                    Timestamp endTime = rs.getTimestamp("EndTime");
                    String hallid = rs.getString("HallID");
                    double totalPrice = rs.getDouble("totalPrice");
                    boolean usePoints = rs.getBoolean("usePoints");

                    Showtime showtime = new Showtime(movieID, movieTitle, startTime, endTime, hallid);
                    Booking booking = new Booking(customerId, bookingId, showtime, totalPrice, usePoints);

                    bookings.add(booking);
                } while (rs.next());
            }

           /* while (rs.next()) {
                String bookingId = rs.getString("bookingId");
                String movieID = rs.getString("movieID");
                String movieTitle = rs.getString("movieTitle");  // Get movie title from Movie table
                Date startTime = rs.getDate("startTime");
                Date endTime = rs.getDate("endTime");
                String hallid= rs.getString("HallId");
                double totalPrice = rs.getDouble("totalPrice");
                boolean usePoints = rs.getBoolean("usePoints");

                Showtime showtime = new Showtime(movieID, movieTitle, startTime, endTime, hallid);
                Booking booking = new Booking(customerId,bookingId, showtime, totalPrice, usePoints);

                bookings.add(booking);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public static boolean UpdateEmail(String email, String query, String userid) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, userid);

                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static boolean UpdatePhoneNumber(String phone, String query, String userid) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, phone);
                statement.setString(2, userid);

                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static ArrayList<Movie> GetMovies(String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("No movies found in the database.");
                }
                do {
                    String title = resultSet.getString("Title");
                    System.out.println("Fetched movie title: " + title);
                    String movieID = resultSet.getString("MovieID");
                    String genre = resultSet.getString("Genre");
                    int duration = resultSet.getInt("Duration");
                    String actors = resultSet.getString("Actors");
                    String rating = resultSet.getString("Rating");
                    String director = resultSet.getString("Director");
                    Date rdate = resultSet.getDate("ReleaseDate");
                    String status = resultSet.getString("Status");

                    Movie movie = new Movie(title,genre,duration,actors,rating,rdate,director,status);
                    movie.movieID = movieID;
                    movies.add(movie);
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }


    public static ArrayList<Snack> GetSnack(String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        ArrayList<Snack> snacks = new ArrayList<>();
        try (Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                System.out.println("Executing query: " + query);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("No Snacks found in the database.");
                }else{
                    System.out.println("Snacks found:");
                    do {
                        String SName = resultSet.getString("SName");
                        String Sid = resultSet.getString("Sid");
                        double SPrice = resultSet.getDouble("SPrice");
                        String Flavor = resultSet.getString("Flavor");
                        String imagePath = resultSet.getString("ImagePaths");
                        Snack snack = new Snack(SName, SPrice, Flavor,imagePath);
                        snack.Sid = Sid;
                        snacks.add(snack);
                    } while (resultSet.next());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return snacks;
    }

    public static boolean checkAvailability(String row, String seatnum, String query) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        boolean isAvailable = false;

        try (Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, row);
                statement.setString(2, seatnum);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        isAvailable = resultSet.getBoolean("IsAvailable");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return isAvailable;

    }

    public static String getUserName(String userid) {

        SQLConnection sqlConnector = SQLConnection.getInstance();
        String userName = null;
        String query="SELECT name FROM Person WHERE id = ?";
        try(Connection connection = sqlConnector.getConnection();){
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userid);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    userName=resultSet.getString("Name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }

    public static int getUserPoints(String userid) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        int userPoints = -1;
        String query="SELECT Points FROM customer WHERE customerid = ?";
        try(Connection connection = sqlConnector.getConnection();){
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userid);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    userPoints=resultSet.getInt("Points");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userPoints;
    }


    public static boolean cancelBooking(String bookingid) {
        SQLConnection sqlConnector = SQLConnection.getInstance();
        String seatsQuery = "UPDATE Seat SET IsAvailable = 1 " +
                "WHERE SeatID IN (SELECT SeatID FROM BookingSeat WHERE BookingID = ?)";
        String delQuery = "DELETE FROM Booking WHERE BookingID = ?";

        try (Connection connection = sqlConnector.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(seatsQuery)) {
                statement.setString(1, bookingid);
                int seatUpdateResult = statement.executeUpdate();
                if (seatUpdateResult == 0) {
                    connection.rollback();
                    return false;
                }
            }
            try (PreparedStatement statement = connection.prepareStatement(delQuery)) {
                statement.setString(1, bookingid);
                int bookingResult = statement.executeUpdate();

                if (bookingResult == 0) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;

        } catch (SQLException e) {
            try (Connection connection = sqlConnector.getConnection()) {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                e.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }



    public static String getMaxBookingID() {
       SQLConnection sqlConnector = SQLConnection.getInstance();
       String maxBookingID = null;
       String query = "SELECT Max(BookingID) FROM Booking";
       try(Connection connection = sqlConnector.getConnection();){
           if (connection == null) {
               throw new SQLException("Failed to establish a connection to the database.");
           }
           try(PreparedStatement statement = connection.prepareStatement(query)) {
               ResultSet resultSet = statement.executeQuery();
               if (resultSet.next()) {
                   maxBookingID = resultSet.getString(1);
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
        if (maxBookingID == null) {
            return "B001"; // First booking
        }
        // Assuming the ID format is 'BXXX', where XXX is a number
        String prefix = maxBookingID.substring(0, 1); // 'B'
        String numberPart = maxBookingID.substring(1); // '003'

        int nextID = Integer.parseInt(numberPart) + 1;
        String nextBookingID = prefix + String.format("%03d", nextID); // Formats to 3 digits (e.g., '004')

        return nextBookingID;
   }

    public static boolean insertNewBooking(String BookingID, String CustomerID, String ShowtimeID, double price, boolean points){
        SQLConnection sqlConnector = SQLConnection.getInstance();
        String query = "INSERT INTO Booking (BookingID, CustomerID, ShowtimeID, TotalPrice, UsePoints, BookingTime) VALUES (?,?,?,?,?, SYSDATE())";
        try (Connection connection = sqlConnector.getConnection();) {
           if (connection == null) {
               throw new SQLException("Failed to establish a connection to the database.");
           }
           try (PreparedStatement statement = connection.prepareStatement(query)) {
               statement.setString(1, BookingID);
               statement.setString(2, CustomerID);
               statement.setString(3, ShowtimeID);
               statement.setDouble(4, price);
               statement.setBoolean(5, points);
               //statement.setDate(6, bookingDate);
               int result = statement.executeUpdate();
               if (result > 0) {
                   return true;
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return false;
   }

    public static boolean insertBookingSeat(String BookingID, String SeatID){
        if (BookingID == null || SeatID == null || BookingID.isEmpty() || SeatID.isEmpty()) {
            System.out.println("Invalid input: BookingID and SeatID must not be null or empty.");
            return false;
        }
        SQLConnection sqlConnector = SQLConnection.getInstance();
        String query = "INSERT INTO BookingSeat (BookingID, SeatID) VALUES (?,?)";
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, BookingID);
                statement.setString(2, SeatID);

                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getSeat(String row, String seatNum){
        SQLConnection sqlConnector = SQLConnection.getInstance();
        String seatID = null;
        String query = "SELECT SeatID FROM Seat WHERE row = ? and seatnum = ?";
        try(Connection connection = sqlConnector.getConnection();){
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, row);
                statement.setString(2, seatNum);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    seatID = resultSet.getString("SeatID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatID;
    }

    public static boolean updatePts(String userid, int points){
        SQLConnection sqlConnector = SQLConnection.getInstance();
        String query = "UPDATE customer SET points = ? WHERE CustomerID = ?";
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, points);
                statement.setString(2, userid);
                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean updateAvailableSeat(String seatID){
        SQLConnection sqlConnector = SQLConnection.getInstance();
        String query = "UPDATE seat SET IsAvailable = 0 WHERE seatID = ?";
        try (Connection connection = sqlConnector.getConnection();) {
            if (connection == null) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, seatID);

                int result = statement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}

