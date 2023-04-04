package de.dhbw.ka.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.dhbw.ka.AbilityScores;
import de.dhbw.ka.Character;
import de.dhbw.ka.CreateCharacter;
import de.dhbw.ka.characterClasses.Fighter;
import de.dhbw.ka.races.Human;
import de.dhbw.ka.races.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CharacterTest {

    private Race testRace = new Human();

    private Character character = CreateCharacter.named("Test")
            .ofRace(testRace)
            .isA(new Fighter())
            .withStrength(15)
            .withDexterity(14)
            .withConstitution(13)
            .withIntelligence(12)
            .withWisdom(10)
            .withCharisma(8)
            .maxHitPoints(10)
            .currentLevel(1)
            .speaking(new String[]{"Celestial", "Elvish"})
            .equippedWith(new String[]{"Leather Armor", "Longsword", "Dagger"})
            .build();

    @Test
    public void testCharacterName() {
        assertEquals("Test", character.getName());
    }

    @Test
    public void testAbilityScore() {
        assertEquals(16, character.getAbilityScore(AbilityScores.STRENGTH).getScore());
        assertEquals(3, character.getAbilityScore(AbilityScores.STRENGTH).getModifier());
    }

    @Test
    public void testHitPoints() {
        assertEquals(10, character.getHitPoints());
    }

    @Test
    public void testRace() {
        assertEquals(testRace, character.getRace());
    }

    @Test
    public void testLanguages() {
        List<String> expectedLanguages = new ArrayList<>();
        expectedLanguages.addAll(List.of(new String[]{"Celestial", "Common", "Elvish"}));
        assertEquals(expectedLanguages.size(), character.getLanguages().size());
        assertEquals(expectedLanguages, character.getLanguages());
    }

    @Test
    public void testEquipment() {
        List<String> expectedEquipment = new ArrayList<>();
        expectedEquipment.addAll(List.of(new String[]{"Dagger", "Leather Armor", "Longsword"}));
        assertEquals(expectedEquipment.size(), character.getEquipment().size());
        assertEquals(expectedEquipment, character.getEquipment());

        character.removeEquipment("Dagger");
        expectedEquipment.remove("Dagger");
        assertEquals(expectedEquipment.size(), character.getEquipment().size());
        assertEquals(expectedEquipment, character.getEquipment());
    }
}
