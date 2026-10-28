---
layout: default
title: Items
permalink: /en/v0.4.0/items/
alt: /v0.4.0/objets/
lang: en
version: v0.4.0
key: items
---

# The items

Everything the mod adds, by category, with the figures to compare. The _iron_ and _diamond_ references are Minecraft's. Everything is in the "Age of Alloys" creative tab.

## The alloys

Each alloy gives **5 tools and weapons** (pickaxe, axe, shovel, hoe, sword) and **4 armor pieces** (helmet, chestplate, leggings, boots). Click a card to open its sheet.

<div class="alloy-cards">
{% include alloy-card.html alloy="bronze" %}
{% include alloy-card.html alloy="duralumin" %}
{% include alloy-card.html alloy="steel" %}
</div>

The three are compared on the [Alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}) page, and the recipes are in [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

## Tools and weapons

All of them mine at iron level.

| | {% include icon.html id="bronze_pickaxe" s=2 %} Bronze | {% include icon.html id="duralumin_pickaxe" s=2 %} Duralumin | {% include icon.html id="steel_pickaxe" s=2 %} Steel | _Iron_ |
|---|---:|---:|---:|---:|
| Durability | 400 | 300 | 1,000 | _250_ |
| Mining speed | 6 | 9 | 7.5 | _6_ |
| Enchantability | 12 | 22 | 9 | _14_ |
| Sword damage | 6 | 5 | 6.5 | _6_ |
| Sword attack speed | 1.6 | 2.0 | 1.6 | _1.6_ |

## Armor

| Full set | {% include icon.html id="bronze_chestplate" s=2 %} Bronze | {% include icon.html id="duralumin_chestplate" s=2 %} Duralumin | {% include icon.html id="steel_chestplate" s=2 %} Steel | _Iron_ | _Diamond_ |
|---|---:|---:|---:|---:|---:|
| Protection | 15 | 15 | 18 | _15_ | _20_ |
| Durability | 1,100 | 715 | 1,485 | _825_ | _1,815_ |
| Toughness (per piece) | 0 | 0 | 1 | _0_ | _2_ |
| Enchantability | 10 | 22 | 9 | _9_ | _10_ |

The detail of each piece is on the sheet of the alloy.

## The alloy traits

Each alloy has its own **character**, without any enchantment: a small effect on the armor pieces, a bonus when you wear the four pieces, and an effect on its tools and weapons. The tooltips announce it: the bonus of the set shows in green when you wear the four pieces.

### {% include icon.html id="bronze_ingot" s=2 %} Bronze

{% include traits.html alloy="bronze" %}

### {% include icon.html id="duralumin_ingot" s=2 %} Duralumin

{% include traits.html alloy="duralumin" %}

### {% include icon.html id="steel_ingot" s=2 %} Steel

{% include traits.html alloy="steel" %}

## The ingots

Seven ingots: the three alloys and the four metals of the world. Other mods recognize them through their tags (`forge:ingots/<metal>`).

{% include gallery.html ids="bronze_ingot,duralumin_ingot,steel_ingot,tin_ingot,aluminum_ingot,magnesium_ingot,manganese_ingot" %}

## Purified coal

Coal cooked in a furnace or a blast furnace (not charcoal). It brings the carbon of steel: 4 iron ingots and 1 purified coal make 1 steel ingot. It does not burn. The recipe is in [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}#purified-coal).

{% include gallery.html ids="purified_coal" %}

## Ores and raw items

Each metal of the world has two ores (stone and deepslate) and a raw item. Where to find them: [the ores]({{ '/en/v0.4.0/ores/' | relative_url }}).

{% include gallery.html ids="tin_ore,deepslate_tin_ore,raw_tin,aluminum_ore,deepslate_aluminum_ore,raw_aluminum,magnesium_ore,deepslate_magnesium_ore,raw_magnesium,manganese_ore,deepslate_manganese_ore,raw_manganese" s=3 %}

## The blocks

Eleven storage blocks, crafted both ways: a **block of nine ingots** for each of the seven metals, and a **block of nine raw items** for tin, aluminum, magnesium and manganese. They are as hard as an iron block and are mined with a pickaxe (stone, or iron for magnesium and manganese). Recipes: [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}#blocks).

{% include gallery.html ids="bronze_block,duralumin_block,steel_block,tin_block,aluminum_block,magnesium_block,manganese_block,raw_tin_block,raw_aluminum_block,raw_magnesium_block,raw_manganese_block" %}

## The foundry

One block, the **alloy foundry**, with its recipe book: [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}).

{% include gallery.html ids="alloy_foundry" %}
