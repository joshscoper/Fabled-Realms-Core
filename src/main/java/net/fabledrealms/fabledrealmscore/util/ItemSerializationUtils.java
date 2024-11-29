package net.fabledrealms.fabledrealmscore.util;

import org.bukkit.Bukkit;
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
        if (item == null) return null;

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
}
