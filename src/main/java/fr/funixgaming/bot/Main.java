package fr.funixgaming.bot;

import fr.funixgaming.bot.Utils.ConsoleCommands;

public class Main {

    public static Bot bot;

    public static void main(String[] args) {
        bot = Bot.initBot();
        if (bot == null) {
            System.err.println("Une erreur est survenue lors de la connection du bot. Veuillez recommencer.");
            System.exit(84);
        }
        ConsoleCommands.setupConsole(bot);
    }
}
