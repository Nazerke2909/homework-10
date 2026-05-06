package com.narxoz.rpg.guild;

public class Healer extends GuildMember {
    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        System.out.println("[HEALER] " + getName() + " preparing -> " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[Healer " + getName() + "] heard topic '" + topic + "' from " + sender + ": Mixing potions for -> " + payload);
    }
}