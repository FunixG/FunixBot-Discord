package fr.funixgaming.bot.Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.List;

public class Commands {

    public static void ip(User user, TextChannel channel, List<String> args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Pacifista Minecraft", "https://pacifista.fr");
        embedBuilder.setDescription("Serveur minecraft survie");
        embedBuilder.addField("Site web", "https://pacifista.fr", true);
        embedBuilder.addField("IP de connexion", "play.pacifista.fr", true);
        embedBuilder.addField("Version", "1.15.2", true);
        embedBuilder.setColor(new Color(0, 168, 232));

        channel.sendMessage(embedBuilder.build()).queue();
    }

}
