package fr.funixgaming.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    public JDA api;

    public Bot(BotConfiguration botConfiguration) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(botConfiguration.discordToken);

        builder.setActivity(Activity.of(Activity.ActivityType.STREAMING, "twitch.tv/funixgaming", "https://twitch.tv/funixgaming"));
        this.api = builder.build();
    }
}
