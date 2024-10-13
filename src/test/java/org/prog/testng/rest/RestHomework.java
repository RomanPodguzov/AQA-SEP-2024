package org.prog.testng.rest;

//TODO: Add location to rest params
//TODO: Add Location DTO with all sub objects
//TODO: For each user, print First Name + Last Name + Street Name + building id
public class RestHomework {
import java.util .*;

    // Класс для хранения информации о местоположении
    class LocationDTO {
        private String streetName;
        private int buildingId;

        public LocationDTO(String streetName, int buildingId) {
            this.streetName = streetName;
            this.buildingId = buildingId;
        }

        public String getStreetName() {
            return streetName;
        }

        public int getBuildingId() {
            return buildingId;
        }

        @Override
        public String toString() {
            return streetName + ", building " + buildingId;
        }
    }

    // Основной класс для работы с данными
    public class RestHomework {

        public static void main(String[] args) {
            // Создаем карту для хранения пользователей и их местоположений
            Map<String, LocationDTO> userLocations = new HashMap<>();

            // Добавляем пользователей и их местоположения
            userLocations.put("Alice Johnson", new LocationDTO("Main Street", 12));
            userLocations.put("Bob Smith", new LocationDTO("Second Street", 8));
            userLocations.put("Charlie Brown", new LocationDTO("Third Avenue", 15));

            // Печатаем имя пользователя и его адрес
            for (String userName : userLocations.keySet()) {
                LocationDTO location = userLocations.get(userName);
                System.out.println("User: " + userName + ", Address: " + location);
            }
        }
    }
}