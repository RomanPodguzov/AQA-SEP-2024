package org.prog.testng.sql;

import io.restassured.RestAssured;
import org.prog.dto.ResultsDto;
import org.prog.dto.UserDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTest {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/db";
    private final static String INSERT_QUERY =
            "insert into Persons (FirstName, LastName, Gender, Title, Nat) VALUES ('%s', '%s', '%s', '%s', '%s')";

    private Connection connection;
    private Statement statement;

    @BeforeMethod
    public void setUp() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        connection =
                DriverManager.getConnection(DB_URL, "user", "password");
        statement = connection.createStatement();
    }

    @AfterMethod
    public void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void insertUsersIntoDB() {
        List<UserDto> users = getUsers(3);
        for (UserDto user : users) {
            try {
                statement.execute(String.format(INSERT_QUERY,
                        user.getName().getFirst(),
                        user.getName().getLast(),
                        user.getGender(),
                        user.getName().getTitle(),
                        user.getNat()
                ));
            } catch (SQLException e) {
                System.out.println("Failed to insert " + user.getName());
            }
        }
    }

    @Test
    public void testSqlInsert() throws SQLException {
        statement.execute("insert into Persons (FirstName, LastName, Gender, Title, Nat) VALUES ('Bob', 'Doe', 'Male', 'Mr', 'IR')");
    }

    @Test
    public void testSqlRead() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Persons");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("FirstName") + " "
                    + resultSet.getString("LastName"));
        }
    }

    @Test
    public void testSqlRead2() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT FirstName, LastName FROM Persons");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " "
                    + resultSet.getString(2));
        }
    }

    private List<UserDto> getUsers(int amount) {
        return new ArrayList<>(RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("/api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ResultsDto.class)
                .getResults());
    }
}
