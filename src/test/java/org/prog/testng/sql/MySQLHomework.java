package org.prog.testng.sql;


// TODO: Create table PHONES with columns: Product_Id (autoincrement, not null, unique), PhoneModel, PhonePrice
// TODO: Search for iPhone in allo UA
// TODO: Check if DB has this phone
// TODO: IF phone is NOT in DB >> Store phone model name and phone price >>  TEST PASSES!!
// TODO: IF phone IS in DB >> if price is different >> update price and fail test
import java.sql.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class MySQLHomework {
    private static final String DATABASE = "db";
    private static final String USER = "user";
    private static final String PASS = "password";

    @Test
    public void testPhoneInDatabase() {
        WebDriver driver = new ChromeDriver();

        try (Connection conn = DriverManager.getConnection(DATABASE, USER, PASS);
             Statement stmt = conn.createStatement()) {

            // TODO: Create table PHONES if not exists
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS PHONES (
                    Product_Id INT AUTO_INCREMENT PRIMARY KEY,
                    PhoneModel VARCHAR(255) NOT NULL UNIQUE,
                    PhonePrice DECIMAL(10, 2)
                );
            """;
            stmt.executeUpdate(createTableSQL);

            // TODO: Search for iPhone on Allo website
            driver.get("https://allo.ua");
            WebElement searchBox = driver.findElement(By.name("search"));
            searchBox.sendKeys("iPhone 16");
            searchBox.submit();

            WebElement phoneElement = driver.findElement(By.xpath("//div[contains(@class, 'product-card')]"));
            String phoneModel = phoneElement.findElement(By.className("product-title")).getText();
            String phonePriceText = phoneElement.findElement(By.className("product-price")).getText();
            double phonePrice = Double.parseDouble(phonePriceText.replace("₴", "").replace(",", ""));

            // TODO: Check if the phone is in the database
            String query = "SELECT PhonePrice FROM PHONES WHERE PhoneModel = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, phoneModel);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    double storedPrice = rs.getDouble("PhonePrice");

                    // TODO: Check if prices match
                    if (storedPrice != phonePrice) {
                        String updateSQL = "UPDATE PHONES SET PhonePrice = ? WHERE PhoneModel = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                            updateStmt.setDouble(1, phonePrice);
                            updateStmt.setString(2, phoneModel);
                            updateStmt.executeUpdate();
                            Assert.fail("Price updated in DB. Test failed.");
                        }
                    } else {
                        System.out.println("Phone already in database with the same price.");
                    }
                } else {
                    // TODO: Insert new phone entry into the database
                    String insertSQL = "INSERT INTO PHONES (PhoneModel, PhonePrice) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                        insertStmt.setString(1, phoneModel);
                        insertStmt.setDouble(2, phonePrice);
                        insertStmt.executeUpdate();
                        System.out.println("New phone added to DB. Test passed!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
