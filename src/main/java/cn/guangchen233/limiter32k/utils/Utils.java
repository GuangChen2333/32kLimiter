package cn.guangchen233.limiter32k.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static List<Material> getShulkerBoxes() {
        return Arrays.asList(
                Material.BLUE_SHULKER_BOX,
                Material.BLACK_SHULKER_BOX,
                Material.BROWN_SHULKER_BOX,
                Material.CYAN_SHULKER_BOX,
                Material.GRAY_SHULKER_BOX,
                Material.GREEN_SHULKER_BOX,
                Material.LIGHT_BLUE_SHULKER_BOX,
                Material.LIME_SHULKER_BOX,
                Material.MAGENTA_SHULKER_BOX,
                Material.ORANGE_SHULKER_BOX,
                Material.PINK_SHULKER_BOX,
                Material.PURPLE_SHULKER_BOX,
                Material.RED_SHULKER_BOX,
                Material.SILVER_SHULKER_BOX,
                Material.WHITE_SHULKER_BOX,
                Material.YELLOW_SHULKER_BOX
                );
    }

    public static Map<String, Object> stringToMap(String str){
        str = str.replace("{", "");
        str = str.replace("}", "");
        String[] str1 = str.split(", ");
        Map<String,Object> map = new HashMap<>();
        for (String s : str1) {
            String[] str2 = s.split("=");
            map.put(str2[0], str2[1]);
        }
        return map;
    }

    public static boolean checkAbnormalInternal(ItemStack item) {
        // I don't know what I got from it, but it did take effect
        if (item != null && !getShulkerBoxes().contains(item.getType())) {
            Map<String, Object> serialize = item.serialize();
            if (serialize.containsKey("meta")) {
                String meta_string = serialize.get("meta").toString().replace("UNSPECIFIC_META:", "");
                Map<String, Object> metas = stringToMap(meta_string);
                return metas.containsKey("internal");
            }
            return false;
        }
        return false;
    }

    public static boolean checkAbnormalEnchantment(ItemStack item) {
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

    public static boolean checkItem(ItemStack itemStack) {
        if (itemStack != null) {
            return checkAbnormalInternal(itemStack)
                    || checkAbnormalEnchantment(itemStack);
        }
        return false;
    }
}
