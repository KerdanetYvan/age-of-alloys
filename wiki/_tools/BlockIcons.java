import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

/**
 * Draws the inventory-style (isometric) icon of the blocks shown on the wiki, from the textures of their faces.
 * Usage (JDK 17+, from the repository root):
 *   java wiki/_tools/BlockIcons.java <client-extra.jar> src/main/resources/assets/ageofalloys/textures wiki/assets/items
 * Output: 32x32 PNG per block, top lit most, then the left face, then the right face.
 */
public class BlockIcons {
    static final int SIZE = 32;
    // id -> {top, left (front), right (side)}; "mc:" is read from the Minecraft jar, "aoa:" from the mod textures.
    static final String[][] BLOCKS = {
        {"stone_bricks", "mc:stone_bricks", "mc:stone_bricks", "mc:stone_bricks"},
        {"smooth_stone", "mc:smooth_stone", "mc:smooth_stone", "mc:smooth_stone"},
        {"coal_block", "mc:coal_block", "mc:coal_block", "mc:coal_block"},
        {"furnace", "mc:furnace_top", "mc:furnace_front", "mc:furnace_side"},
        {"blast_furnace", "mc:blast_furnace_top", "mc:blast_furnace_front", "mc:blast_furnace_side"},
        {"alloy_foundry", "aoa:alloy_foundry_top", "aoa:alloy_foundry_front", "aoa:alloy_foundry_side"},
        {"tin_ore", "aoa:tin_ore", "aoa:tin_ore", "aoa:tin_ore"},
        {"deepslate_tin_ore", "aoa:deepslate_tin_ore", "aoa:deepslate_tin_ore", "aoa:deepslate_tin_ore"},
        {"aluminum_ore", "aoa:aluminum_ore", "aoa:aluminum_ore", "aoa:aluminum_ore"},
        {"deepslate_aluminum_ore", "aoa:deepslate_aluminum_ore", "aoa:deepslate_aluminum_ore", "aoa:deepslate_aluminum_ore"},
        {"magnesium_ore", "aoa:magnesium_ore", "aoa:magnesium_ore", "aoa:magnesium_ore"},
        {"deepslate_magnesium_ore", "aoa:deepslate_magnesium_ore", "aoa:deepslate_magnesium_ore", "aoa:deepslate_magnesium_ore"},
        {"manganese_ore", "aoa:manganese_ore", "aoa:manganese_ore", "aoa:manganese_ore"},
        {"deepslate_manganese_ore", "aoa:deepslate_manganese_ore", "aoa:deepslate_manganese_ore", "aoa:deepslate_manganese_ore"},
        {"tin_block", "aoa:tin_block", "aoa:tin_block", "aoa:tin_block"},
        {"aluminum_block", "aoa:aluminum_block", "aoa:aluminum_block", "aoa:aluminum_block"},
        {"magnesium_block", "aoa:magnesium_block", "aoa:magnesium_block", "aoa:magnesium_block"},
        {"manganese_block", "aoa:manganese_block", "aoa:manganese_block", "aoa:manganese_block"},
        {"bronze_block", "aoa:bronze_block", "aoa:bronze_block", "aoa:bronze_block"},
        {"duralumin_block", "aoa:duralumin_block", "aoa:duralumin_block", "aoa:duralumin_block"},
        {"steel_block", "aoa:steel_block", "aoa:steel_block", "aoa:steel_block"},
        {"raw_tin_block", "aoa:raw_tin_block", "aoa:raw_tin_block", "aoa:raw_tin_block"},
        {"raw_aluminum_block", "aoa:raw_aluminum_block", "aoa:raw_aluminum_block", "aoa:raw_aluminum_block"},
        {"raw_magnesium_block", "aoa:raw_magnesium_block", "aoa:raw_magnesium_block", "aoa:raw_magnesium_block"},
        {"raw_manganese_block", "aoa:raw_manganese_block", "aoa:raw_manganese_block", "aoa:raw_manganese_block"},
    };

    public static void main(String[] args) throws Exception {
        ZipFile jar = new ZipFile(args[0]);
        File modDir = new File(args[1]);
        File out = new File(args[2]);
        for (String[] b : BLOCKS) {
            BufferedImage top = load(jar, modDir, b[1]), left = load(jar, modDir, b[2]), right = load(jar, modDir, b[3]);
            ImageIO.write(render(top, left, right), "png", new File(out, b[0] + ".png"));
        }
    }

    static BufferedImage load(ZipFile jar, File modDir, String ref) throws Exception {
        String name = ref.substring(ref.indexOf(':') + 1);
        if (ref.startsWith("mc:")) {
            String path = "assets/minecraft/textures/block/" + name + ".png";
            try (InputStream in = jar.getInputStream(jar.getEntry(path))) { return ImageIO.read(in); }
        }
        return ImageIO.read(new File(modDir, "block/" + name + ".png"));
    }

    static BufferedImage render(BufferedImage top, BufferedImage left, BufferedImage right) {
        BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
        double e = SIZE / 2.0;                    // edge of the cube in pixels
        double a = e * Math.sqrt(3) / 2;          // half width of the hexagon
        double cx = SIZE / 2.0, h = e / 2;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                double px = x + 0.5 - cx, py = y + 0.5;
                // top face: P = W + u*(a,-h) + v*(a,h), W = (-a, h)
                double u = ((px + a) / a + (py - h) / -h) / 2;
                double v = ((px + a) / a - (py - h) / -h) / 2;
                if (in(u) && in(v)) { img.setRGB(x, y, shade(sample(top, u, v), 1.0)); continue; }
                // left face: P = W + u*(a,h) + v*(0,e)
                u = (px + a) / a;
                v = (py - h - u * h) / e;
                if (in(u) && in(v)) { img.setRGB(x, y, shade(sample(left, u, v), 0.8)); continue; }
                // right face: P = S + u*(a,-h) + v*(0,e), S = (0, e)
                u = px / a;
                v = (py - e + u * h) / e;
                if (in(u) && in(v)) img.setRGB(x, y, shade(sample(right, u, v), 0.6));
            }
        }
        return img;
    }

    static boolean in(double t) { return t >= 0 && t < 1; }

    static int sample(BufferedImage t, double u, double v) {
        int size = t.getWidth();
        return t.getRGB(Math.min(size - 1, (int) (u * size)), Math.min(size - 1, (int) (v * size)));
    }

    static int shade(int argb, double f) {
        int a = argb >>> 24, r = (int) (((argb >> 16) & 255) * f), g = (int) (((argb >> 8) & 255) * f), b = (int) ((argb & 255) * f);
        return a << 24 | r << 16 | g << 8 | b;
    }
}
