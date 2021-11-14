package cn.guangchen233.limiter32k.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Utils {
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
        // I don't know what is that, but I found it may be suitable for detecting abnormal NBT
        if (item != null) {
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
