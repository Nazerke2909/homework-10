package com.narxoz.rpg.guild;

public class Scout extends GuildMember {
    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        System.out.println("[SCOUT] " + getName() + " reports -> " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[Scout " + getName() + "] heard topic '" + topic + "' from " + sender + ": Checking maps for -> " + payload);
    }
}