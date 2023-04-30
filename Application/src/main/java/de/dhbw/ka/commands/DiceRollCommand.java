package de.dhbw.ka.commands;

import de.dhbw.ka.Dice;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;

import java.util.Arrays;

public class DiceRollCommand extends Command {
    private final Dice dice;
    private final Dice.types diceType;
    private final int count;
    private final String[] additionalParameters;

    public DiceRollCommand(Dice dice, String commandMessage) {
        this.dice = dice;

        String[] parameter = commandMessage.split(" ");
        this.count = Integer.parseInt(parameter[1]);
        this.diceType = Dice.types.valueOf(parameter[2].toUpperCase());
        this.additionalParameters = Arrays.copyOfRange(parameter, 3, parameter.length);

    }

    public static String help() {
        return """
                Rolls a dice of the specified type and count.
                Available dice types: D4, D6, D8, D10, D12, D20, D100
                Add -s to get the sum of all rolls
                Usage: roll <count> <diceType>""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Rolling ").append(count).append(" ").append(diceType).append(": ");
        int[] diceRolls = new int[count];

        switch (diceType) {
            case D4 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD4();
                }
            }
            case D6 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD6();
                }
            }
            case D8 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD8();
                }
            }
            case D10 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD10();
                }
            }
            case D12 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD12();
                }
            }
            case D20 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD20();
                }
            }
            case D100 -> {
                for (int i = 0; i < count; i++) {
                    diceRolls[i] = dice.rollD100();
                }
            }
        }
        // Arrays.sort(diceRolls);
        stringBuilder.append(Arrays.toString(diceRolls));

        for (String parameter : additionalParameters) {
            if (parameter.contains("-s")) {
                stringBuilder.append("\nfor a total sum of: ").append(Arrays.stream(diceRolls).sum());
            }
        }

        output.displayMessage(stringBuilder.toString());
    }
}
