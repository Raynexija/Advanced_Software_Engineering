package de.dhbw.ka;

public final class AbilityScore {

    protected final int score;
    protected final int modifier;
    private final int baseScore;
    private final int racialBonus;

    public AbilityScore(int baseScore, int racialBonus) {
        if (baseScore < 3 || baseScore > 18){
            throw new IllegalArgumentException("Base Ability Score must be between 3 and 18");
        }
        this.baseScore = baseScore;
        this.racialBonus = racialBonus;
        this.score = baseScore + racialBonus;
        if (score >= 10) {
            this.modifier = (score - 10) / 2;
        } else {
            this.modifier = (score - 11) / 2;
        }
    }

    @Override
    public String toString() {
        return "Score: " + score + " Modifier: " + modifier;
    }
}
