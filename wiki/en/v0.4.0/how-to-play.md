---
layout: default
title: How to play
permalink: /en/v0.4.0/how-to-play/
alt: /v0.4.0/comment-jouer/
lang: en
version: v0.4.0
key: howto
---

# How to play

From the first ingot to steel armor, step by step. Everything is played in **survival**. To install the mod, see [the home page]({{ '/en/v0.4.0/' | relative_url }}#installation).

> **Important:** the ores only appear in **chunks created with the mod**. Create a new world, or go to an area you have never explored.

## Step by step

1. {% include icon.html id="tin_ore" s=3 %} **Find tin and aluminum** in stone (a stone pickaxe is enough), and Minecraft's **copper**. See [the ores]({{ '/en/v0.4.0/ores/' | relative_url }}).
2. {% include icon.html id="furnace" s=3 %} **Smelt** the raw items into ingots, in a furnace or a blast furnace.
3. {% include icon.html id="alloy_foundry" s=3 %} **Craft the foundry** at a crafting table: its recipe is in [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}#foundry).
4. {% include icon.html id="bronze_ingot" s=3 %} **Make [bronze]({{ '/en/v0.4.0/bronze/' | relative_url }})**: 3 copper ingots and 1 tin ingot, with any fuel.
5. {% include icon.html id="duralumin_ingot" s=3 %} With an iron-level pickaxe, **look for magnesium and manganese** (deeper) to make **[duralumin]({{ '/en/v0.4.0/duralumin/' | relative_url }})**, with a heat 2 fuel (a coal).
6. {% include icon.html id="steel_ingot" s=3 %} For **[steel]({{ '/en/v0.4.0/steel/' | relative_url }})**, you need iron, **purified coal** (a coal cooked in a furnace or a blast furnace) and a very hot fuel (a coal block or a lava bucket).
7. {% include icon.html id="steel_chestplate" s=3 %} **Craft** tools, weapons and armor with the ingots: [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}). With the four pieces of an armor, a bonus kicks in: see [the alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}).
{: .steps}

## The guide in the game

The mod has its own **advancement** tab, "Age of Alloys" (**L** key by default). It appears as soon as you pick up your **first raw metal**, and guides you in nine steps. The texts give hints without spoiling the recipes: they are in the recipe book of the foundry, or in [the recipes]({{ '/en/v0.4.0/recipes/' | relative_url }}).

<div class="chain">
<span class="chain-step">{% include icon.html id="tin_ore" s=3 %}Prospector</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="manganese_ore" s=3 %}A Tour of the Metals</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="alloy_foundry" s=3 %}A Crucible</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="bronze_ingot" s=3 %}First Alloy</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="duralumin_ingot" s=3 %}Hotter</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_ingot" s=3 %}Almost Diamond</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="bronze_pickaxe" s=3 %}Alloy Tools</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_helmet" s=3 %}Under Armor</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_block" s=3 %}The Best of Every Metal</span>
</div>

## Good to know

<div class="tip-tiles">
{% capture t %}"Iron level" means iron, bronze, duralumin or steel: they all mine at iron level. Magnesium and manganese need one.{% endcapture %}{% include tile.html icon="iron_ingot" title="Pickaxe level" text=t %}
{% capture t %}Each alloy needs a fuel that is hot enough, and the hotter it is, the faster the foundry burns it. A fuel that is too cool is not consumed. See <a href="{{ '/en/v0.4.0/foundry/' | relative_url }}#heat">heat</a>.{% endcapture %}{% include tile.html icon="coal_block" title="Heat" text=t %}
{% capture t %}It opens next to the foundry and shows the recipes you have unlocked. See <a href="{{ '/en/v0.4.0/foundry/' | relative_url }}#the-recipe-book">the foundry</a>.{% endcapture %}{% include tile.html icon="alloy_foundry" title="The recipe book" text=t %}
{% capture t %}As soon as you hold one of their ingredients: an ingot of the metal for the crafting table, stone bricks, a bucket or smooth stone for the foundry.{% endcapture %}{% include tile.html icon="stone_bricks" title="Recipes unlock" text=t %}
{% capture t %}Nine ingots make a block, and the block gives its nine ingots back: handy to store and to build. See <a href="{{ '/en/v0.4.0/recipes/' | relative_url }}#blocks">the recipes</a>.{% endcapture %}{% include tile.html icon="steel_block" title="Blocks" text=t %}
{% capture t %}Hover an alloy armor or tool to see its character, and the bonus of the set in green when you wear the four pieces.{% endcapture %}{% include tile.html icon="bronze_chestplate" title="Tooltips" text=t %}
{% capture t %}More raw items with Fortune, the ore block itself with Silk Touch. Everything is in the "Age of Alloys" creative tab.{% endcapture %}{% include tile.html icon="raw_tin" title="Fortune, Silk Touch and creative" text=t %}
</div>

## Which alloy to choose?

<div class="alloy-cards">
{% include alloy-card.html alloy="bronze" %}
{% include alloy-card.html alloy="duralumin" %}
{% include alloy-card.html alloy="steel" %}
</div>

The three are compared on the [Alloys]({{ '/en/v0.4.0/alloys/' | relative_url }}) page, and the figures to compare with Minecraft are in [the items]({{ '/en/v0.4.0/items/' | relative_url }}).
