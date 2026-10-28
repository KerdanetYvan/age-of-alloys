---
layout: default
title: Steel
permalink: /en/v0.4.0/steel/
alt: /v0.4.0/acier/
lang: en
version: v0.4.0
key: steel
nav: alloys
---

# Steel

Steel is the **best alloy** of the mod: a notch below diamond on every criterion. In real life it is iron with a little carbon; here, the carbon is **purified coal**.

{% include alloy-hero.html alloy="steel" %}

> **In short:** 4 iron ingots + 1 purified coal make 1 steel ingot, with a heat 3 fuel (a coal block or a lava bucket). The most demanding of the three alloys. Back to [all the alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}).

## Making it

It is melted in [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}) with a **heat 3** fuel (see [heat]({{ '/en/v0.4.0/foundry/' | relative_url }}#heat)). **Purified coal** is coal cooked in a furnace (20 s) or a blast furnace (10 s), not charcoal: see [the recipe]({{ '/en/v0.4.0/recipes/' | relative_url }}#purified-coal).

<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "steel" | first %}{% include foundry.html r=r %}
</div>

An ingot therefore costs 4 iron and 1 purified coal: a full steel armor takes 24 ingots, that is 96 iron ingots. A coal block gives 26 meltings (26 ingots) in the foundry. All the recipes: [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

## Its character

{% include traits.html alloy="steel" %}

## Tools and weapons

All of them mine at iron level. Shapes and quantities: [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

{% include stats-tools.html alloy="steel" %}

## Armor

It protects almost like diamond and has toughness. It is repaired at an anvil with a steel ingot.

{% include armor-row.html alloy="steel" %}
