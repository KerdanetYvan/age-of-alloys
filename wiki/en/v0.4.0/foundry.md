---
layout: default
title: Foundry
permalink: /en/v0.4.0/foundry/
alt: /v0.4.0/fonderie/
lang: en
version: v0.4.0
key: foundry
---

# The alloy foundry

The foundry is the block that **melts several ingots into one alloy**. You put the ingredients and a fuel in it; the more advanced the alloy, the hotter the fuel must be. Its crafting recipe and the alloy recipes are in [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}); the alloys themselves are presented on [the Alloys page]({{ '/en/v0.4.0/alloys/' | relative_url }}).

## How to use it

- It is placed facing the player and mined with a pickaxe (stone or better); it drops its contents when broken.
- A right click opens its screen: **4 input slots**, **1 fuel slot** (it only accepts what burns) and **1 output slot** (nothing can be put in it).
- Shift + click moves items quickly. Hoppers can fill and empty it, from the top, the sides and the bottom.
- It only burns fuel when it has something to melt. It lights up and gives off light (level 13). The arrow shows the progress of the melting, the flame what is left of the fuel.
- **It only melts what the recipe asks for.** An item that is not part of the recipe, put in the inputs, blocks the melting until it is removed: iron ore put with the tin and the copper stops the bronze.

## The recipe book {#the-recipe-book}

A button on the left of the screen, under the inputs, opens the **recipe book** next to the window, like the furnace one.

- It shows the **unlocked** recipes. A recipe unlocks as soon as you hold one of its ingredients: a copper or tin ingot for bronze, for example.
- Hovering a recipe shows its ingredients with their quantities, the **heat** it needs and the **melting time**.
- It is for **looking things up**: clicking a recipe does not put anything in the slots, it is up to you to fill the foundry.

## Heat {#heat}

Each recipe asks for a **heat level**. A hotter fuel also suits the cooler recipes. The heat shows on the flame of the foundry: orange, red-orange or blue depending on the level.

| Level | You need | Examples |
|---|---|---|
| 1 | any fuel | {% include icon.html id="stick" s=2 %} planks, sticks, logs... |
| 2 | a fuel that burns for at least **1,600 ticks** | {% include icon.html id="coal" s=2 %} {% include icon.html id="charcoal" s=2 %} {% include icon.html id="blaze_rod" s=2 %} coal, charcoal, blaze rod |
| 3 | a fuel that burns for at least **10,000 ticks** | {% include icon.html id="coal_block" s=2 %} {% include icon.html id="lava_bucket" s=2 %} coal block, lava bucket |

A fuel that is too cool for the recipe **is not consumed**. A lava bucket leaves an empty bucket.

### A hot fire burns fast

In the foundry, the reserve of a fuel goes down **faster when it is hot**: level 1 burns normally, level 2 **twice as fast**, level 3 **three times as fast**. The furnace does not change. A melting takes 10 seconds (200 ticks):

| Fuel | Level | Time in the furnace | Time in the foundry | Meltings |
|---|---|---:|---:|---:|
| {% include icon.html id="stick" s=2 %} Planks, sticks | 1 | unchanged | unchanged | depends on the item |
| {% include icon.html id="coal" s=2 %} {% include icon.html id="charcoal" s=2 %} Coal, charcoal | 2 | 80 s | 40 s | 4 |
| {% include icon.html id="blaze_rod" s=2 %} Blaze rod | 2 | 120 s | 60 s | 6 |
| {% include icon.html id="coal_block" s=2 %} Coal block | 3 | 800 s | 267 s (4 min 27 s) | 26 |
| {% include icon.html id="lava_bucket" s=2 %} Lava bucket | 3 | 1,000 s | 333 s (5 min 33 s) | 33 |

A coal block therefore makes 26 meltings: with steel at one ingot per melting, that is 26 steel ingots.
