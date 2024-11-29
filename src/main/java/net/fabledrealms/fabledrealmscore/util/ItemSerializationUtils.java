package net.fabledrealms.fabledrealmscore.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemSerializationUtils {

    /**
     * Serializes an ItemStack to a Base64 string.
     *
     * @param item The ItemStack to serialize.
     * @return A Base64 string representing the serialized item.
     * @throws IOException If an error occurs during serialization.
     */
    public static String serializeItem(ItemStack item) throws IOException {
        if (item == null) return "";

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream)) {

            bukkitObjectOutputStream.writeObject(item);
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        }
    }

    /**
     * Deserializes an ItemStack from a Base64 string.
     *
     * @param data The Base64 string representing the serialized item.
     * @return The deserialized ItemStack.
     * @throws IOException If an error occurs during deserialization.
     */
    public static ItemStack deserializeItem(String data) throws IOException {
        if (data == null || data.isEmpty()) return null;

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
             BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream)) {

            return (ItemStack) bukkitObjectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to deserialize item", e);
        }
    }

    /**
     * Serializes an Inventory to a Base64 string.
     *
     * @param inventory The Inventory to serialize.
     * @return A Base64 string representing the serialized inventory.
     * @throws IOException If an error occurs during serialization.
     */
    public static String serializeInventory(Inventory inventory) throws IOException {
        if (inventory == null) return "";

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream)) {

            bukkitObjectOutputStream.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); i++) {
                bukkitObjectOutputStream.writeObject(inventory.getItem(i));
            }
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        }
    }

    /**
     * Deserializes an Inventory from a Base64 string.
     *
     * @param data The Base64 string representing the serialized inventory.
     * @return The deserialized Inventory.
     * @throws IOException If an error occurs during deserialization.
     */
    public static Inventory deserializeInventory(String data) throws IOException {
        if (data == null || data.isEmpty()) return Bukkit.createInventory(null, 9);

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(data));
             BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream)) {

            int size = bukkitObjectInputStream.readInt();
            Inventory inventory = Bukkit.createInventory(null, size);

            for (int i = 0; i < size; i++) {
                ItemStack item = (ItemStack) bukkitObjectInputStream.readObject();
                inventory.setItem(i, item);
            }

            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to deserialize inventory", e);
        }
    }

    /**
     * Serializes equipment (as an Inventory) to a Base64 string.
     *
     * @param equipment The Inventory representing equipment to serialize.
     * @return A Base64 string representing the serialized equipment.
     * @throws IOException If an error occurs during serialization.
     */
    public static String serializeEquipment(Inventory equipment) throws IOException {
        return serializeInventory(equipment);
    }

    /**
     * Deserializes equipment from a Base64 string.
     *
     * @param data The Base64 string representing the serialized equipment.
     * @return The deserialized Inventory representing equipment.
     * @throws IOException If an error occurs during deserialization.
     */
    public static Inventory deserializeEquipment(String data) throws IOException {
        return deserializeInventory(data);
    }
}
