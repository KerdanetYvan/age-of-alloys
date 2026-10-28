---
layout: default
title: Comment jouer
permalink: /v0.4.0/comment-jouer/
alt: /en/v0.4.0/how-to-play/
lang: fr
version: v0.4.0
key: howto
---

# Comment jouer

Du premier lingot à l'armure d'acier, étape par étape. Tout se joue en **survie**. Pour installer le mod, voir [l'accueil]({{ '/v0.4.0/' | relative_url }}#installation).

> **Important :** les minerais n'apparaissent que dans les **chunks créés avec le mod**. Créez un nouveau monde, ou allez dans une zone jamais explorée.

## Pas à pas

1. {% include icon.html id="tin_ore" s=3 %} **Trouvez de l'étain et de l'aluminium** dans la pierre (une pioche en pierre suffit), et du **cuivre** de Minecraft. Voir [les minerais]({{ '/v0.4.0/minerais/' | relative_url }}).
2. {% include icon.html id="furnace" s=3 %} **Fondez** les objets bruts en lingots, au four ou au haut fourneau.
3. {% include icon.html id="alloy_foundry" s=3 %} **Fabriquez la fonderie** à l'établi : sa recette est dans [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}#fonderie).
4. {% include icon.html id="bronze_ingot" s=3 %} **Faites du [bronze]({{ '/v0.4.0/bronze/' | relative_url }})** : 3 lingots de cuivre et 1 lingot d'étain, avec n'importe quel combustible.
5. {% include icon.html id="duralumin_ingot" s=3 %} Avec une pioche de niveau fer, **cherchez du magnésium et du manganèse** (plus profond) pour le **[duralumin]({{ '/v0.4.0/duralumin/' | relative_url }})**, avec un combustible de chaleur 2 (un charbon).
6. {% include icon.html id="steel_ingot" s=3 %} Pour l'**[acier]({{ '/v0.4.0/acier/' | relative_url }})**, il faut du fer, du **charbon purifié** (un charbon cuit au four ou au haut fourneau) et un combustible très chaud (bloc de charbon ou seau de lave).
7. {% include icon.html id="steel_chestplate" s=3 %} **Fabriquez** outils, armes et armures avec les lingots : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}). Avec les quatre pièces d'une armure, un bonus se déclenche : voir [les alliages]({{ '/v0.4.0/alliages/' | relative_url }}).
{: .steps}

## Le guide en jeu

Le mod a sa propre page de **succès**, « Age of Alloys » (touche **L** par défaut). Elle apparaît dès que vous ramassez votre **premier métal brut**, et vous guide en neuf étapes. Les textes donnent des indices sans révéler les recettes : elles se trouvent dans le livre de recettes de la fonderie, ou dans [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

<div class="chain">
<span class="chain-step">{% include icon.html id="tin_ore" s=3 %}Prospecteur</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="manganese_ore" s=3 %}Le tour des métaux</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="alloy_foundry" s=3 %}Un creuset</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="bronze_ingot" s=3 %}Premier alliage</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="duralumin_ingot" s=3 %}Plus chaud</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_ingot" s=3 %}Presque du diamant</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="bronze_pickaxe" s=3 %}Outils d'alliage</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_helmet" s=3 %}Sous l'armure</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_block" s=3 %}Le meilleur de chaque métal</span>
</div>

## Bon à savoir

<div class="tip-tiles">
{% capture t %}« Niveau fer » veut dire fer, bronze, duralumin ou acier : tous minent au niveau du fer. Le magnésium et le manganèse en demandent une.{% endcapture %}{% include tile.html icon="iron_ingot" title="Niveau de pioche" text=t %}
{% capture t %}Chaque alliage demande un combustible assez chaud, et plus il est chaud, plus vite la fonderie le brûle. Un combustible trop froid n'est pas consommé. Voir <a href="{{ '/v0.4.0/fonderie/' | relative_url }}#la-chaleur">la chaleur</a>.{% endcapture %}{% include tile.html icon="coal_block" title="La chaleur" text=t %}
{% capture t %}Il s'ouvre à côté de la fonderie et montre les recettes que vous avez débloquées. Voir <a href="{{ '/v0.4.0/fonderie/' | relative_url }}#le-livre-de-recettes">la fonderie</a>.{% endcapture %}{% include tile.html icon="alloy_foundry" title="Le livre de recettes" text=t %}
{% capture t %}Dès qu'on possède un de leurs ingrédients : un lingot du métal pour l'établi, des briques de pierre, un seau ou de la pierre lisse pour la fonderie.{% endcapture %}{% include tile.html icon="stone_bricks" title="Les recettes se débloquent" text=t %}
{% capture t %}Neuf lingots font un bloc, et le bloc rend ses neuf lingots : pratique pour ranger et décorer. Voir <a href="{{ '/v0.4.0/recettes/' | relative_url }}#blocs">les recettes</a>.{% endcapture %}{% include tile.html icon="steel_block" title="Les blocs" text=t %}
{% capture t %}Survolez une armure ou un outil d'alliage pour voir son caractère, et le bonus de l'ensemble en vert quand vous portez les quatre pièces.{% endcapture %}{% include tile.html icon="bronze_chestplate" title="Les infobulles" text=t %}
{% capture t %}Plus d'objets bruts avec Fortune, le bloc de minerai lui-même avec Silk Touch. Tout est dans l'onglet créatif « Age of Alloys ».{% endcapture %}{% include tile.html icon="raw_tin" title="Fortune, Silk Touch et créatif" text=t %}
</div>

## Quel alliage choisir ?

<div class="alloy-cards">
{% include alloy-card.html alloy="bronze" %}
{% include alloy-card.html alloy="duralumin" %}
{% include alloy-card.html alloy="steel" %}
</div>

Les trois sont comparés sur la page [Alliages]({{ '/v0.4.0/alliages/' | relative_url }}), et les chiffres de comparaison avec Minecraft sont dans [les objets]({{ '/v0.4.0/objets/' | relative_url }}).
