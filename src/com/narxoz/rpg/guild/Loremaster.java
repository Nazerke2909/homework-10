package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {
    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        System.out.println("[LOREMASTER] " + getName() + " shares -> " + payload);
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = (from != null) ? from.getName() : "GuildMaster";
        System.out.println("[Loremaster " + getName() + "] heard topic '" + topic + "' from " + sender + ": Recording history -> " + payload);
    }
}