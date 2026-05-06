package com.narxoz.rpg.guild;

public class Captain extends GuildMember {
    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        System.out.println("[CAPTAIN] " + getName() + " issues order -> " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[Captain " + getName() + "] heard topic '" + topic + "' from " + sender + ": " + payload);
    }
}