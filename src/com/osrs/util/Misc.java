package com.osrs.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheLoader;
import com.google.gson.Gson;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Item;
import com.osrs.game.model.Position;

public class Misc {
	
	public static int getTicks(int seconds) {
		return (int) (seconds / 0.6);
	}
	
	public static int getSeconds(int ticks) {
		return (int) (ticks * 0.6);
	}
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(); 
	
	public static String formatNumber(long number) {
		return DECIMAL_FORMAT.format(number);
	}

    //Our formatter
    public static final DecimalFormat FORMATTER = new DecimalFormat("0.#");
    public static final int HALF_A_DAY_IN_MILLIS = 43200000;
    /**
     * An array containing valid player name characters.
     */
    public static final char VALID_PLAYER_CHARACTERS[] = {'_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '[', ']', '/', '-', ' '};
    /**
     * An array containing valid characters that may be used on the server.
     */
    public static final char VALID_CHARACTERS[] = {'_', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^', '&',
            '*', '(', ')', '-', '+', '=', ':', ';', '.', '>', '<', ',', '"',
            '[', ']', '|', '?', '/', '`'
    };

    public static int randomMinusOne(int length) {
        return getRandom(length - 1);
    }
    /**
     * Random instance, used to generate pseudo-random primitive types.
     */
    private static final RandomGen RANDOM = new RandomGen();
    private static final String[] BLOCKED_WORDS = new String[]{
            ".com", ".net", ".org", "<img", "@cr", "<img=", ":tradereq:", ":duelreq:",
            "<col=", "<shad="};
    public static byte directionDeltaX[] = new byte[]{0, 1, 1, 1, 0, -1, -1, -1};
    public static byte directionDeltaY[] = new byte[]{1, 1, 0, -1, -1, -1, 0, 1};
    public static byte xlateDirectionToClient[] = new byte[]{1, 2, 4, 7, 6, 5, 3, 0};
    public static char xlateTable[] = {' ', 'e', 't', 'a', 'o', 'i', 'h', 'n',
            's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b',
            'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-',
            '&', '*', '\\', '\'', '@', '#', '+', '=', '\243', '$', '%', '"',
            '[', ']'};
    private static ZonedDateTime zonedDateTime;

    public static int getRandom(int length) {
        return RANDOM.get().nextInt(length + 1);
    }

    public static double getRandomDouble(double length) {
        return RANDOM.get().nextDouble(length);
    }

    public static double getRandomDouble() {
        return RANDOM.get().nextDouble();
    }

    public static int getRandomInt() {
        return RANDOM.get().nextInt();
    }

    public static int inclusive(int min, int max) {
        return RANDOM.inclusive(min, max);
    }

    public static String getCurrentServerTime() {
        zonedDateTime = ZonedDateTime.now();
        int hour = zonedDateTime.getHour();
        String hourPrefix = hour < 10 ? "0" + hour + "" : "" + hour + "";
        int minute = zonedDateTime.getMinute();
        String minutePrefix = minute < 10 ? "0" + minute + "" : "" + minute + "";
        return "" + hourPrefix + ":" + minutePrefix + "";
    }
    public static int random(int range) {
        return (int) (java.lang.Math.random() * (range + 1));
    }
    
    public static int random(int min, int max) {
		final int n = Math.abs(max - min);
		return Math.min(min, max) + (n == 0 ? 0 : random(n));
	}
    
    public static String getTimePlayed(long totalPlayTime) {
        final int sec = (int) (totalPlayTime / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }

    public static String formatEnum(final String string) {
        return capitalizeSentence(string.toLowerCase().replace("_", " "));
    }

    public static String capitalizeSentence(final String string) {
        int pos = 0;
        boolean capitalize = true;
        StringBuilder sb = new StringBuilder(string);
        while (pos < sb.length()) {
            if (sb.charAt(pos) == '.') {
                capitalize = true;
            } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
                sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
                capitalize = false;
            }
            pos++;
        }
        return sb.toString();
    }

    public static double getExactDistance(Position a, Position b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public static String getHoursPlayed(long totalPlayTime) {
        final int sec = (int) (totalPlayTime / 1000), h = sec / 3600;
        return (h < 10 ? "0" + h : h) + "h";
    }
    public static String getAOrAn(String nextWord) {
        String s = "a";
        char c = nextWord.toUpperCase().charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        return s;
    }
    public static boolean startsWithVowel(String word) {
        if (word != null) {
            word = word.toLowerCase();
            return (word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o' || word.charAt(0) == 'u');

        }
        return false;
    }


    public static int getMinutesPassed(long t) {
        int seconds = (int) ((t / 1000) % 60);
        int minutes = (int) (((t - seconds) / 1000) / 60);
        return minutes;
    }

    public static int randomNumber(int length) {
        return (int) (java.lang.Math.random() * length);
    }

    public static Item[] concat(Item[] a, Item[] b) {
        int aLen = a.length;
        int bLen = b.length;
        Item[] c = new Item[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    public static Player getCloseRandomPlayer(List<Player> plrs) {
        int index = Misc.getRandom(plrs.size() - 1);
        if (index > 0)
            return plrs.get(index);
        return null;
    }

    public static int direction(int srcX, int srcY, int x, int y) {
        double dx = (double) x - srcX, dy = (double) y - srcY;
        double angle = Math.atan(dy / dx);
        angle = Math.toDegrees(angle);
        if (Double.isNaN(angle))
            return -1;
        if (Math.signum(dx) < 0)
            angle += 180.0;
        return (int) ((((90 - angle) / 22.5) + 16) % 16);
        /*int changeX = x - srcX; int changeY = y - srcY;
		for (int j = 0; j < directionDeltaX.length; j++) {
			if (changeX == directionDeltaX[j] &&
				changeY == directionDeltaY[j])
				return j;

		}
		return -1;*/
    }

    public static String ucFirst(String str) {
        str = str.toLowerCase();
        if (str.length() > 1) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str.toUpperCase();
        }
        return str;
    }

    public static String format(int num) {
        return NumberFormat.getInstance().format(num);
    }

    public static String formatText(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                s = String.format("%s%s", Character.toUpperCase(s.charAt(0)),
                        s.substring(1));
            }
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                if (i + 1 < s.length()) {
                    s = String.format("%s%s%s", s.subSequence(0, i + 1),
                            Character.toUpperCase(s.charAt(i + 1)),
                            s.substring(i + 2));
                }
            }
        }
        return s.replace("_", " ");
    }

    public static String getTotalAmount(int j) {
        if (j >= 10000 && j < 1000000) {
            return j / 1000 + "K";
        } else if (j >= 1000000 && j <= Integer.MAX_VALUE) {
            return j / 1000000 + "M";
        } else {
            return "" + j;
        }
    }

    public static String formatPlayerName(String str) {
        return formatText(str);
    }

    public static String insertCommasToNumber(String number) {
        return number.length() < 4 ? number : insertCommasToNumber(number
                .substring(0, number.length() - 3))
                + ","
                + number.substring(number.length() - 3, number.length());
    }

    public static String textUnpack(byte packedData[], int size) {
    	byte[] decodeBuf = new byte[4096];
        int idx = 0, highNibble = -1;
        for (int i = 0; i < size * 2; i++) {
            int val = packedData[i / 2] >> (4 - 4 * (i % 2)) & 0xf;
            if (highNibble == -1) {
                if (val < 13)
                    decodeBuf[idx++] = (byte) xlateTable[val];
                else
                    highNibble = val;
            } else {
                decodeBuf[idx++] = (byte) xlateTable[((highNibble << 4) + val) - 195];
                highNibble = -1;
            }
        }

        return new String(decodeBuf, 0, idx);
    }
    
    /**
     * Packs text.
     *
     * @param packedData The destination of the packed text.
     * @param text       The unpacked text.
     */
    public static void textPack(byte packedData[], String text) {
        if (text.length() > 80) {
            text = text.substring(0, 80);
        }
        text = text.toLowerCase();
        int carryOverNibble = -1;
        int ofs = 0;
        for (int idx = 0; idx < text.length(); idx++) {
            char c = text.charAt(idx);
            int tableIdx = 0;
            for (int i = 0; i < xlateTable.length; i++) {
                if (c == (byte) xlateTable[i]) {
                    tableIdx = i;
                    break;
                }
            }
            if (tableIdx > 12) {
                tableIdx += 195;
            }
            if (carryOverNibble == -1) {
                if (tableIdx < 13) {
                    carryOverNibble = tableIdx;
                } else {
                    packedData[ofs++] = (byte) tableIdx;
                }
            } else if (tableIdx < 13) {
                packedData[ofs++] = (byte) ((carryOverNibble << 4) + tableIdx);
                carryOverNibble = -1;
            } else {
                packedData[ofs++] = (byte) ((carryOverNibble << 4) + (tableIdx >> 4));
                carryOverNibble = tableIdx & 0xf;
            }
        }
        if (carryOverNibble != -1) {
            packedData[ofs++] = (byte) (carryOverNibble << 4);
        }
    }

    public static String anOrA(String s) {
        s = s.toLowerCase();
        if (s.equalsIgnoreCase("anchovies") || s.equalsIgnoreCase("soft clay") || s.equalsIgnoreCase("cheese") || s.equalsIgnoreCase("ball of wool") || s.equalsIgnoreCase("spice") || s.equalsIgnoreCase("steel nails") || s.equalsIgnoreCase("snape grass") || s.equalsIgnoreCase("coal"))
            return "some";
        if (s.startsWith("a") || s.startsWith("e") || s.startsWith("i") || s.startsWith("o") || s.startsWith("u"))
            return "an";
        return "a";
    }

    @SuppressWarnings("rawtypes")
    public static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    @SuppressWarnings("rawtypes")
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static String removeSpaces(String s) {
        return s.replace(" ", "");
    }

    public static int getMinutesElapsed(int minute, int hour, int day, int year) {
        Calendar i = Calendar.getInstance();

        if (i.get(1) == year) {
            if (i.get(6) == day) {
                if (hour == i.get(11)) {
                    return i.get(12) - minute;
                }
                return (i.get(11) - hour) * 60 + (59 - i.get(12));
            }

            int ela = (i.get(6) - day) * 24 * 60 * 60;
            return ela > 2147483647 ? 2147483647 : ela;
        }

        int ela = getElapsed(day, year) * 24 * 60 * 60;

        return ela > 2147483647 ? 2147483647 : ela;
    }

    public static int getDayOfYear() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int days = 0;
        int[] daysOfTheMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            daysOfTheMonth[1] = 29;
        }
        days += c.get(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < daysOfTheMonth.length; i++) {
            if (i < month) {
                days += daysOfTheMonth[i];
            }
        }
        return days;
    }

    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static int getElapsed(int day, int year) {
        if (year < 2013) {
            return 0;
        }

        int elapsed = 0;
        int currentYear = Misc.getYear();
        int currentDay = Misc.getDayOfYear();

        if (currentYear == year) {
            elapsed = currentDay - day;
        } else {
            elapsed = currentDay;

            for (int i = 1; i < 5; i++) {
                if (currentYear - i == year) {
                    elapsed += 365 - day;
                    break;
                } else {
                    elapsed += 365;
                }
            }
        }

        return elapsed;
    }

    public static boolean isWeekend() {
        int day = Calendar.getInstance().get(7);
        return (day == 1) || (day == 6) || (day == 7);
    }

    public static byte[] readFile(File s) {
        try {
            FileInputStream fis = new FileInputStream(s);
            FileChannel fc = fis.getChannel();
            ByteBuffer buf = ByteBuffer.allocate((int) fc.size());
            fc.read(buf);
            buf.flip();
            fis.close();
            return buf.array();
        } catch (Exception e) {
            System.out.println("FILE : " + s.getName() + " missing.");
            return null;
        }
    }

    public static <T> T randomTypeOfList(List<T> list) {
        return list.get(RANDOM.get().nextInt(list.size()));
    }

    public static int randomInclusive(int min, int max) {
        return Math.min(min, max) + RANDOM.get().nextInt(Math.max(min, max) - Math.min(min, max) + 1);
    }

    public static byte[] getBuffer(File f) throws Exception {
        if (!f.exists())
            return null;
        byte[] buffer = new byte[(int) f.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(f));
        dis.readFully(buffer);
        dis.close();
        byte[] gzipInputBuffer = new byte[999999];
        int bufferlength = 0;
        GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(buffer));
        do {
            if (bufferlength == gzipInputBuffer.length) {
                System.out.println("Error inflating data.\nGZIP buffer overflow.");
                break;
            }
            int readByte = gzip.read(gzipInputBuffer, bufferlength, gzipInputBuffer.length - bufferlength);
            if (readByte == -1)
                break;
            bufferlength += readByte;
        } while (true);
        byte[] inflated = new byte[bufferlength];
        System.arraycopy(gzipInputBuffer, 0, inflated, 0, bufferlength);
        buffer = inflated;
        if (buffer.length < 10)
            return null;
        return buffer;
    }

    public static int getTimeLeft(long start, int timeAmount, TimeUnit timeUnit) {
        start -= timeUnit.toMillis(timeAmount);
        long elapsed = System.currentTimeMillis() - start;
        int toReturn = timeUnit == TimeUnit.SECONDS ? (int) ((elapsed / 1000) % 60) - timeAmount : (int) ((elapsed / 1000) / 60) - timeAmount;
        if (toReturn <= 0)
            toReturn = 1;
        return timeAmount - toReturn;
    }

    /**
     * Gets the formatted time played.
     *
     * @return The time played formatted as a string.
     */
    public static String getFormattedPlayTime(Player player) {
        long different = System.currentTimeMillis() - player.getCreationDate().getTime();


        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

     /*   long days = (long) elapsedJoinDate / 86400; // 86,400
        long daysRemainder = (long) elapsedJoinDate - (days * 86400);
        long hours = (long) daysRemainder / 3600; // 3,600
        long hoursRemainder = (long) daysRemainder - (hours * 3600);
        long minutes = (long) hoursRemainder / 60; // 60
        long seconds = (long) hoursRemainder - (minutes * 60); // remainder*/

        return elapsedDays + " day(s) : " + elapsedHours + " hour(s) : " + elapsedMinutes + " minute(s) : " + elapsedSeconds + " second(s)";

    }

    /**
     * Converts an array of bytes to an integer.
     *
     * @param data the array of bytes.
     * @return the newly constructed integer.
     */
    public static int hexToInt(byte[] data) {
        int value = 0;
        int n = 1000;
        for (int i = 0; i < data.length; i++) {
            int num = (data[i] & 0xFF) * n;
            value += num;
            if (n > 1) {
                n = n / 1000;
            }
        }
        return value;
    }

    public static Position delta(Position a, Position b) {
        return new Position(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * Picks a random element out of any array type.
     *
     * @param array the array to pick the element from.
     * @return the element chosen.
     */
    public static <T> T randomElement(T[] array) {
        return array[(int) (RANDOM.get().nextDouble() * array.length)];
    }

    /**
     * Picks a random element out of any list type.
     *
     * @param list the list to pick the element from.
     * @return the element chosen.
     */
    public static <T> T randomElement(List<T> list) {
        return list.get((int) (RANDOM.get().nextDouble() * list.size()));
    }

    public static boolean blockedWord(String string) {
        for (String s : BLOCKED_WORDS) {
            if (string.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Capitalized all words split by a space char.
     *
     * @param name The string to format.
     */
    public static String capitalizeWords(String name) {
        StringBuilder builder = new StringBuilder(name.length());
        String[] words = name.split("\\s");
        for (int i = 0, l = words.length; i < l; ++i) {
            if (i > 0)
                builder.append(" ");
            builder.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));

        }
        return builder.toString();
    }

    /**
     * Capitalizes the first letter in said string.
     *
     * @param name The string to capitalize.
     * @return The string with the first char capitalized.
     */
    public static String capitalize(String name) {
        if (name.length() < 1)
            return "";
        StringBuilder builder = new StringBuilder(name.length());
        char first = Character.toUpperCase(name.charAt(0));
        builder.append(first).append(name.toLowerCase().substring(1));
        return builder.toString();
    }

    /**
     * Formats the name by checking if it starts with a vowel.
     *
     * @param name The string to format.
     */
    public static String getVowelFormat(String name) {
        char letter = name.charAt(0);
        boolean vowel = letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u';
        String other = vowel ? "an" : "a";
        return other + " " + name;
    }

    /**
     * Checks if a name is valid according to the {@code VALID_PLAYER_CHARACTERS} array.
     *
     * @param name The name to check.
     * @return The name is valid.
     */
    public static boolean isValidName(String name) {
        return formatNameForProtocol(name).matches("[a-z0-9_]+");
    }

    /**
     * Converts a name to a long value.
     *
     * @param string The string to convert to long.
     * @return The long value of the string.
     */
    public static long stringToLong(String string) {
        long l = 0L;
        for (int i = 0; i < string.length() && i < 12; i++) {
            char c = string.charAt(i);
            l *= 37L;
            if (c >= 'A' && c <= 'Z')
                l += (1 + c) - 65;
            else if (c >= 'a' && c <= 'z')
                l += (1 + c) - 97;
            else if (c >= '0' && c <= '9')
                l += (27 + c) - 48;
        }
        while (l % 37L == 0L && l != 0L)
            l /= 37L;
        return l;
    }

    /**
     * Converts a long to a string.
     *
     * @param l The long value to convert to a string.
     * @return The string value.
     */
    public static String longToString(long l) {
        int i = 0;
        char ac[] = new char[12];
        while (l != 0L) {
            long l1 = l;
            l /= 37L;
            ac[11 - i++] = VALID_CHARACTERS[(int) (l1 - l * 37L)];
        }
        return new String(ac, 12 - i, i);
    }

    public static byte[] getBuffer(String file) {
        try {
            java.io.File f = new java.io.File(file);
            if (!f.exists())
                return null;
            byte[] buffer = new byte[(int) f.length()];
            java.io.DataInputStream dis = new java.io.DataInputStream(new java.io.FileInputStream(f));
            dis.readFully(buffer);
            dis.close();
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Formats a name for use in the protocol.
     *
     * @param name The name to format.
     * @return The formatted name.
     */
    public static String formatNameForProtocol(String name) {
        return name.toLowerCase().replace(" ", "_");
    }

    /**
     * Formats a name for in-game display.
     *
     * @param name The name to format.
     * @return The formatted name.
     */
    public static String formatName(String name) {
        return fixName(name.replace(" ", "_"));
    }

    /**
     * Formats a player's name, i.e sets upper case letters after a space.
     *
     * @param name The name to format.
     * @return The formatted name.
     */
    private static String fixName(String name) {
        if (name.length() > 0) {
            final char ac[] = name.toCharArray();
            for (int j = 0; j < ac.length; j++)
                if (ac[j] == '_') {
                    ac[j] = ' ';
                    if ((j + 1 < ac.length) && (ac[j + 1] >= 'a')
                            && (ac[j + 1] <= 'z')) {
                        ac[j + 1] = (char) ((ac[j + 1] + 65) - 97);
                    }
                }

            if ((ac[0] >= 'a') && (ac[0] <= 'z')) {
                ac[0] = (char) ((ac[0] + 65) - 97);
            }
            return new String(ac);
        } else {
            return name;
        }
    }

    /**
     * Hashes a {@code String} using Jagex's algorithm, this method should be
     * used to convert actual names to hashed names to lookup files within the
     * {@link CacheLoader}.
     *
     * @param string The string to hash.
     * @return The hashed string.
     */
    public static int hash(String string) {
        return _hash(string.toUpperCase());
    }

    /**
     * Hashes a {@code String} using Jagex's algorithm, this method should be
     * used to convert actual names to hashed names to lookup files within the
     * {@link CacheLoader}.
     * <p>
     * <p>
     * This method should <i>only</i> be used internally, it is marked
     * deprecated as it does not properly hash the specified {@code String}. The
     * functionality of this method is used to create a proper {@code String}
     * {@link #hash(String) <i>hashing method</i>}. The scope of this method has
     * been marked as {@code private} to prevent confusion.
     * </p>
     *
     * @param string The string to hash.
     * @return The hashed string.
     * @deprecated This method should only be used internally as it does not
     * correctly hash the specified {@code String}. See the note
     * below for more information.
     */
    @Deprecated
    private static int _hash(String string) {
        return IntStream.range(0, string.length()).reduce(0, (hash, index) -> hash * 61 + string.charAt(index) - 32);
    }
    
    public static String findCommand(String message) {
		if (!message.contains(" ") && !message.contains("-")) {
			return message;
		} else if (!message.contains(" ")) {
			return message.substring(0, message.indexOf("-"));
		} else if (!message.contains("-")) {
			return message.substring(0, message.indexOf(" "));
		}
		int seperatorIndex = message.indexOf(" ") < message.indexOf("-") ? message.indexOf(" ") : message.indexOf("-");
		return message.substring(0, seperatorIndex);
	}

	public static String findInput(String message) {
		if (!message.contains(" ") && !message.contains("-")) {
			return "";
		} else if (!message.contains(" ")) {
			return message.substring(message.indexOf("-") + 1);
		} else if (!message.contains("-")) {
			return message.substring(message.indexOf(" ") + 1);
		}
		int seperatorIndex = message.indexOf(" ") < message.indexOf("-") ? message.indexOf(" ") : message.indexOf("-");
		return message.substring(seperatorIndex + 1);
	}
	
    public static String wrap(final String str, final int wrapLength) {
        return wrap(str, wrapLength, null, false);
    }

    public static String wrap(final String str, final int wrapLength, final String newLineStr, final boolean wrapLongWords) {
        return wrap(str, wrapLength, newLineStr, wrapLongWords, " ");
    }
    
    public static String wrap(final String str, int wrapLength, String newLineStr, final boolean wrapLongWords, String wrapOn) {
        if (str == null) {
            return null;
        }
        if (newLineStr == null) {
            newLineStr = System.lineSeparator();
        }
        if (wrapLength < 1) {
            wrapLength = 1;
        }
        if (StringUtils.isBlank(wrapOn)) {
            wrapOn = " ";
        }
        final Pattern patternToWrapOn = Pattern.compile(wrapOn);
        final int inputLineLength = str.length();
        int offset = 0;
        final StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);

        while (offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset, Math.min(offset + wrapLength + 1, inputLineLength)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    offset += matcher.end();
                    continue;
                }
                spaceToWrapAt = matcher.start() + offset;
            }

            // only last line without leading spaces is left
            if(inputLineLength - offset <= wrapLength) {
                break;
            }

            while(matcher.find()){
                spaceToWrapAt = matcher.start() + offset;
            }

            if (spaceToWrapAt >= offset) {
                // normal case
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;

            } else {
                // really long word or URL
                if (wrapLongWords) {
                    // wrap really long word one line at a time
                    wrappedLine.append(str, offset, wrapLength + offset);
                    wrappedLine.append(newLineStr);
                    offset += wrapLength;
                } else {
                    // do not wrap really long word, just extend beyond limit
                    matcher = patternToWrapOn.matcher(str.substring(offset + wrapLength));
                    if (matcher.find()) {
                        spaceToWrapAt = matcher.start() + offset + wrapLength;
                    }

                    if (spaceToWrapAt >= 0) {
                        wrappedLine.append(str, offset, spaceToWrapAt);
                        wrappedLine.append(newLineStr);
                        offset = spaceToWrapAt + 1;
                    } else {
                        wrappedLine.append(str, offset, str.length());
                        offset = inputLineLength;
                    }
                }
            }
        }

        // Whatever is left in line is short enough to just pass through
        wrappedLine.append(str, offset, str.length());

        return wrappedLine.toString();
    }
    
	public static <T> List<T> jsonArrayToList(Path path, Class<T[]> clazz) {
		try {
			T[] collection = new Gson().fromJson(Files.newBufferedReader(path), clazz);
			return new ArrayList<T>(Arrays.asList(collection));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isNonNegative(int input) {
		return input > -1;
	}

	public static boolean isNonNegative(int[] input) {
		for (int value : input) {
			if (!isNonNegative(value)) {
				return false;
			}
		}
		return true;
	}

	public static String[] nullToEmpty(int i) {
		String[] output = new String[i];
		Arrays.fill(output, 0, i, "");
		return output;
	}

	public static int randomSearch(int[] elements, int inclusive, int exclusiveLength) {
		Preconditions.checkArgument(exclusiveLength <= elements.length, "The length specified is greater than the length of the array.");
		return elements[RandomUtils.nextInt(inclusive, exclusiveLength)];
	}

	public static <T> T randomSearch(T[] elements, int inclusive, int exclusiveLength) {
		Preconditions.checkArgument(exclusiveLength <= elements.length, "The length specified is greater than the length of the array.");
		return elements[RandomUtils.nextInt(inclusive, exclusiveLength)];
	}

	public static int random(Range<Integer> range) {
		int minimum = range.getMinimum();
		return minimum + random(range.getMaximum() - minimum);
	}

	public static Item getRandomItem(Item[] itemArray) {
		return itemArray[random(itemArray.length - 1)];
	}

	public static Item getRandomItem(List<Item> itemArray) {
		return itemArray.get(random(itemArray.size() - 1));
	}

}
