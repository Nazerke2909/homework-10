package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===\n");

        List<Hero> party = List.of(
            new Hero("Arthur", 120, 30, 20),
            new Hero("Merlin", 80, 150, 15, 10, 50)
        );

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear out the rats in the cellar", QuestPriority.LOW, 10, false));
        questLog.add(new Quest("Escort the merchant to town", QuestPriority.NORMAL, 100, false));
        questLog.add(new Quest("Hunt down the local bandit leader", QuestPriority.HIGH, 300, false));
        questLog.add(new Quest("Defend the village from incoming raid", QuestPriority.URGENT, 500, true));
        questLog.add(new Quest("Explore the ancient ruins", QuestPriority.NORMAL, 800, false));

        GuildHall guildHall = new GuildHall();
        Quartermaster quartermaster = new Quartermaster("Thorek", guildHall);
        Scout scout = new Scout("Elara", guildHall);
        Healer healer = new Healer("Jocelyn", guildHall);
        Captain captain = new Captain("Vanguard", guildHall);
        Loremaster loremaster = new Loremaster("Deckard", guildHall);

        System.out.println("--- [Guild Preparation] ---");
        scout.reportRoute("scouting", "The northern pass is blocked by snow.");
        captain.issueOrder("supplies", "Need extra rations for the cold weather.");
        quartermaster.requestSupplies("urgent", "We are running out of healing herbs!");
        healer.prepareAid("healing", "Setting up the infirmary for the returning heroes.");
        loremaster.shareLore("history", "The ancient ruins date back to the First Era.");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, guildHall);

        System.out.println("\n=== Final Run Result ===");
        System.out.println(result);
    }
}