---
layout: default
title: Alloys
permalink: /en/v0.4.0/alloys/
alt: /v0.4.0/alliages/
lang: en
version: v0.4.0
key: alloys
---

# The alloys

The mod adds three alloys: **bronze**, **duralumin** and **steel**. Each has its own role, its recipe in [the foundry]({{ '/en/v0.4.0/foundry/' | relative_url }}) and its **character**: a bonus for the armor, another one for the tools and weapons. Click a card to open the sheet of the alloy, with every figure.

<div class="alloy-cards">
{% include alloy-card.html alloy="bronze" %}
{% include alloy-card.html alloy="duralumin" %}
{% include alloy-card.html alloy="steel" %}
</div>

## From the ore to the armor

<div class="chain">
<span class="chain-step">{% include icon.html id="tin_ore" s=4 %}Ore</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="tin_ingot" s=4 %}Ingot</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="alloy_foundry" s=4 %}Foundry</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="bronze_ingot" s=4 %}Alloy</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_pickaxe" s=4 %}Gear</span>
</div>

You dig the [ores]({{ '/en/v0.4.0/ores/' | relative_url }}), smelt the raw item into an ingot in a furnace, mix the ingots in the foundry with a fuel that is hot enough, then craft tools, weapons and armor at a crafting table ([the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }})). [How to play]({{ '/en/v0.4.0/how-to-play/' | relative_url }}) goes through these steps one by one.

## Bronze {#bronze}

**Sturdy and simple to get**: the power of iron, but more than twice the durability. It is the first alloy to make. It takes Minecraft's copper and [tin]({{ '/en/v0.4.0/ores/' | relative_url }}), with any fuel (heat 1).

<div class="alloy-section">
<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "bronze" | first %}{% include foundry.html r=r %}
</div>
{% include traits.html alloy="bronze" %}
</div>

[The bronze sheet]({{ '/en/v0.4.0/bronze/' | relative_url }})

## Duralumin {#duralumin}

**Light, fast, highly enchantable**: the alloy of hurried miners. It takes aluminum, copper, magnesium and manganese, so the [four metals]({{ '/en/v0.4.0/ores/' | relative_url }}) (magnesium and manganese need an iron-level pickaxe), with at least a coal (heat 2).

<div class="alloy-section">
<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "duralumin" | first %}{% include foundry.html r=r %}
</div>
{% include traits.html alloy="duralumin" %}
</div>

[The duralumin sheet]({{ '/en/v0.4.0/duralumin/' | relative_url }})

## Steel {#steel}

**The best alloy, just below diamond**, and the most demanding: 4 iron ingots and 1 [purified coal]({{ '/en/v0.4.0/recipes/' | relative_url }}#purified-coal) (coal cooked in a furnace) for **a single** ingot, with a coal block or a lava bucket (heat 3). A full armor takes 96 iron ingots.

<div class="alloy-section">
<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "steel" | first %}{% include foundry.html r=r %}
</div>
{% include traits.html alloy="steel" %}
</div>

[The steel sheet]({{ '/en/v0.4.0/steel/' | relative_url }})

## Comparing them

| | {% include icon.html id="bronze_ingot" s=2 %} Bronze | {% include icon.html id="duralumin_ingot" s=2 %} Duralumin | {% include icon.html id="steel_ingot" s=2 %} Steel |
|---|---|---|---|
| Role | durable | fast, enchantable | powerful |
| Heat | 1 | 2 | 3 |
| Ingots per batch | 4 | 8 | 1 |
| Tool durability | 400 | 300 | 1,000 |
| Mining speed | 6 | 9 | 7.5 |
| Armor (full set) | 15 | 15 | 18 |
| Armor durability | 1,100 | 715 | 1,485 |

For Minecraft's references (iron, diamond) and the detail of each piece, see [the items]({{ '/en/v0.4.0/items/' | relative_url }}).
