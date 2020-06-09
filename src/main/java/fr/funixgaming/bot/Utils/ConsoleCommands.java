package fr.funixgaming.bot.Utils;

import fr.funixgaming.bot.Bot;

import java.util.Scanner;

public class ConsoleCommands {

    private static boolean RUNNING = true;

    private final Bot bot;

    private ConsoleCommands(Bot bot) {
        this.bot = bot;
    }

    private void init() {
        new Thread(() -> {
            while (RUNNING) {
                Scanner scanner = new Scanner(System.in);
                final String userEntry = scanner.nextLine();
                switch (userEntry) {
                    case "stop":
                        System.out.println("Arrêt du bot.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Commande inconnue.");
                }
            }
        }).start();
    }

    public static void setupConsole(Bot bot) {
        ConsoleCommands console = new ConsoleCommands(bot);
        console.init();
    }

    public static void stopConsoleAndBot() {
        RUNNING = false;
        System.exit(0);
    }

}
