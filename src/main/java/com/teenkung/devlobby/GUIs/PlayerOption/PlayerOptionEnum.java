package com.teenkung.devlobby.GUIs.PlayerOption;

public enum PlayerOptionEnum {

    FLY,JOINMESSAGE,JOINFIREWORK,VANISH,HIDEPLAYER,SPEEDBOOST,JUMPBOOST,EGLOW,UNKNOWN;

    public static PlayerOptionEnum getPlayerOptionEnum(String option) {
        if (option.equalsIgnoreCase("fly")) { return FLY; }
        else if (option.equalsIgnoreCase("join-message") || option.equalsIgnoreCase("joinmessage")) { return JOINMESSAGE; }
        else if (option.equalsIgnoreCase("join-firework") || option.equalsIgnoreCase("joinfirework")) { return JOINFIREWORK; }
        else if (option.equalsIgnoreCase("vanish")) { return VANISH; }
        else if (option.equalsIgnoreCase("hideplayer") || option.equalsIgnoreCase("hide-player")) { return HIDEPLAYER; }
        else if (option.equalsIgnoreCase("speed-boost") || option.equalsIgnoreCase("speedboost")) { return SPEEDBOOST; }
        else if (option.equalsIgnoreCase("jump-boost") || option.equalsIgnoreCase("jumpboost")) { return JUMPBOOST; }
        else if (option.equalsIgnoreCase("eglow")) { return EGLOW; }
        else { return UNKNOWN; }
    }

}
