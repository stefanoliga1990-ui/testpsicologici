"""Generate Spazio Test brand assets from the canonical vector geometry."""

from pathlib import Path
from PIL import Image, ImageDraw, ImageFont


ROOT = Path(__file__).resolve().parents[1]
OUTPUT = ROOT / "src" / "main" / "resources" / "static" / "images" / "brand"

GREEN = "#245c4c"
GREEN_DARK = "#183f34"
PAPER = "#fffefa"
CREAM = "#f8f7f2"
MINT = "#dceee3"
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

    draw.ellipse((770, -250, 1370, 350), fill=MINT)
    draw.ellipse((900, 350, 1270, 720), outline=GREEN, width=3)
    draw.ellipse((960, 410, 1210, 660), outline=GREEN, width=2)
    draw.polygon(spark_points(1045, 175, 30), fill=GREEN)
    draw.polygon(spark_points(900, 470, 16), fill=GREEN)

    mark = logo_image(116)
    image.paste(mark, (96, 78), mark)
    draw.text((236, 99), "Spazio Test", font=font(54, bold=True), fill=INK)
    draw.text((96, 260), "Uno spazio per osservarti", font=font(48, bold=True), fill=INK)
    draw.text((96, 318), "con più chiarezza.", font=font(48, bold=True), fill=GREEN)
    draw.text(
        (96, 420),
        "Questionari informativi su benessere psicologico e relazioni,",
        font=font(25),
        fill=GREEN_DARK,
    )
    draw.text((96, 458), "con risultati immediati e senza registrazione.", font=font(25), fill=GREEN_DARK)
    draw.rounded_rectangle((96, 538, 430, 542), radius=2, fill=GREEN)
    image.save(OUTPUT / "og-default.png", format="PNG", optimize=True)


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
