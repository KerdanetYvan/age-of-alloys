---
layout: default
title: Foundry
permalink: /en/v0.3.0/foundry/
alt: /v0.3.0/fonderie/
lang: en
version: v0.3.0
key: foundry
---

# The alloy foundry

The foundry is the block that **melts several ingots into one alloy**. You put the ingredients and a fuel in it; the more advanced the alloy, the hotter the fuel must be. Its crafting recipe and the alloy recipes are in [the recipes]({{ '/en/v0.3.0/recipes/' | relative_url }}).

## How to use it

- It is placed facing the player and mined with a pickaxe (stone or better); it drops its contents when broken.
- A right click opens its screen: **4 input slots**, **1 fuel slot** (it only accepts what burns) and **1 output slot** (nothing can be put in it).
- Shift + click moves items quickly. Hoppers can fill and empty it, from the top, the sides and the bottom.
- It only burns fuel when it has something to melt. It lights up and gives off light (level 13). The arrow shows the progress of the melting, the flame what is left of the fuel.

## Heat {#heat}

Each recipe asks for a **heat level**. A hotter fuel also suits the cooler recipes. The heat shows on the flame of the foundry: orange, red-orange or blue depending on the level.

| Level | You need | Examples |
|---|---|---|
| 1 | any fuel | planks, sticks, logs... |
| 2 | a fuel that burns for at least **1,600 ticks** | coal, charcoal, blaze rod |
| 3 | a fuel that burns for at least **10,000 ticks** | coal block, lava bucket |

A fuel that is too cool for the recipe **is not consumed**. A lava bucket leaves an empty bucket.
