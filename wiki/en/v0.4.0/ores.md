---
layout: default
title: Ores
permalink: /en/v0.4.0/ores/
alt: /v0.4.0/minerais/
lang: en
version: v0.4.0
key: ores
---

# The ores

Four metals are found in the world: **tin**, **aluminum**, **magnesium** and **manganese**. Each exists as a **stone** ore and as a **deepslate** ore, and appears in **every biome of the Overworld**. They are used to make the [alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}).

> **Important:** the ores only generate in chunks created with the mod. Create a new world, or go to an area you have never explored.

## Where to dig

The height (Y) gives the zone; in the middle of the zone the veins are more numerous, towards the edges they are rarer. On each card: the stone ore, the deepslate ore, the raw item it drops, then the ingot.

<div class="ore-cards">
{% for o in site.data.ores %}{% include ore-card.html o=o %}{% endfor %}
</div>

"Iron-level pickaxe" means iron, bronze, duralumin or steel: they all mine at iron level. Duralumin tools mine rock and every ore **twice as fast** (see [duralumin]({{ '/en/v0.4.0/duralumin/' | relative_url }})).

## What an ore drops

Each ore drops its **raw item** (raw tin, raw aluminum, etc.), with more items under Fortune. With Silk Touch, you get the **block** itself.

## Smelting into ingots

The raw item and both ore blocks smelt into an ingot: **10 s in a furnace, 5 s in a blast furnace.** The ingots are then used to make the alloys in [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}).

## The blocks

Nine raw items make a **raw block**, and nine ingots a **block of ingots**, for each of the four metals. A block is mined with a pickaxe: stone, or iron for magnesium and manganese. The recipes are in [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}#blocks).

{% include gallery.html ids="raw_tin_block,raw_aluminum_block,raw_magnesium_block,raw_manganese_block,tin_block,aluminum_block,magnesium_block,manganese_block" s=3 %}
