package com.pluralsight;


import com.pluralsight.Model.Category;
import com.pluralsight.Persistance.DataManager;
import com.pluralsight.UserInterFace.ConsoleHelper;
import com.pluralsight.UserInterFace.SakilaConsoleApp;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args)  {

        if (!ensureArgs(args)) { return; }

        try{
            BasicDataSource ds = getDataSource(args);

            DataManager dm = new DataManager(ds);

            SakilaConsoleApp app = new SakilaConsoleApp(dm);

            app.start();

        } catch (Exception e) {
            System.out.println("There was a SQL exception: "+ e.getMessage());
        }



    }

    private static boolean ensureArgs(String[] args){

        if(args.length < 3){
            System.out.println("You need to provide a username, password, and URL when running this command.");
            System.out.println("For example:");
            System.out.println("Main.exe myUsername myPassword myURL");
            return false;
        }
        return true;
    }

    private static BasicDataSource getDataSource(String username, String password, String URL) throws SQLException {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        return basicDataSource;

    }

    private static BasicDataSource getDataSource(String[] args) throws SQLException {
        String username = args[0];
        String password = args[1];
        String URL = args[2];
        return getDataSource(username, password, URL);
    }




}