package com.pluralsight.Persistance;

import com.pluralsight.Model.Actor;
import com.pluralsight.Model.Category;
import com.pluralsight.Model.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 The purpose of this class

    The DataManager class is responsible for handling all communication with the database in my application.
    It contains the SQL queries and methods that retrieve information, such as actors, films, and categories.
    By keeping all database logic in this one class, the rest of the program stays organized and easier to maintain.
    The user interface does not need to know how the database works—it simply calls methods
    from DataManager to get the data it needs.
    This separation makes the code cleaner, reusable, and easier to update in the future.
 */

public class DataManager {

    private BasicDataSource ds;

    public DataManager(BasicDataSource ds) {
        this.ds = ds;
    }

    public List<Category> getAllCategories() throws SQLException {

        List<Category> categories = new ArrayList<>();

        String query = """
                SELECT
                category_id,
                name
                from category""";

        try (
                Connection connection = this.ds.getConnection(); // opens a connection to the database
                PreparedStatement statement = connection.prepareStatement(query); // prepares the SQL query to run safely
                ResultSet results = statement.executeQuery() // runs the query and stores the rows the database returns
        ) {
            while (results.next()) { // loop goes through each row one by one. for each row, it
                int id = results.getInt("category_id"); // Gets the category id
                String name = results.getString("name"); // and name
                Category c = new Category(id, name);// then it create a new category object with that info
                categories.add(c);// lastly it adds the category object to a list so the program can use it later
            }

        }

        return categories;
    }

    public List<Actor> searchActorsByName(String name) throws SQLException {

        List<Actor> actors = new ArrayList<>();

        String query = """
                SELECT actor_id, first_name, last_name
                FROM actor
                WHERE first_name LIKE ? OR last_name LIKE ?
                """;

        try ( // this is the not nested one both works
                Connection connection = this.ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {

            String like = "%" + name + "%"; // so this code says add % symbols before and after the search term so the database will match any actor whose first or last name contains the text the user typed
            /*
            the below code means safely put this search term into the SQL query for the '1' firstName and '2' lastName columns
             */
            statement.setString(1, like);
            statement.setString(2, like);
            ResultSet results = statement.executeQuery();
            {
                while (results.next()) {
                    int id = results.getInt("actor_id");
                    String firstName = results.getString("first_name");
                    String lastName = results.getString("last_name");

                    actors.add(new Actor(id, firstName, lastName));
                }
            }
        }

        return actors;
    }

    public List<Film> getFilmByActorId(int actorId) throws SQLException{

        List<Film> films = new ArrayList<>();

        String query = """
                Select
                film.film_id, film.title, film.description, 
                film.release_year, film.length
                from film
                join film_actor on film.film_id = film_actor.film_id
                where film_actor.actor_id = ?
                """;

        try (
                Connection connection = this.ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, actorId);

            try (ResultSet results = statement.executeQuery()) { //Both ways work this is the nested try
                while (results.next()) {
                    int filmId = results.getInt("film_id");
                    String title = results.getString("title");
                    String description = results.getString("description");
                    int releaseYear = results.getInt("release_year");
                    int length = results.getInt("length");

                    films.add(new Film(filmId, title, length , description,  releaseYear));
                }
            }
        }

        return films;
    }



}








