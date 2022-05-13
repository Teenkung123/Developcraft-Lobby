package com.teenkung.devlobby.Utils;

import javax.annotation.Nullable;

import static com.teenkung.devlobby.DevLobby.colorize;

public enum Rank {

    DEFAULT,GUARDIAN,GUARDIANF,HERO,TITAN,DRAGON,SUPREME,YOUTUBER,ADMIN,UNKNOWN;

    public static String getString(Rank rank) {
        if (rank.equals(DEFAULT)) {
            return colorize("Rookie");
        } else if (rank.equals(GUARDIAN) || rank.equals(GUARDIANF)) {
            return colorize("Guardian");
        } else if (rank.equals(HERO)) {
            return colorize("Hero");
        } else if (rank.equals(TITAN)) {
            return colorize("Titan");
        } else if (rank.equals(DRAGON)) {
            return colorize("Dragon");
        } else if (rank.equals(SUPREME)) {
            return colorize("Supreme");
        } else if (rank.equals(YOUTUBER)) {
            return colorize("Youtuber");
        } else if (rank.equals(ADMIN)) {
            return colorize("ADMIN");
        } else {
            return colorize("Rookie");
        }
    }

    public static Rank getRank(@Nullable String Rank) {
        if (Rank == null) {
            return UNKNOWN;
        } else if (Rank.equalsIgnoreCase("rookie") || Rank.equalsIgnoreCase("default")) {
            return DEFAULT;
        } else if (Rank.equalsIgnoreCase("guardian")) {
            return GUARDIAN;
        } else if (Rank.equalsIgnoreCase("guardianf") || Rank.equalsIgnoreCase("guardianfree") || Rank.equalsIgnoreCase("guardian-free")) {
            return GUARDIANF;
        } else if (Rank.equalsIgnoreCase("hero")) {
            return HERO;
        } else if (Rank.equalsIgnoreCase("titan")) {
            return TITAN;
        } else if (Rank.equalsIgnoreCase("dragon")) {
            return DRAGON;
        } else if (Rank.equalsIgnoreCase("supreme")) {
            return SUPREME;
        } else if (Rank.equalsIgnoreCase("youtuber")) {
            return YOUTUBER;
        } else if (Rank.equalsIgnoreCase("admin") || Rank.equalsIgnoreCase("staff")) {
            return ADMIN;
        } else {
            return UNKNOWN;
        }
    }

    public static Boolean isHigherOrEqual(Rank input1, Rank input2) {
        return getRankPriority(input1) >= getRankPriority(input2);
    }

    public static String getRankNameColorize(Rank rank) {
        if (rank.equals(DEFAULT)) {
            return colorize("&7Rookie");
        } else if (rank.equals(GUARDIAN) || rank.equals(GUARDIANF)) {
            return colorize("&eGuardian");
        } else if (rank.equals(HERO)) {
            return colorize("&dHero");
        } else if (rank.equals(TITAN)) {
            return colorize("&bTitan");
        } else if (rank.equals(DRAGON)) {
            return colorize("&aDragon");
        } else if (rank.equals(SUPREME)) {
            return colorize("&cSupreme");
        } else if (rank.equals(YOUTUBER)) {
            return colorize("&4You&ftuber");
        } else if (rank.equals(ADMIN)) {
            return colorize("&c&lADMIN");
        } else {
            return colorize("&8Unknown");
        }
    }

    private static Integer getRankPriority(Rank rank) {
        if (rank.equals(DEFAULT)) {
            return 1;
        } else if (rank.equals(GUARDIAN) || rank.equals(GUARDIANF)) {
            return 2;
        } else if (rank.equals(HERO)) {
            return 3;
        } else if (rank.equals(TITAN)) {
            return 4;
        } else if (rank.equals(DRAGON)) {
            return 5;
        } else if (rank.equals(SUPREME)) {
            return 6;
        } else if (rank.equals(YOUTUBER)) {
            return 8;
        } else if (rank.equals(ADMIN)) {
            return 10;
        } else {
            return 0;
        }
    }
}
