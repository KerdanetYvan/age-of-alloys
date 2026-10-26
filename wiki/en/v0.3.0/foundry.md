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

The foundry is the block that **melts several ingots into one alloy**. You put the ingredients and a fuel in it; the more advanced the alloy, the hotter the fuel must be.

## Its recipe (crafting table)

| | | |
|---|---|---|
| Stone bricks | Bucket | Stone bricks |
| Stone bricks | _(empty)_ | Stone bricks |
| Smooth stone | Smooth stone | Smooth stone |
{: .craft-grid }

Four stone bricks, a bucket and three smooth stones.

## Heat

Each recipe asks for a **heat level**. A hotter fuel also suits the cooler recipes. The heat shows on the flame of the foundry: orange, red-orange or blue depending on the level.

| Level | You need | Examples |
|---|---|---|
| 1 | any fuel | planks, sticks, logs... |
| 2 | a fuel that burns for at least **1,600 ticks** | coal, charcoal, blaze rod |
| 3 | a fuel that burns for at least **10,000 ticks** | coal block, lava bucket |

A fuel that is too cool for the recipe **is not consumed**. A lava bucket leaves an empty bucket.

## The alloy recipes

Each recipe takes **10 seconds** (200 ticks).

| Alloy | Ingredients | Result | Heat |
|---|---|---:|:-:|
| [Bronze]({{ '/en/v0.3.0/bronze/' | relative_url }}) | 3 copper ingots + 1 tin ingot | 4 ingots | 1 |
| [Duralumin]({{ '/en/v0.3.0/duralumin/' | relative_url }}) | 5 aluminum + 1 copper + 1 magnesium + 1 manganese | 8 ingots | 2 |
| [Steel]({{ '/en/v0.3.0/steel/' | relative_url }}) | 4 iron ingots + 1 coal (or charcoal) | 4 ingots | 3 |

Ingredients are matched by tag (`forge:ingots/<metal>`): a copper, tin or iron ingot, or an ingot from another mod that carries the same tag, works.
