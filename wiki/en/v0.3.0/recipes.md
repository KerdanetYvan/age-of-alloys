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

Every recipe of the mod, as on the game screen. Crafting table recipes unlock as soon as you own an ingot of the metal. Items in a slot take turns when several work.

## The foundry {#foundry}

At a crafting table. This recipe unlocks as soon as you own stone bricks. How it works: [the foundry]({{ '/en/v0.3.0/foundry/' | relative_url }}).

<div class="cards">
{% for r in site.data.recipes.bench %}{% include craft.html r=r %}{% endfor %}
</div>

## Smelting the ores

The raw item and the two ore blocks (stone and deepslate) are smelted into an ingot: **10 s in a furnace, 5 s in a blast furnace.**

<div class="cards cards-small">
{% for r in site.data.recipes.smelt %}{% include smelt.html r=r %}{% endfor %}
</div>

## The alloys {#alloys}

In [the foundry]({{ '/en/v0.3.0/foundry/' | relative_url }}). Each recipe takes **10 seconds** and asks for a **heat level**: see [heat]({{ '/en/v0.3.0/foundry/' | relative_url }}#heat). The bottom slot is the fuel.

<div class="cards">
{% for r in site.data.recipes.foundry %}{% include foundry.html r=r %}{% endfor %}
</div>

Ingredients are matched by tag (`forge:ingots/<metal>`): a copper, tin or iron ingot, or an ingot from another mod that carries the same tag, works. Pages: [bronze]({{ '/en/v0.3.0/bronze/' | relative_url }}), [duralumin]({{ '/en/v0.3.0/duralumin/' | relative_url }}), [steel]({{ '/en/v0.3.0/steel/' | relative_url }}).

## Tools and weapons

At a crafting table. The shapes are Minecraft's: an iron tool is crafted the same way. The ingots and the result take turns between bronze, duralumin and steel: **the shape is the same for all three**.

<div class="cards">
{% for r in site.data.recipes.tools %}{% include craft.html r=r %}{% endfor %}
</div>

## Armor

At a crafting table, same principle.

<div class="cards">
{% for r in site.data.recipes.armor %}{% include craft.html r=r %}{% endfor %}
</div>

## Repairing

At an anvil, with an ingot of the alloy. Tools and armor can also be repaired by combining two of them.
