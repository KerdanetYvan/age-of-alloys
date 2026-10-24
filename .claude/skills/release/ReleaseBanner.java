import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Generates the banner of a release: the project banner without text (docs/assets/banner-notext.png)
 * with "AoA v<version>" written in the style of the title of docs/assets/banner.png.
 *
 * <p>Run it from the repository root, with JDK 17 and no dependency:
 * <pre>
 *   java .claude/skills/release/ReleaseBanner.java 0.1.0 docs/assets/releases/banner-v0.1.0.png
 *   java .claude/skills/release/ReleaseBanner.java --check
 * </pre>
 *
 * <p>The art is drawn on the 320x80 grid of the banner, then enlarged by an integer (x4) with
 * nearest-neighbor, never smoothed (see docs/identity.md). The letters are the classic 5x7 pixel font,
 * enlarged x2, like the original title. {@code --check} redraws "AGE OF ALLOYS" and compares it to
 * docs/assets/banner.png, to prove that the drawing rules still match the original.
 */
public final class ReleaseBanner {
    private static final String BASE = "docs/assets/banner-notext.png";
    private static final String ORIGINAL = "docs/assets/banner.png";
    private static final int SCALE = 4;
    /** Size in pixels, on the 320x80 grid, of one dot of the 5x7 font. */
    private static final int MODULE = 2;
    private static final int CELL = 5 * MODULE;
    private static final int GAP = MODULE;
    /** Top row of the letters and horizontal center of the title, measured on docs/assets/banner.png. */
    private static final int TOP = 25;
    private static final int CENTER_X = 177;
    /** Left edge of the free area: the anvil of the banner ends before it. */
    private static final int MIN_X = 85;

    private static final int DARK = 0x080809;
    /**
     * Where the dark pixels (outline and drop shadow) fall, as offsets of the letters. They were fitted on
     * the title of docs/assets/banner.png, whose shadow is hand-drawn: they reproduce about 91 % of it.
     */
    private static final int[][] SHADOW = {{-1, 0}, {1, 0}, {-1, 1}, {1, 2}, {3, 1}, {3, 2}, {2, 3}};
    /** Most dark pixels that may differ from the original title in --check. */
    private static final int MAX_SHADOW_DIFF = 130;
    /** Most letter-colored pixels of the background (sparks) that may sit in the zone of the title. */
    private static final int MAX_SPARKS = 5;
    /** Colors of the letters from top to bottom, each used for four rows. */
    private static final int[] GRADIENT = {0xF3BC82, 0xE8A55F, 0xCD7F32, 0x8A5A2B};

    private static final Map<Character, String[]> FONT = new HashMap<>();
    /** Where the letters were drawn by the last call to render, used by --check. */
    private static boolean[][] lastBody;

    static {
        glyph('A', ".###.", "#...#", "#...#", "#####", "#...#", "#...#", "#...#");
        glyph('E', "#####", "#....", "#....", "####.", "#....", "#....", "#####");
        glyph('F', "#####", "#....", "#....", "####.", "#....", "#....", "#....");
        glyph('G', ".###.", "#...#", "#....", "#.###", "#...#", "#...#", ".####");
        glyph('L', "#....", "#....", "#....", "#....", "#....", "#....", "#####");
        glyph('O', ".###.", "#...#", "#...#", "#...#", "#...#", "#...#", ".###.");
        glyph('S', ".####", "#....", "#....", ".###.", "....#", "....#", "####.");
        glyph('Y', "#...#", "#...#", ".#.#.", "..#..", "..#..", "..#..", "..#..");
        glyph('o', ".....", ".....", ".###.", "#...#", "#...#", "#...#", ".###.");
        glyph('v', ".....", ".....", "#...#", "#...#", "#...#", ".#.#.", "..#..");
        glyph('0', ".###.", "#...#", "#..##", "#.#.#", "##..#", "#...#", ".###.");
        glyph('1', "..#..", ".##..", "..#..", "..#..", "..#..", "..#..", ".###.");
        glyph('2', ".###.", "#...#", "....#", "...#.", "..#..", ".#...", "#####");
        glyph('3', "#####", "...#.", "..#..", "...#.", "....#", "#...#", ".###.");
        glyph('4', "...#.", "..##.", ".#.#.", "#..#.", "#####", "...#.", "...#.");
        glyph('5', "#####", "#....", "####.", "....#", "....#", "#...#", ".###.");
        glyph('6', "..##.", ".#...", "#....", "####.", "#...#", "#...#", ".###.");
        glyph('7', "#####", "....#", "...#.", "..#..", ".#...", ".#...", ".#...");
        glyph('8', ".###.", "#...#", "#...#", ".###.", "#...#", "#...#", ".###.");
        glyph('9', ".###.", "#...#", "#...#", ".####", "....#", "...#.", ".##..");
        glyph('.', ".", ".", ".", ".", ".", ".", "#");
        glyph(' ', ".....", ".....", ".....", ".....", ".....", ".....", ".....");
    }

    private static void glyph(char c, String... rows) {
        FONT.put(c, rows);
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 1 && args[0].equals("--check")) {
            System.exit(check());
        }
        if (args.length != 2 || !args[0].matches("\\d+\\.\\d+\\.\\d+")) {
            System.err.println("usage: ReleaseBanner <X.Y.Z> <output.png> | ReleaseBanner --check");
            System.exit(2);
        }
        BufferedImage banner = render("AoA v" + args[0], CENTER_X);
        File out = new File(args[1]);
        if (out.getParentFile() != null) {
            out.getParentFile().mkdirs();
        }
        ImageIO.write(banner, "png", out);
        System.out.println("banner written: " + out + " (" + banner.getWidth() + "x" + banner.getHeight() + ")");
    }

    /**
     * Redraws the original title. Every letter pixel drawn here must have the color of the original, and the
     * original must not have letter-colored pixels left undrawn, except the few sparks of the background that
     * happen to use the same colors. The dark pixels may differ a little (see SHADOW).
     */
    private static int check() throws IOException {
        BufferedImage original = ImageIO.read(new File(ORIGINAL));
        BufferedImage redrawn = render("AGE OF ALLOYS", 100 + widthOf("AGE OF ALLOYS") / 2);
        int wrong = 0;
        int undrawn = 0;
        int dark = 0;
        // Only the zone of the title is compared: the anvil and the sparks use the same colors.
        for (int y = TOP - 3; y < TOP + 20; y++) {
            for (int x = 95; x < 262; x++) {
                int expected = original.getRGB(x * SCALE, y * SCALE) & 0xFFFFFF;
                int actual = redrawn.getRGB(x * SCALE, y * SCALE) & 0xFFFFFF;
                if (lastBody[y][x]) {
                    if (expected != actual) {
                        wrong++;
                    }
                } else if (isBody(expected)) {
                    undrawn++;
                } else if ((expected == DARK) != (actual == DARK)) {
                    dark++;
                }
            }
        }
        System.out.println("letter pixels with the wrong color: " + wrong + " (must be 0)");
        System.out.println("letter-colored pixels left undrawn: " + undrawn + " (at most " + MAX_SPARKS + ", sparks of the background)");
        System.out.println("dark pixels different from the original title: " + dark + " (at most " + MAX_SHADOW_DIFF + ")");
        return wrong == 0 && undrawn <= MAX_SPARKS && dark <= MAX_SHADOW_DIFF ? 0 : 1;
    }

    private static boolean isBody(int color) {
        for (int gradient : GRADIENT) {
            if (gradient == color) {
                return true;
            }
        }
        return false;
    }

    private static BufferedImage render(String text, int centerX) throws IOException {
        BufferedImage source = ImageIO.read(new File(BASE));
        int w = source.getWidth() / SCALE;
        int h = source.getHeight() / SCALE;
        int[][] pixels = new int[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int color = source.getRGB(x * SCALE, y * SCALE) & 0xFFFFFF;
                for (int dy = 0; dy < SCALE; dy++) {
                    for (int dx = 0; dx < SCALE; dx++) {
                        if ((source.getRGB(x * SCALE + dx, y * SCALE + dy) & 0xFFFFFF) != color) {
                            throw new IllegalStateException(BASE + " is not made of uniform " + SCALE + "x" + SCALE + " blocks");
                        }
                    }
                }
                pixels[y][x] = color;
            }
        }

        int width = widthOf(text);
        int left = centerX - width / 2;
        if (left < MIN_X || left + width + 3 > w) {
            throw new IllegalStateException("the text does not fit in the free area of the banner");
        }

        boolean[][] body = new boolean[h][w];
        int x = left;
        for (char c : text.toCharArray()) {
            String[] rows = FONT.get(c);
            if (rows == null) {
                throw new IllegalArgumentException("no glyph for '" + c + "'");
            }
            for (int row = 0; row < rows.length; row++) {
                for (int col = 0; col < rows[row].length(); col++) {
                    if (rows[row].charAt(col) == '#') {
                        fill(body, x + col * MODULE, TOP + row * MODULE);
                    }
                }
            }
            x += rows[0].length() * MODULE + GAP;
        }

        lastBody = body;

        // Dark layer: the letters shifted by the SHADOW offsets.
        boolean[][] dark = new boolean[h][w];
        for (int y = 0; y < h; y++) {
            for (int px = 0; px < w; px++) {
                if (body[y][px]) {
                    for (int[] offset : SHADOW) {
                        mark(dark, px + offset[0], y + offset[1]);
                    }
                }
            }
        }
        for (int y = 0; y < h; y++) {
            for (int px = 0; px < w; px++) {
                if (dark[y][px]) {
                    pixels[y][px] = DARK;
                }
                if (body[y][px]) {
                    pixels[y][px] = GRADIENT[Math.min((y - TOP) / 4, GRADIENT.length - 1)];
                }
            }
        }

        BufferedImage out = new BufferedImage(w * SCALE, h * SCALE, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < h * SCALE; y++) {
            for (int px = 0; px < w * SCALE; px++) {
                out.setRGB(px, y, 0xFF000000 | pixels[y / SCALE][px / SCALE]);
            }
        }
        return out;
    }

    private static int widthOf(String text) {
        int width = -GAP;
        for (char c : text.toCharArray()) {
            String[] rows = FONT.get(c);
            if (rows == null) {
                throw new IllegalArgumentException("no glyph for '" + c + "'");
            }
            width += rows[0].length() * MODULE + GAP;
        }
        return width;
    }

    private static void fill(boolean[][] mask, int x, int y) {
        for (int dy = 0; dy < MODULE; dy++) {
            for (int dx = 0; dx < MODULE; dx++) {
                mask[y + dy][x + dx] = true;
            }
        }
    }

    private static void mark(boolean[][] mask, int x, int y) {
        if (y >= 0 && y < mask.length && x >= 0 && x < mask[0].length) {
            mask[y][x] = true;
        }
    }
}
