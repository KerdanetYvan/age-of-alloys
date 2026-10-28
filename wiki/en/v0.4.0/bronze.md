---
layout: default
title: Bronze
permalink: /en/v0.4.0/bronze/
alt: /v0.4.0/bronze/
lang: en
version: v0.4.0
key: bronze
nav: alloys
---

# Bronze

Bronze is the **sturdy mid-game alloy**: it has the power of iron but lasts longer. In real life it is copper and tin, already used in the bronze age.

{% include alloy-hero.html alloy="bronze" %}

> **In short:** 3 copper ingots + 1 tin ingot make 4 bronze ingots, with any fuel (heat 1). The simplest of the three alloys, and the first to make. Back to [all the alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}).

## Making it

It is melted in [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}), with any fuel (see [heat]({{ '/en/v0.4.0/foundry/' | relative_url }}#heat)). The copper comes from Minecraft, the tin is found in [the ores]({{ '/en/v0.4.0/ores/' | relative_url }}). All the recipes (tools, weapons, armor, blocks): [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "bronze" | first %}{% include foundry.html r=r %}
</div>

## Its character

{% include traits.html alloy="bronze" %}

## Tools and weapons

All of them mine at iron level. Shapes and quantities: [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

{% include stats-tools.html alloy="bronze" %}

## Armor

It protects like iron armor, but lasts longer. It is repaired at an anvil with a bronze ingot.

{% include armor-row.html alloy="bronze" %}

## To try next

With bronze, you can look for magnesium and manganese (an iron-level pickaxe, so a bronze one, is enough) to make [duralumin]({{ '/en/v0.4.0/duralumin/' | relative_url }}).
