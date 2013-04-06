/**
 * Colors.java- Class of all colors so I don't waste time trying to find that
 * damn character
 *
 * @author James
 */
public class Colors {

    public static final String Black = "\u00A70";
    public static final String Navy = "\u00A71";
    public static final String Green = "\u00A72";
    public static final String Blue = "\u00A73";
    public static final String Red = "\u00A74";
    public static final String Purple = "\u00A75";
    public static final String Gold = "\u00A76";
    public static final String LightGray = "\u00A77";
    public static final String Gray = "\u00A78";
    public static final String DarkPurple = "\u00A79";
    public static final String LightGreen = "\u00A7a";
    public static final String LightBlue = "\u00A7b";
    public static final String Rose = "\u00A7c";
    public static final String LightPurple = "\u00A7d";
    public static final String Yellow = "\u00A7e";
    public static final String White = "\u00A7f";
    public static final String Random = "\u00A7k";
    public static final String Bold = "\u00A7l";
    public static final String Strike = "\u00A7m";
    public static final String Underline = "\u00A7n";
    public static final String Italic = "\u00A7o";
    public static final String Reset = "\u00A7r";
    public static final String Marker = "\u00A7";

    /* Helper methods */

    /**
     * Strips a string of all (valid) colors.
     *
     * @param toStrip The string to strip
     * @return The stripped string
     */
    public static String strip(String toStrip) {
        return toStrip.replaceAll("\u00a7[0-9a-fk-or]", "");
    }

    /**
     * Convert colors to their ANSI equivalents.
     * This is useful for displaying colors on the terminal.
     *
     * @param toConvert The string to convert
     * @return The string with ANSI colors
     */
    public static String toANSI(String toConvert) {
        StringBuilder ansi = new StringBuilder();
        char[] data = toConvert.toCharArray();

        for (int i = 0; i < data.length; i++) {
            if (data[i] == '\u00a7') {
                char c = data[++i];
                ansi.append('\033').append('[');
                if (c >= '0' && c <= '9' || c >= 'a' && c <= 'f') {
                    ansi.append('0').append(';');
                    String term = System.getenv("TERM");
                    // Ugly, but the best we can do without external libraries.
                    if (term != null && term.contains("256color")) {
                        ansi.append(ansiColor256(c));
                    } else {
                        ansi.append(ansiColor16(c));
                    }
                    ansi.append('m');
                } else if (c >= 'k' && c <= 'o' || c == 'r') {
                    String s;
                    switch (c) {
                        case 'k':
                            s = "7"; // Use inverse, ANSI doesn't have a random
                            break;
                        case 'l':
                            s = "1";
                            break;
                        case 'm':
                            s = "9";
                            break;
                        case 'n':
                            s = "4";
                            break;
                        case 'o':
                            s = "3";
                            break;
                        case 'r':
                            s = "0";
                            break;
                        default:
                            s = "28"; // No-op (reveal)
                    }
                    ansi.append(s).append('m');
                } else {
                    ansi.append('\u00a7').append(c);
                }
            } else {
                ansi.append(data[i]);
            }
        }

        ansi.append("\033[m");
        return ansi.toString();
    }

    private static char[] ansiColor16(char c) {
        char[] ret = new char[2];
        ret[0] = '3';
        switch (c) {
            case '0':
            case '8':
                ret[1] = '0';
                break;
            case '1':
            case '9':
                ret[1] = '4';
                break;
            case '2':
            case 'a':
                ret[1] = '2';
                break;
            case '3':
            case 'b':
                ret[1] = '6';
                break;
            case '4':
            case 'c':
                ret[1] = '1';
                break;
            case '5':
            case 'd':
                ret[1] = '5';
                break;
            case '6':
            case 'e':
                ret[1] = '3';
                break;
            case '7':
            case 'f':
                ret[1] = '7';
                break;
        }
        return ret;
    }

    private static String ansiColor256(char c) {
        // Yes I'm lazy.
        int i = ansiColor16(c)[1] - '0';
        if (c > '7')
            i += 8;
        return "38;5;" + i;
    }
}
