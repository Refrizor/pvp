package me.refriz.minigames.pvparena;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Kits {

    private static List<String> knightList = new ArrayList<>();
    private static List<String> testList = new ArrayList<>();

    public static List<String> getKnightList() {
        return knightList;
    }

    public static List<String> getTestList() {
        return testList;
    }

    public enum Knight {
        MAIN(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.DIAMOND_SWORD, null),
        ARCHER(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.IRON_BOOTS, Material.STONE_SWORD, Material.BOW);

        private final Material helmet;
        private final Material chestplate;
        private final Material leggings;
        private final Material boots;

        private final Material sword;
        private final Material other;

        Knight(Material helmet, Material chestplate, Material leggings, Material boots, Material sword, Material other) {
            this.helmet = helmet;
            this.chestplate = chestplate;
            this.leggings = leggings;
            this.boots = boots;
            this.sword = sword;
            this.other = other;
        }

        public ItemStack getHelmet() {
            return new ItemStack(helmet);
        }

        public ItemStack getChestplate() {
            return new ItemStack(chestplate);
        }

        public ItemStack getLeggings() {
            return new ItemStack(leggings);
        }

        public ItemStack getBoots() {
            return new ItemStack(boots);
        }

        public ItemStack getSword() {
            return new ItemStack(sword);
        }
    }
}
