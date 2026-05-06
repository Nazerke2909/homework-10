package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    
    private int messagesRoutedCount = 0;
    private int membersNotifiedCount = 0;

    @Override
    public void register(GuildMember member) {
        addSubscriber("urgent", member);

        if (member instanceof Quartermaster) {
            addSubscriber("supplies", member);
        } else if (member instanceof Scout) {
            addSubscriber("scouting", member);
        } else if (member instanceof Healer) {
            addSubscriber("healing", member);
        } else if (member instanceof Captain) {
            addSubscriber("orders", member);
        } else if (member instanceof Loremaster) { 
            addSubscriber("lore", member);
            addSubscriber("history", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        messagesRoutedCount++;
        List<GuildMember> targets = subscribersFor(topic);
        for (GuildMember member : targets) {
            if (member != from) { 
                member.receive(topic, from, payload);
                membersNotifiedCount++;
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }

    public int getMessagesRoutedCount() { return messagesRoutedCount; }
    public int getMembersNotifiedCount() { return membersNotifiedCount; }
}