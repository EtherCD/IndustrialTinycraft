#====Created by: EtherCD====
import colorsys
from PIL import Image
import sys

templates = {
    "dust": {
        "file": "./templates/items/dust.png",
        "pixels" : [
            [x, y] for x in range(0, 16) for y in range(0, 16)]
    },
    "purified_dust": {
        "file": "./templates/items/purified_dust.png",
        "pixels" : [
            [x, y] for x in range(0, 16) for y in range(0, 16)]
    },
    "tiny_dust": {
        "file": "./templates/items/tiny_dust.png",
        "pixels" : [
            [x, y] for x in range(0, 16) for y in range(0, 16)]
    },
    "ingot": {
        "file": "./templates/items/ingot.png",
        "pixels" : [
            [x, y] for x in range(0, 16) for y in range(0, 16)]
    },
    "plate": {
        "file": "./templates/items/plate.png",
        "pixels" : [
            [x, y] for x in range(0, 16) for y in range(0, 16)]
    },
    "dense_plate": {
        "file": "templates/items/dense_plate.png",
        "pixels" : [
            [x, y] for x in range(0, 16) for y in range(0, 16)]
    }
}

mod_id = "industrialtinycraft"

def generate(template_name, result_name, tc):
    input_tc = tc.split(",")
    rgb = int(input_tc[0]), int(input_tc[1]), int(input_tc[2])
    normalized_rgb = tuple(val / 255.0 for val in rgb)
    hsv = colorsys.rgb_to_hsv(*normalized_rgb)
    target_color = tuple([round(hsv[0] * 255), round(hsv[1] * 100), 0])
    with Image.open(templates[template_name]["file"]) as image:
        try:
            alpha = image.getchannel('A')
            image_hsv = image.convert('HSV')
            image_hsv_pixels = image.load()

            for pixel in templates[template_name]["pixels"]:
                original_pixel = image_hsv_pixels[int(pixel[0]), int(pixel[1])]
                new_color = (
                    target_color[0],
                    target_color[1],
                    original_pixel[2]
                )
                print(original_pixel, new_color)
                image_hsv.putpixel([int(pixel[0]), int(pixel[1])], new_color)
            result = image_hsv.convert('RGBA')
            result.putalpha(alpha)

            result.save(result_name)
            print(f"Texture generated on {result_name}")
        except IOError: 
            print("IO ERROR") # Hello World

def main():
    parts = sys.argv
    if parts[1] == "generate":
        if parts[2] == "resource":
            name = parts[3]
            target_color = input("Put color (R,G,B 0...255) <- ")
            generate("dust", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_dust.png", target_color)
            generate("ingot", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_ingot.png", target_color)
            generate("tiny_dust", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_small.png", target_color)
            generate("plate", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_plate.png", target_color)
            generate("dense_plate", f"./src/main/resources/assets/{mod_id}/textures/items/dense_{name}_plate.png", target_color)
        if parts[2] == "purified":
            name = parts[3]
            target_color = input("Put color (R,G,B 0...255) <- ")
            generate("purified_dust", f"./src/main/resources/assets/{mod_id}/textures/items/purified_{name}.png", target_color)
        if parts[2] == "processor":
            name = parts[3]
            target_color = input("Put chip color (R,G,B 0...255) <- ")
            target_color = input("Put substrate color (R,G,B 0...255) <- ")
            generate("chip", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_chip.png", target_color)
            generate("processor", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_processor.png", target_color)
            generate("substrate", f"./src/main/resources/assets/{mod_id}/textures/items/{name}_substrate.png", target_color)
    if parts[1] == "bleach":
        name = input("Put asset name <- ")
        texture_util.texture_bleach(name)


if __name__ == '__main__':
    main()