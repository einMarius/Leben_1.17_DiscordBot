package me.marius.listeners;

import me.marius.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDateTime;

public class QuitListener implements Listener {

    JDA bot;
    TextChannel textChannel;
    private Main plugin;
    public QuitListener(JDA bot, Main plugin) {
        this.plugin = plugin;
        this.bot = bot;
        this.textChannel = bot.getTextChannelById(plugin.SERVER_INFOS);
    }

    @EventHandler
    public void onDiconnect(PlayerQuitEvent event) {

        plugin.onlinePlayers.remove(event.getPlayer());

        try {
            URL url = new URL("https://crafatar.com/avatars/" + event.getPlayer().getUniqueId().toString());

            EmbedBuilder builder = new EmbedBuilder()
                    .setThumbnail(String.valueOf(url))
                    .setColor(Color.RED)
                    .setTitle(">>> Serverleave")
                    .setDescription(event.getPlayer().getName() + " hat den Server verlassen!")
                    .addField("Online-Spieler", "Aktuell sind " + plugin.onlinePlayers.size() + " Spieler online.", false)
                    .setTimestamp(LocalDateTime.now(Clock.systemUTC()));

            textChannel.sendMessage(builder.build()).queue();
            builder.clear();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
