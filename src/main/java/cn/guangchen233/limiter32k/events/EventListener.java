package cn.guangchen233.limiter32k.events;

import cn.guangchen233.limiter32k.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EventListener implements Listener {
    public static boolean enabled = true;
    ItemStack AIR = new ItemStack(Material.AIR);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (enabled) {
            if (event.getDamage() > 30D) {
                if (event.getDamager() instanceof Player) {
                    // is player
                    Player player = (Player) event.getDamager();
                    // main hand
                    if (Utils.checkItem(player.getInventory().getItemInMainHand())) {
                        event.setDamage(40D);
                        player.getInventory().setItemInMainHand(AIR);
                    }
                    // off hand
                    else if (Utils.checkItem(player.getInventory().getItemInOffHand())) {
                        event.setDamage(40D);
                        player.getInventory().setItemInOffHand(AIR);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if (enabled) {
            boolean mainHandResult = Utils.checkItem(event.getMainHandItem());
            boolean offHandResult = Utils.checkItem(event.getOffHandItem());
            if (mainHandResult) {
                event.setMainHandItem(AIR);
            }
            if (offHandResult) {
                event.setOffHandItem(AIR);
            }
        }
    }

    @EventHandler
    public void EntityPickupItem(EntityPickupItemEvent event) {
        if (enabled) {
            ItemStack item = event.getItem().getItemStack();
            if (Utils.checkItem(item)) {
                event.setCancelled(true);
                event.getItem().remove();
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (enabled) {
            if (Utils.checkItem(event.getCurrentItem())) {
                if (event.getInventory().getType() != InventoryType.HOPPER) {
                    event.setCurrentItem(AIR);
                } else {
                    if (event.getAction() != InventoryAction.PICKUP_ALL) {
                        event.setCurrentItem(AIR);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (enabled) {
            ItemStack[] items = event.getInventory().getStorageContents();
            ArrayList<ItemStack> abnormalItems = new ArrayList<>();
            for (ItemStack item : items) {
                if (Utils.checkItem(item)) {
                    if (!abnormalItems.contains(item)) {
                        abnormalItems.add(item);
                    }
                }
            }
            if (abnormalItems.size() > 0) {
                for (ItemStack item : abnormalItems) {
                    event.getInventory().remove(item);
                }
            }
        }
    }

    public void setEnabled(boolean enabled) {
        EventListener.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
