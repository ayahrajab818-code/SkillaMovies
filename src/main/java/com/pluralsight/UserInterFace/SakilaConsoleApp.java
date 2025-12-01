package com.pluralsight.UserInterFace;

import com.pluralsight.Model.Category;
import com.pluralsight.Persistance.DataManager;

import java.sql.SQLException;
import java.util.List;

public class SakilaConsoleApp {
    private DataManager dm;

    public SakilaConsoleApp(DataManager dm) {
        this.dm = dm;
    }

    public void start(){
        String prompt = """
                Please select from one of the following:
                   1) List all Categories
                   2) Search for Actor by Name
                   3) List Films by Actor ID
                   0) Quit
                Command""";

        int choice = -1;
        while (choice != 0){
            choice = ConsoleHelper.promptForInt(prompt);

            switch (choice){
                case 1:
                    processListAllCategories();
                    break;
                case 2:
                    processSearchActors();
                    break;
                case 3:
                    processListFilmsByActor();
                    break;
            }
        }
    }

    private void processListAllCategories() {
        try {
            List<Category> categories = dm.getAllCategories();
            ConsoleHelper.displayList(categories);

        } catch (SQLException e) {
            System.out.println("There was a SQL error: " + e.getMessage());
        }
    }

    private void processSearchActors() {
        try {
            String name = ConsoleHelper.promptForString("Enter actor name: ");
            var actors = dm.searchActorsByName(name);

            if (actors.isEmpty()) {
                System.out.println("No actors found.");
            } else {
                ConsoleHelper.displayList(actors);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void processListFilmsByActor() {
        try {
            int actorId = ConsoleHelper.promptForInt("Enter actor ID: ");
            var films = dm.getFilmByActorId(actorId);

            if (films.isEmpty()) {
                System.out.println("No films found for this actor.");
            } else {
                ConsoleHelper.displayList(films);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
