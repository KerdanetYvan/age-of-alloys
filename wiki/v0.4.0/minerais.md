---
layout: default
title: Minerais
permalink: /v0.4.0/minerais/
alt: /en/v0.4.0/ores/
lang: fr
version: v0.4.0
key: ores
---

# Les minerais

Quatre métaux se trouvent dans le monde : l'**étain**, l'**aluminium**, le **magnésium** et le **manganèse**. Chacun existe en minerai de **pierre** et en minerai d'**ardoise des abîmes**, et apparaît dans **tous les biomes du monde normal**. Ils servent à faire les [alliages]({{ '/v0.4.0/alliages/' | relative_url }}).

> **Important :** les minerais ne se génèrent que dans les chunks créés avec le mod. Créez un nouveau monde, ou allez dans une zone jamais explorée.

## Où creuser

La hauteur (Y) donne la zone ; au milieu de la zone, les filons sont plus nombreux, vers les bords ils sont plus rares. Sur chaque carte : le minerai de pierre, celui de l'ardoise, l'objet brut qu'il lâche, puis le lingot.

<div class="ore-cards">
{% for o in site.data.ores %}{% include ore-card.html o=o %}{% endfor %}
</div>

« Pioche de niveau fer » veut dire fer, bronze, duralumin ou acier : tous minent au niveau du fer. Les outils en duralumin minent la roche et tous les minerais **deux fois plus vite** (voir [le duralumin]({{ '/v0.4.0/duralumin/' | relative_url }})).

## Ce que lâche un minerai

Chaque minerai lâche son **objet brut** (étain brut, aluminium brut, etc.), avec plus d'objets en Fortune. Avec Silk Touch, on récupère le **bloc** lui-même.

## Fondre en lingot

L'objet brut et les deux blocs de minerai se fondent en lingot : **10 s au four, 5 s au haut fourneau.** Les lingots servent ensuite à fabriquer les alliages dans [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}).

## Les blocs

Neuf objets bruts font un **bloc brut**, et neuf lingots un **bloc de lingots**, pour chacun des quatre métaux. Un bloc se mine à la pioche : en pierre, ou en fer pour le magnésium et le manganèse. Les recettes sont dans [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}#blocs).

{% include gallery.html ids="raw_tin_block,raw_aluminum_block,raw_magnesium_block,raw_manganese_block,tin_block,aluminum_block,magnesium_block,manganese_block" s=3 %}
