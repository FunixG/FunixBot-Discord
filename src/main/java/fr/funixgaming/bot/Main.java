package fr.funixgaming.bot;

import fr.funixgaming.bot.Modules.ScheduledTasks;
import fr.funixgaming.bot.Modules.TwitchApi;
import fr.funixgaming.bot.Utils.ConsoleCommands;

import java.io.File;

public class Main {
    public static final File dataFolder = new File("data");

    public static Bot bot;
    public static TwitchApi twitchApi;

    public static void main(String[] args) {
        twitchApi = TwitchApi.init();
        bot = Bot.initBot();
        ScheduledTasks.init();
        ConsoleCommands.setupConsole();
    }
}
