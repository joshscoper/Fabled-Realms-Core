package net.fabledrealms.fabledrealmscore.util;


import org.bukkit.ChatColor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {

    // Regex for hex color codes (&#rrggbb)
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    // Regex for traditional color codes (&a, &b, etc.)
    private static final char COLOR_CODE_CHAR = '&';

    /**
     * Formats a string with color codes and placeholders.
     *
     * @param input        The string to format.
     * @param placeholders A map of placeholders to replace in the format {key}.
     * @return The formatted string.
     */
    public static String format(String input, Map<String, String> placeholders) {
        if (input == null) {
            return "";
        }

        // Replace placeholders
        if (placeholders != null) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                input = input.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }

        // Apply hex colors
        input = applyHexColors(input);

        // Translate traditional color codes
        return ChatColor.translateAlternateColorCodes(COLOR_CODE_CHAR, input);
    }

    /**
     * Applies hex color codes in the format &#rrggbb.
     *
     * @param input The string to process.
     * @return The string with hex colors applied.
     */
    private static String applyHexColors(String input) {
        Matcher matcher = HEX_COLOR_PATTERN.matcher(input);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hexColor = matcher.group(1); // Extract the color (rrggbb)
            String chatColor = net.md_5.bungee.api.ChatColor.of("#" + hexColor).toString(); // Convert to Minecraft ChatColor
            matcher.appendReplacement(buffer, chatColor); // Replace the match with the ChatColor equivalent
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
