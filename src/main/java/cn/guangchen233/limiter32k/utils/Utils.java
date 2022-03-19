package cn.guangchen233.limiter32k.utils;

import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Utils {
    public boolean checkAbnormalNBT(ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompoundList attrs = nbtItem.getCompoundList("AttributeModifiers");
            if (attrs.size() > 0) {
                for (NBTListCompound attr : attrs) {
                    if (attr.getInteger("Amount") != 0) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean checkAbnormalEnchantment(ItemStack item) {
        if (item != null) {
            Map<Enchantment, Integer> enchantments = item.getEnchantments();
            for (Integer level : enchantments.values()) {
                if (level > 5) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean checkItem(ItemStack itemStack) {
        if (itemStack != null) {
            return checkAbnormalNBT(itemStack) || checkAbnormalEnchantment(itemStack);
        }
        return false;
    }
}
