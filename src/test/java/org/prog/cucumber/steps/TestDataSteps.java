package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import org.prog.util.DataHolder;

import java.util.List;
import java.util.Random;

public class TestDataSteps {

    @Given("I pick name {string} as {string}")
    public void setSearchedName(String name, String alias) {
        if ("random_name".equals(name)) {
            Random random = new Random();
            List<String> randomNames = (List<String>) DataHolder.HOLDER.get("random_group");
            DataHolder.HOLDER.put(alias, randomNames.get(random.nextInt(randomNames.size())));
        } else {
            DataHolder.HOLDER.put(alias, name);
        }
    }
}
