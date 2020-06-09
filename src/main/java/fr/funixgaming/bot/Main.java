package fr.funixgaming.bot;

import fr.funixgaming.bot.Utils.ConsoleColors;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    public static BotConfiguration botConfig;
    public static Bot bot;

    public static void main(String[] args) {
        try {
            botConfig = BotConfiguration.getConfiguration();
            bot = new Bot(botConfig);
        } catch (IOException | LoginException e) {
            System.out.println(ConsoleColors.RED + e.getMessage());
            e.printStackTrace();
        }
    }
}
