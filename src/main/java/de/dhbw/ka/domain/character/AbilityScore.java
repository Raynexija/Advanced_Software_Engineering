package de.dhbw.ka.domain.character;

import de.dhbw.ka.domain.character.exception.AbilityScoreLimitException;
import de.dhbw.ka.domain.character.exception.BaseAbilityScoreOutOfBoundsException;

public final class AbilityScore {

    private final int score;
    private final int modifier;
    private final int baseScore;
    private final int racialBonus;
    private final int additionalBonus ;

    public AbilityScore(int baseScore, int racialBonus, int additionalBonus) {
        if (baseScore < 3 || baseScore > 18) {
            throw new BaseAbilityScoreOutOfBoundsException("Base Ability Score must be between 3 and 18");
        }
        this.baseScore = baseScore;
        this.racialBonus = racialBonus;
        this.additionalBonus = additionalBonus;
        this.score = baseScore + racialBonus + additionalBonus;
        if (score >= 10) {
            this.modifier = (score - 10) / 2;
        } else {
            this.modifier = (score - 11) / 2;
        }
        if (this.score > 20) {
            throw new AbilityScoreLimitException("Ability Score must not exceed 20");
        }
    }

    @Override
    public String toString() {
        return "Score: " + score + " Modifier: " + modifier;
    }

    public int getModifier() {
        return modifier;
    }

    public int getScore() {
        return score;
    }

    public int getRacialBonus() {
        return racialBonus;
    }

    public int getAdditionalBonus() {
        return additionalBonus;
    }

    public int getBaseScore() {
        return baseScore;
    }
}
