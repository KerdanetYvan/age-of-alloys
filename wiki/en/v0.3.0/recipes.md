---
layout: default
title: Recipes
permalink: /en/v0.3.0/recipes/
alt: /v0.3.0/recettes/
lang: en
version: v0.3.0
key: recipes
---

# Recipes

Every recipe of the mod in one place: the foundry, smelting the ores, the alloys, then the tools, weapons and armor. Crafting table recipes unlock as soon as you own an ingot of the metal.

## The foundry {#foundry}

At a crafting table:

| | | |
|---|---|---|
| Stone bricks | Bucket | Stone bricks |
| Stone bricks | _(empty)_ | Stone bricks |
| Smooth stone | Smooth stone | Smooth stone |
{: .craft-grid }

Four stone bricks, a bucket and three smooth stones. This recipe unlocks as soon as you own stone bricks. How it works: [the foundry]({{ '/en/v0.3.0/foundry/' | relative_url }}).

## Smelting the ores

The raw item and the two ore blocks (stone and deepslate) are smelted into an ingot:

| Where | Time |
|---|---:|
| Furnace | 10 s |
| Blast furnace | 5 s |

## The alloys {#alloys}

In [the foundry]({{ '/en/v0.3.0/foundry/' | relative_url }}). Each recipe takes **10 seconds** (200 ticks) and asks for a **heat level**: see [heat]({{ '/en/v0.3.0/foundry/' | relative_url }}#heat).

| Alloy | Ingredients | Result | Heat |
|---|---|---:|:-:|
| [Bronze]({{ '/en/v0.3.0/bronze/' | relative_url }}) | 3 copper ingots + 1 tin ingot | 4 ingots | 1 |
| [Duralumin]({{ '/en/v0.3.0/duralumin/' | relative_url }}) | 5 aluminum + 1 copper + 1 magnesium + 1 manganese | 8 ingots | 2 |
| [Steel]({{ '/en/v0.3.0/steel/' | relative_url }}) | 4 iron ingots + 1 coal (or charcoal) | 4 ingots | 3 |

Ingredients are matched by tag (`forge:ingots/<metal>`): a copper, tin or iron ingot, or an ingot from another mod that carries the same tag, works.

## Tools and weapons

At a crafting table, with **ingots of the alloy** (bronze, duralumin or steel) and **sticks**. The shapes are Minecraft's: an iron tool is crafted the same way, with the ingot of the alloy instead.

| Item | Shape | Ingots | Sticks |
|---|---|---:|---:|
| Pickaxe | 3 ingots on top, 2 sticks in the middle column | 3 | 2 |
| Axe | 2 ingots on top, 1 ingot and 1 stick in the middle row, 1 stick below | 3 | 2 |
| Shovel | 1 ingot, 2 sticks below | 1 | 2 |
| Hoe | 2 ingots on top, 2 sticks below | 2 | 2 |
| Sword | 2 ingots one above the other, 1 stick below | 2 | 1 |

## Armor

At a crafting table, with ingots of the alloy.

| Piece | Shape | Ingots |
|---|---|---:|
| Helmet | 5 ingots: a row of 3, then the two sides | 5 |
| Chestplate | 8 ingots: everything except the middle of the top row | 8 |
| Leggings | 7 ingots: a row of 3, then two columns | 7 |
| Boots | 4 ingots: two columns of 2 | 4 |

## Repairing

At an anvil, with an ingot of the alloy. Tools and armor can also be repaired by combining two of them.
