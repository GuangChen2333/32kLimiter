package cn.guangchen233.limiter32k.events;

import cn.guangchen233.limiter32k.LimiterMain;
import cn.guangchen233.limiter32k.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EventListener implements Listener {
    private final Utils utils = new Utils();
    private final ItemStack AIR = new ItemStack(Material.AIR);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (LimiterMain.isEnabled) {
            if (event.getDamage() > 30D) {
                if (event.getDamager() instanceof Player) {
                    Player player = (Player) event.getDamager();
                    if (utils.checkItem(player.getInventory().getItemInMainHand())) {
                        event.setDamage(40D);
                        player.getInventory().setItemInMainHand(AIR);
                    }
                    if (utils.checkItem(player.getInventory().getItemInOffHand())) {
                        event.setDamage(40D);
                        player.getInventory().setItemInOffHand(AIR);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if (LimiterMain.isEnabled) {
            boolean mainHandResult = utils.checkItem(event.getMainHandItem());
            boolean offHandResult = utils.checkItem(event.getOffHandItem());
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
        if (LimiterMain.isEnabled) {
            ItemStack item = event.getItem().getItemStack();
            if (utils.checkItem(item)) {
                event.setCancelled(true);
                event.getItem().remove();
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (LimiterMain.isEnabled) {
            if (utils.checkItem(event.getCurrentItem())) {
                if (event.getInventory().getType() != InventoryType.HOPPER) {
                    event.setCurrentItem(AIR);
                } else if (event.getAction() != InventoryAction.PICKUP_ALL) {
                    event.setCurrentItem(AIR);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (LimiterMain.isEnabled) {
            ItemStack[] items = event.getInventory().getStorageContents();
            if (items.length > 0) {
                ArrayList<ItemStack> abnormalItems = new ArrayList<>();
                for (ItemStack item : items) {
                    if (utils.checkItem(item)) {
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
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        if (LimiterMain.isEnabled) {
            // Player
            ItemStack[] items = event.getPlayer().getInventory().getStorageContents();
            if (items.length > 0) {
                ArrayList<ItemStack> abnormalItems = new ArrayList<>();
                for (ItemStack item : items) {
                    if (utils.checkItem(item)) {
                        if (!abnormalItems.contains(item)) {
                            abnormalItems.add(item);
                        }
                    }
                }
                if (abnormalItems.size() > 0) {
                    for (ItemStack item : abnormalItems) {
                        event.getPlayer().getInventory().remove(item);
                    }
                }
            }
            // Inventory
            ItemStack[] inventoryContents = event.getInventory().getStorageContents();
            if (inventoryContents.length > 0) {
                ArrayList<ItemStack> abnormalItems = new ArrayList<>();
                for (ItemStack item : inventoryContents) {
                    if (utils.checkItem(item)) {
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
    }
}
