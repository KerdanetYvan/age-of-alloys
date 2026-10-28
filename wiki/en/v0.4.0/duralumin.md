---
layout: default
title: Duralumin
permalink: /en/v0.4.0/duralumin/
alt: /v0.4.0/duralumin/
lang: en
version: v0.4.0
key: duralumin
nav: alloys
---

# Duralumin

Duralumin is the alloy of **hurried miners**: light, it mines much faster than the others, and takes the best enchantments. In real life it is an aviation alloy: mostly aluminum, with a little copper, magnesium and manganese.

{% include alloy-hero.html alloy="duralumin" %}

> **In short:** 5 aluminum + 1 copper + 1 magnesium + 1 manganese make 8 duralumin ingots, with a heat 2 fuel (at least a coal). Its tools mine rock and ores **twice as fast**. Back to [all the alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}).

## Making it

It is melted in [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}) with a **heat 2** fuel (see [heat]({{ '/en/v0.4.0/foundry/' | relative_url }}#heat)). You therefore have to find the four metals: [aluminum and tin with a stone pickaxe, magnesium and manganese with an iron-level pickaxe]({{ '/en/v0.4.0/ores/' | relative_url }}). The copper comes from Minecraft. All the recipes: [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "duralumin" | first %}{% include foundry.html r=r %}
</div>

## Its character

{% include traits.html alloy="duralumin" %}

## Tools and weapons

All of them mine at iron level. The sword hits more often (2.0 times per second) but a bit less hard per hit: per second, it deals a little more damage than iron.

{% include stats-tools.html alloy="duralumin" %}

## Armor

It protects like iron armor, but lasts a bit less, and it is the easiest to enchant well. It is repaired at an anvil with a duralumin ingot.

{% include armor-row.html alloy="duralumin" %}

## To try next

Duralumin lets you dig faster to find what [steel]({{ '/en/v0.4.0/steel/' | relative_url }}) needs.
