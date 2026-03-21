import sys

prompt = sys.argv[1]
print(prompt)

parts = prompt.split(",")
mod_id="industrialtinycraft"

def gen_item_model(model_name):
    return """{
  "parent": "item/generated",
  "textures": {
    "layer0": \"""" + mod_id + """:items/""" + model_name + """"
  }
}"""

if parts[0] == "gen":
    if parts[1] == "text":
        if parts[2] == "item": # "gen,text,item"
            model_name = parts[3]
        
            with open(f"src/main/resources/assets/{mod_id}/models/item/{model_name}.json", "w+") as file:
                file.write(gen_item_model(model_name))

