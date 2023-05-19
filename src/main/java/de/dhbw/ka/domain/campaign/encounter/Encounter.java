package de.dhbw.ka.domain.campaign.encounter;

import de.dhbw.ka.domain.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Encounter {

    private final List<Creature> creatures = new ArrayList<>();
    private final List<Character> playerCharacters = new ArrayList<>();

    public Encounter() {
    }

    /**
     * Returns a String representation of all participants in the encounter and their current hit points.
     *
     * @return String representation of all participants in the encounter and their current hit points.
     */
    public String getParticipants() {
        StringBuilder sb = new StringBuilder();
        sb.append("Creatures:\n");
        for (Creature creature : creatures) {
            sb.append(creature.name()).append(": ").append(creature.hitPoints()).append("\n");
        }
        sb.append("Player Characters:\n");
        for (Character character : playerCharacters) {
            sb.append(character.getName()).append(": ").append(character.getHitPoints()).append("\n");
        }
        return sb.toString();
    }

    public void addCreature(Creature creature) {
        this.creatures.add(creature);
    }

    public void addPlayerCharacter(Character character) {
        this.playerCharacters.add(character);
    }

    public void removeCreature(Creature creature) {
        this.creatures.remove(creature);
    }

    public void removePlayerCharacter(Character character) {
        this.playerCharacters.remove(character);
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public List<Character> getPlayerCharacters() {
        return playerCharacters;
    }

}
