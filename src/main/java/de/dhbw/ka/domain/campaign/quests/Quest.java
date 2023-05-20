package de.dhbw.ka.domain.campaign.quests;

import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.character.Character;

import java.util.ArrayList;
import java.util.List;

public record Quest(String name, String description, List<Character> assignedCharacters, List<Encounter> encounters,
                    boolean completed, List<String> rewards) {

    public Quest(String name, String description) {
        this(name, description, new ArrayList<>(), new ArrayList<>(), false, new ArrayList<>());
    }

    public void addEncounter(Encounter encounter) {
        encounters.add(encounter);
    }

    public void removeEncounter(Encounter encounter) {
        encounters.remove(encounter);
    }

    public void assignCharacter(Character character) {
        assignedCharacters.add(character);
    }

    public void removeCharacter(Character character) {
        assignedCharacters.remove(character);
    }

    public void addReward(String reward) {
        rewards.add(reward);
    }

    public void removeReward(String reward) {
        rewards.remove(reward);
    }

    public Quest markCompleted() {
        return new Quest(name, description, assignedCharacters, encounters, true, rewards);
    }
}