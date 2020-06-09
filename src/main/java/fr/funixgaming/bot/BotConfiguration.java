package fr.funixgaming.bot;

import fr.funixgaming.bot.Utils.ConsoleColors;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BotConfiguration {

    private static final String configVersion = "1.0";
    private static final String filePath = ".env.json";

    public String discordToken;
    public String bienvenueID;
    public String logID;
    public String followerID;

    public String configVersionSet;

    //Used for base config generation
    private BotConfiguration(boolean genConf) {
        if (!genConf) return;
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColors.GREEN + "Veuillez entrer le token du bot discord: ");
        this.discordToken = scanner.nextLine();
        System.out.println(ConsoleColors.GREEN + "Channel id de bienvenue: ");
        this.bienvenueID = scanner.nextLine();
        System.out.println(ConsoleColors.GREEN + "Channel id de log: ");
        this.logID = scanner.nextLine();
        System.out.println(ConsoleColors.GREEN + "Group id du grade follower: ");
        this.followerID = scanner.nextLine();
        scanner.close();
        this.configVersionSet = configVersion;
    }

    public void saveConfig() throws IOException {
        File configFile = new File(filePath);
        Gson gson = new Gson();

        if (!configFile.exists()) {
            if (!configFile.createNewFile())
                throw new IOException("Erreur lors de la création de .env.json");
        }
        String objString = gson.toJson(this);
        FileWriter fw = new FileWriter(configFile.getAbsoluteFile(), false);
        fw.write(objString);
        fw.close();
    }

    public static BotConfiguration getConfiguration() throws IOException {
        File configFile = new File(filePath);

        if (!configFile.exists()) {
            System.out.println(ConsoleColors.YELLOW_BOLD + "La configuration du bot n'existe pas. Veuillez configurer le bot.");
            BotConfiguration config = new BotConfiguration(true);
            config.saveConfig();
            return config;
        } else {
            FileInputStream fis = new FileInputStream(configFile);
            byte[] data = new byte[(int) configFile.length()];
            if (fis.read(data) == -1)
                throw new IOException("Erreur lors de la lecture du fichier");
            fis.close();

            String fileContent = new String(data, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            BotConfiguration config = gson.fromJson(fileContent, BotConfiguration.class);
            if (!config.configVersionSet.equals(configVersion)) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "La configuration du bot à changé. Veuillez reconfigurer le bot.");
                config = new BotConfiguration(true);
                config.saveConfig();
            }
            return config;
        }
    }
}
