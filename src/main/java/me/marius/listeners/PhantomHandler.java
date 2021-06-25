package me.marius.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class PhantomHandler implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPhantomSpawn(EntitySpawnEvent event) {
        if(event.getEntityType() == EntityType.PHANTOM)
            event.setCancelled(true);
    }
}