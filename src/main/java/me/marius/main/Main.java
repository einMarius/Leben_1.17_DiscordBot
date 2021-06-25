//This file was created in 2021

package me.marius.main;

import me.marius.listeners.BedEnterListener;
import me.marius.listeners.JoinListener;
import me.marius.listeners.PhantomHandler;
import me.marius.listeners.QuitListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    /*
    *
    *ID´s für Discord Channels!
    *
    */
    public final long SERVER_INFOS = 858014658164948992L;

    public final String prefix = "§8[§cBaumbalabunga§8] §7";

    private final String TOKEN = "TOKEN";

    public List<Player> onlinePlayers;

    private JDABuilder jdaBuilder;

    private final PluginDescriptionFile pdf = this.getDescription();

    public void onEnable() {

        onlinePlayers = new ArrayList<>();

        jdaBuilder = JDABuilder.createDefault(TOKEN);
        jdaBuilder.setActivity(Activity.playing("auf dem Minecraft Server..."));
        jdaBuilder.setStatus(OnlineStatus.ONLINE);

        JDA bot = null;

        try{
            bot = jdaBuilder.build();
            bot.awaitReady();
        }catch (LoginException | InterruptedException e){
            e.printStackTrace();
            System.out.println("[Baumbalabunga] Es gab einen Fehler beim starten des Bots!");
        }

        //--------------Bot-Register---------------//
        JoinListener joinListener = new JoinListener(bot, this);
        QuitListener quitListener = new QuitListener(bot, this);
        Bukkit.getPluginManager().registerEvents(joinListener, this);
        Bukkit.getPluginManager().registerEvents(quitListener, this);
        Bukkit.getPluginManager().registerEvents(new BedEnterListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PhantomHandler(), this);
        //--------------Bot-Register---------------//

//getCommand("COMMAND").setExecutor(new cmd_COMMAND());
//Bukkit.getPluginManager().registerEvents(new LISTENER(), this);

// -------------------------------
        System.out.println("----------[Leben_1.17]----------");
        System.out.println("Plugin aktiviert");
        System.out.println("Version: " + pdf.getVersion());
        System.out.println("Author: " + pdf.getAuthors());
        System.out.println("----------[Leben_1.17]----------");
// -------------------------------
    }

    public void onDisable() {

        jdaBuilder.setStatus(OnlineStatus.OFFLINE);

// -------------------------------
        System.out.println("----------[Leben_1.17]----------");
        System.out.println("Plugin deaktiviert");
        System.out.println("Version: " + pdf.getVersion());
        System.out.println("Author: " + pdf.getAuthors());
        System.out.println("----------[Leben_1.17]----------");
// -------------------------------

    }

}