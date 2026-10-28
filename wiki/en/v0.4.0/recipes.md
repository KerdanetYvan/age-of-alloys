---
layout: default
title: Recipes
permalink: /en/v0.4.0/recipes/
alt: /v0.4.0/recettes/
lang: en
version: v0.4.0
key: recipes
---

# The recipes

Every recipe of the mod, as on the screen of the game. The crafting table recipes unlock as soon as you hold one of their ingredients, and the foundry ones are also in its [recipe book]({{ '/en/v0.4.0/foundry/' | relative_url }}#the-recipe-book). The items in the cells alternate when several fit.

## The foundry {#foundry}

At a crafting table. This recipe unlocks as soon as you hold stone bricks, a bucket or smooth stone. How it works: [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}).

<div class="cards">
{% for r in site.data.recipes.bench %}{% include craft.html r=r %}{% endfor %}
</div>

## Smelting the ores

The raw item and both ore blocks (stone and deepslate) smelt into an ingot: **10 s in a furnace, 5 s in a blast furnace.**

<div class="cards cards-small">
{% for r in site.data.recipes.smelt %}{% include smelt.html r=r %}{% endfor %}
</div>

## Purified coal {#purified-coal}

**Coal** cooked in a furnace (**20 s**) or a blast furnace (**10 s**). Charcoal does not give any. It is used for [steel]({{ '/en/v0.4.0/steel/' | relative_url }}).

<div class="cards cards-small">
{% for r in site.data.recipes.purify %}{% include smelt.html r=r %}{% endfor %}
</div>

## The alloys {#alloys}

In [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}). Each recipe takes **10 seconds** and asks for a **heat level**: see [heat]({{ '/en/v0.4.0/foundry/' | relative_url }}#heat). The bottom cell is the fuel. The three alloys are presented together on the [Alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}) page.

<div class="cards">
{% for r in site.data.recipes.foundry %}{% include foundry.html r=r %}{% endfor %}
</div>

The ingots are recognized by their tag (`forge:ingots/<metal>`): a copper, tin, iron ingot or one from another mod that carries the same tag works. Sheets: [bronze]({{ '/en/v0.4.0/bronze/' | relative_url }}), [duralumin]({{ '/en/v0.4.0/duralumin/' | relative_url }}), [steel]({{ '/en/v0.4.0/steel/' | relative_url }}).

## Tools and weapons

At a crafting table. The shapes are Minecraft's: an iron tool is crafted the same way. The ingots and the result alternate between bronze, duralumin and steel: **the shape is the same for the three**.

<div class="cards">
{% for r in site.data.recipes.tools %}{% include craft.html r=r %}{% endfor %}
</div>

## Armor

At a crafting table, same principle.

<div class="cards">
{% for r in site.data.recipes.armor %}{% include craft.html r=r %}{% endfor %}
</div>

## The blocks {#blocks}

At a crafting table: **nine ingots** (or **nine raw items**) fill the grid and make a block. The other way round, **a single block** in the grid gives its nine items back.

<div class="cards">
{% for b in site.data.recipes.blocks %}{% include block.html b=b %}{% endfor %}
</div>

## Repairing

At an anvil, with an ingot of the alloy. Tools and armor can also be repaired by combining them with each other.
