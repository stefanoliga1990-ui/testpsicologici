"""Generate Spazio Test brand assets from the canonical vector geometry."""

from pathlib import Path
from PIL import Image, ImageDraw, ImageFont


ROOT = Path(__file__).resolve().parents[1]
OUTPUT = ROOT / "src" / "main" / "resources" / "static" / "images" / "brand"

GREEN = "#245c4c"
PAPER = "#fffefa"
CREAM = "#f8f7f2"
INK = "#263330"


def cubic(p0, p1, p2, p3, steps=32):
    points = []
    for index in range(steps):
        t = index / steps
        u = 1 - t
        points.append((
            u**3 * p0[0] + 3 * u**2 * t * p1[0] + 3 * u * t**2 * p2[0] + t**3 * p3[0],
            u**3 * p0[1] + 3 * u**2 * t * p1[1] + 3 * u * t**2 * p2[1] + t**3 * p3[1],
        ))
    return points


def spark_points(center_x, center_y, radius):
    top = (center_x, center_y - radius)
    right = (center_x + radius, center_y)
    bottom = (center_x, center_y + radius)
    left = (center_x - radius, center_y)
    inner = radius * 0.10
    shoulder = radius * 0.39
    return (
        cubic(top, (center_x + inner, center_y - shoulder), (center_x + shoulder, center_y - inner), right)
        + cubic(right, (center_x + shoulder, center_y + inner), (center_x + inner, center_y + shoulder), bottom)
        + cubic(bottom, (center_x - inner, center_y + shoulder), (center_x - shoulder, center_y + inner), left)
        + cubic(left, (center_x - shoulder, center_y - inner), (center_x - inner, center_y - shoulder), top)
    )


def logo_image(size, background=None, circle_ratio=0.875):
    scale = 4
    canvas = Image.new("RGBA", (size * scale, size * scale), background or (0, 0, 0, 0))
    draw = ImageDraw.Draw(canvas)
    center = size * scale / 2
    circle_radius = size * scale * circle_ratio / 2
    draw.ellipse(
        (center - circle_radius, center - circle_radius, center + circle_radius, center + circle_radius),
        fill=GREEN,
    )
    spark_radius = circle_radius * 0.46
    draw.polygon(spark_points(center, center, spark_radius), fill=PAPER)
    return canvas.resize((size, size), Image.Resampling.LANCZOS)


def font(size, bold=False):
    windows_fonts = Path("C:/Windows/Fonts")
    names = ["arialbd.ttf", "segoeuib.ttf"] if bold else ["arial.ttf", "segoeui.ttf"]
    for name in names:
        path = windows_fonts / name
        if path.exists():
            return ImageFont.truetype(path, size=size)
    return ImageFont.load_default(size=size)


def generate_open_graph():
    width, height = 1200, 630
    image = Image.new("RGB", (width, height), CREAM)
    draw = ImageDraw.Draw(image)

    label = "Spazio Test"
    label_font = font(96, bold=True)
    mark_size = 176
    gap = 42
    label_width = draw.textlength(label, font=label_font)
    group_width = mark_size + gap + label_width
    group_x = (width - group_width) / 2
    center_y = height / 2

    mark = logo_image(mark_size)
    image.paste(mark, (round(group_x), round(center_y - mark_size / 2)), mark)
    draw.text(
        (round(group_x + mark_size + gap), round(center_y)),
        label,
        font=label_font,
        fill=INK,
        anchor="lm",
    )
    image.save(OUTPUT / "og-default.png", format="PNG", optimize=True)
    image.save(OUTPUT / "og-spazio-test.png", format="PNG", optimize=True)


def main():
    OUTPUT.mkdir(parents=True, exist_ok=True)

    logo = logo_image(512)
    logo.save(OUTPUT / "logo-512.png", format="PNG", optimize=True)

    apple_icon = logo_image(180, background=PAPER, circle_ratio=0.72)
    apple_icon.save(OUTPUT / "apple-touch-icon.png", format="PNG", optimize=True)

    favicon_16 = logo_image(16, circle_ratio=0.94)
    favicon_32 = logo_image(32, circle_ratio=0.94)
    favicon_16.save(OUTPUT / "favicon-16x16.png", format="PNG", optimize=True)
    favicon_32.save(OUTPUT / "favicon-32x32.png", format="PNG", optimize=True)
    logo_image(48, circle_ratio=0.94).save(
        OUTPUT / "favicon.ico",
        format="ICO",
        sizes=[(16, 16), (32, 32), (48, 48)],
    )

    generate_open_graph()


if __name__ == "__main__":
    main()
