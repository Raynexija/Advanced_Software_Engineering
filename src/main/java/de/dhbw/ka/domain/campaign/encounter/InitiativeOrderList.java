package de.dhbw.ka.domain.campaign.encounter;

import java.util.ArrayList;

public class InitiativeOrderList<e> extends ArrayList<e> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (e name : this) {
            sb.append(name.toString()).append("\n");
        }
        return sb.toString();
    }
}
