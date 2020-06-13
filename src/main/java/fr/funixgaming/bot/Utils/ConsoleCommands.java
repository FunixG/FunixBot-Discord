package fr.funixgaming.bot.Utils;

import fr.funixgaming.bot.Bot;

import java.util.Scanner;

public class ConsoleCommands {

    private static boolean RUNNING = true;

    public static void setupConsole(Bot bot) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (RUNNING) {
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

    public static void stopConsoleAndBot() {
        RUNNING = false;
        System.exit(0);
    }
}
