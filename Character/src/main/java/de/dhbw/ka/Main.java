package de.dhbw.ka;

public class Main {
    public static void main(String[] args) {
        Character character = new Character();
        System.out.println(character.getAbilityScore(AbilityScores.STRENGTH).modifier);
        System.out.println(character.skillCheckModifier(Skills.Arcana));
    }


}