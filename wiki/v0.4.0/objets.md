---
layout: default
title: Objets
permalink: /v0.4.0/objets/
alt: /en/v0.4.0/items/
lang: fr
version: v0.4.0
key: items
---

# Les objets

Tout ce que le mod ajoute, par catégorie, avec les chiffres pour comparer. Les repères _fer_ et _diamant_ sont ceux de Minecraft. Tout est dans l'onglet créatif « Age of Alloys ».

## Les alliages

Chaque alliage donne **5 outils et armes** (pioche, hache, pelle, houe, épée) et **4 pièces d'armure** (casque, plastron, jambières, bottes). Cliquez sur une carte pour ouvrir sa fiche.

<div class="alloy-cards">
{% include alloy-card.html alloy="bronze" %}
{% include alloy-card.html alloy="duralumin" %}
{% include alloy-card.html alloy="steel" %}
</div>

Les trois sont comparés sur la page [Alliages]({{ '/v0.4.0/alliages/' | relative_url }}), et les recettes sont dans [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

## Outils et armes

Tous minent au niveau du fer.

| | {% include icon.html id="bronze_pickaxe" s=2 %} Bronze | {% include icon.html id="duralumin_pickaxe" s=2 %} Duralumin | {% include icon.html id="steel_pickaxe" s=2 %} Acier | _Fer_ |
|---|---:|---:|---:|---:|
| Durabilité | 400 | 300 | 1 000 | _250_ |
| Vitesse de minage | 6 | 9 | 7,5 | _6_ |
| Enchantabilité | 12 | 22 | 9 | _14_ |
| Dégâts de l'épée | 6 | 5 | 6,5 | _6_ |
| Vitesse d'attaque de l'épée | 1,6 | 2,0 | 1,6 | _1,6_ |

## Armures

| Jeu complet | {% include icon.html id="bronze_chestplate" s=2 %} Bronze | {% include icon.html id="duralumin_chestplate" s=2 %} Duralumin | {% include icon.html id="steel_chestplate" s=2 %} Acier | _Fer_ | _Diamant_ |
|---|---:|---:|---:|---:|---:|
| Protection | 15 | 15 | 18 | _15_ | _20_ |
| Durabilité | 1 100 | 715 | 1 485 | _825_ | _1 815_ |
| Robustesse (par pièce) | 0 | 0 | 1 | _0_ | _2_ |
| Enchantabilité | 10 | 22 | 9 | _9_ | _10_ |

Le détail de chaque pièce est sur la fiche de l'alliage.

## Les traits d'alliage

Chaque alliage a son **caractère**, sans enchantement : un petit effet sur les pièces d'armure, un bonus quand on porte les quatre pièces, et un effet sur ses outils et ses armes. Les infobulles l'annoncent : le bonus de l'ensemble s'affiche en vert quand on porte les quatre pièces.

### {% include icon.html id="bronze_ingot" s=2 %} Bronze

{% include traits.html alloy="bronze" %}

### {% include icon.html id="duralumin_ingot" s=2 %} Duralumin

{% include traits.html alloy="duralumin" %}

### {% include icon.html id="steel_ingot" s=2 %} Acier

{% include traits.html alloy="steel" %}

## Les lingots

Sept lingots : les trois alliages et les quatre métaux du monde. Les autres mods les reconnaissent grâce à leurs tags (`forge:ingots/<métal>`).

{% include gallery.html ids="bronze_ingot,duralumin_ingot,steel_ingot,tin_ingot,aluminum_ingot,magnesium_ingot,manganese_ingot" %}

## Le charbon purifié

Du charbon cuit au four ou au haut fourneau (pas le charbon de bois). Il apporte le carbone de l'acier : 4 lingots de fer et 1 charbon purifié font 1 lingot d'acier. Il ne brûle pas. La recette est dans [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}#charbon-purifie).

{% include gallery.html ids="purified_coal" %}

## Minerais et objets bruts

Chaque métal du monde a deux minerais (pierre et ardoise des abîmes) et un objet brut. Où les trouver : [les minerais]({{ '/v0.4.0/minerais/' | relative_url }}).

{% include gallery.html ids="tin_ore,deepslate_tin_ore,raw_tin,aluminum_ore,deepslate_aluminum_ore,raw_aluminum,magnesium_ore,deepslate_magnesium_ore,raw_magnesium,manganese_ore,deepslate_manganese_ore,raw_manganese" s=3 %}

## Les blocs

Onze blocs de rangement, qui se fabriquent dans les deux sens : un **bloc de neuf lingots** pour chacun des sept métaux, et un **bloc de neuf objets bruts** pour l'étain, l'aluminium, le magnésium et le manganèse. Ils ont la dureté d'un bloc de fer et se minent à la pioche (en pierre, ou en fer pour le magnésium et le manganèse). Recettes : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}#blocs).

{% include gallery.html ids="bronze_block,duralumin_block,steel_block,tin_block,aluminum_block,magnesium_block,manganese_block,raw_tin_block,raw_aluminum_block,raw_magnesium_block,raw_manganese_block" %}

## La fonderie

Un bloc, la **fonderie d'alliages**, avec son livre de recettes : [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}).

{% include gallery.html ids="alloy_foundry" %}
