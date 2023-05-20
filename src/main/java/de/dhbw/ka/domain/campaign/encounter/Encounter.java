package de.dhbw.ka.domain.campaign.encounter;

import de.dhbw.ka.domain.character.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Encounter {

    private final String name;

    private final List<Creature> creatures = new ArrayList<>();
    private final List<Character> playerCharacters = new ArrayList<>();

    private final HashMap<String, Integer> initiativeOrder = new HashMap<>();

    public Encounter(String name) {
        this.name = name;
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

    public void addToInitiative(String name, int initiative) {
        initiativeOrder.put(name, initiative);
    }

    public void removeFromInitiative(String name) {
        initiativeOrder.remove(name);
    }

    public List<String> getInitiativeOrder() {
        List<String> order = new InitiativeOrderList<>();
        order.add("Name: Initiative");
        initiativeOrder.entrySet().stream()
                .sorted(HashMap.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> order.add(entry.getKey() + ": " + entry.getValue()));
        return order;
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

    public String getName() {
        return this.name;
    }
}
