package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;

        System.out.println("\n--- [Phase 1: Urgent & High Priority Review] ---");
        QuestIterator priorityIterator = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priorityIterator.hasNext()) {
            Quest q = priorityIterator.next();
            questsTraversed++;
            hall.dispatch("orders", null, "Prepare party for priority mission: " + q.getTitle());
            hall.dispatch("urgent", null, "High priority! Need status checks for: " + q.getTitle());
        }

        System.out.println("\n--- [Phase 2: Standard Order Traversal for Scouting] ---");
        QuestIterator orderedIterator = questLog.ordered();
        while (orderedIterator.hasNext()) {
            Quest q = orderedIterator.next();
            questsTraversed++;
            hall.dispatch("scouting", null, "Please verify route safety for: " + q.getTitle());
        }

        System.out.println("\n--- [Phase 3: Reviewing Rewards (Extension Part 4)] ---");
        QuestIterator rewardIterator = questLog.rewardSorted();
        while (rewardIterator.hasNext()) {
            Quest q = rewardIterator.next();
            questsTraversed++;
            hall.dispatch("lore", null, "Checking archives for high-value target: " + q.getTitle() + " (" + q.getRewardGold() + "g)");
        }

        int messagesRouted = 0;
        int membersNotified = 0;
        if (hall instanceof GuildHall) {
            GuildHall gh = (GuildHall) hall;
            messagesRouted = gh.getMessagesRoutedCount();
            membersNotified = gh.getMembersNotifiedCount();
        }

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}