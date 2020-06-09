package fr.funixgaming.bot;

import fr.funixgaming.bot.Events.UserMessage;
import fr.funixgaming.bot.Utils.ConsoleColors;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Bot {

    private JDA api;
    private final BotConfiguration botConfiguration;

    private Bot(BotConfiguration botConfiguration) throws LoginException {
        this.botConfiguration = botConfiguration;
        setupBot();
        setupEvents();
    }

    private void setupBot() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(botConfiguration.discordToken);
        builder.setActivity(Activity.of(Activity.ActivityType.STREAMING, "twitch.tv/funixgaming", "https://twitch.tv/funixgaming"));
        this.api = builder.build();
    }

    private void setupEvents() {
        this.api.addEventListener(new UserMessage());
    }

    public JDA getApi() { return this.api; }

    public static Bot initBot() {
        Bot bot;
        try {
            BotConfiguration botConfig = BotConfiguration.getConfiguration();
            bot = new Bot(botConfig);
            return bot;
        } catch (IOException | LoginException | NoSuchElementException e) {
            System.err.println(ConsoleColors.RED + e.getMessage());
            BotConfiguration.removeConfigFile();
            return null;
        }
    }
}
