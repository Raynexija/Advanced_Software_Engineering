package de.dhbw.ka.test.domain.character;

import de.dhbw.ka.domain.character.AbilityScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbilityScoreTest {

    @Test
    public void modifierTest() {
        List<AbilityScore> abilityScores = new ArrayList<>();
        int[] realModifiers = {-4, -3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5};
        for (int i = 3; i <= 18; i++) {
            abilityScores.add(new AbilityScore(i, 0, 0));
        }
        abilityScores.add(new AbilityScore(18, 1, 0));
        abilityScores.add(new AbilityScore(18, 1, 1));
        for (int i = 0; i < abilityScores.size(); i++) {
            int temp = i + 3;
            //System.out.println("value: " + temp + ", realModifier: " + realModifiers[i] + ", calculatedModifier: " + abilityScores.get(i).getModifier());
            Assertions.assertEquals(realModifiers[i], abilityScores.get(i).getModifier());
        }
    }

    @Test
    public void lowerBaseBoundaryTest() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new AbilityScore(2, 0, 0);
        });

        String expectedMessage = "Base Ability Score must be between 3 and 18";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void upperBaseBoundaryTest() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new AbilityScore(19, 0, 0);
        });

        String expectedMessage = "Base Ability Score must be between 3 and 18";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void totalBoundaryTest() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new AbilityScore(18, 2, 1);
        });

        String expectedMessage = "Ability Score must not exceed 20";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
