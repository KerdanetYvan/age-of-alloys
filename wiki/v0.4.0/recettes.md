---
layout: default
title: Recettes
permalink: /v0.4.0/recettes/
alt: /en/v0.4.0/recipes/
lang: fr
version: v0.4.0
key: recipes
---

# Les recettes

Toutes les recettes du mod, comme à l'écran du jeu. Les recettes d'établi se débloquent dès qu'on possède un de leurs ingrédients, et celles de la fonderie sont aussi dans son [livre de recettes]({{ '/v0.4.0/fonderie/' | relative_url }}#le-livre-de-recettes). Les objets des cases alternent quand plusieurs conviennent.

## La fonderie {#fonderie}

À l'établi. Cette recette se débloque dès qu'on possède des briques de pierre, un seau ou de la pierre lisse. Fonctionnement : [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}).

<div class="cards">
{% for r in site.data.recipes.bench %}{% include craft.html r=r %}{% endfor %}
</div>

## Fondre les minerais

L'objet brut et les deux blocs de minerai (pierre et abîmes) se fondent en lingot : **10 s au four, 5 s au haut fourneau.**

<div class="cards cards-small">
{% for r in site.data.recipes.smelt %}{% include smelt.html r=r %}{% endfor %}
</div>

## Le charbon purifié {#charbon-purifie}

Du **charbon** cuit au four (**20 s**) ou au haut fourneau (**10 s**). Le charbon de bois n'en donne pas. Il sert à l'[acier]({{ '/v0.4.0/acier/' | relative_url }}).

<div class="cards cards-small">
{% for r in site.data.recipes.purify %}{% include smelt.html r=r %}{% endfor %}
</div>

## Les alliages {#alliages}

Dans [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}). Chaque recette dure **10 secondes** et demande un **niveau de chaleur** : voir [la chaleur]({{ '/v0.4.0/fonderie/' | relative_url }}#la-chaleur). La case du bas est le combustible. Les trois alliages sont présentés ensemble sur la page [Alliages]({{ '/v0.4.0/alliages/' | relative_url }}).

<div class="cards">
{% for r in site.data.recipes.foundry %}{% include foundry.html r=r %}{% endfor %}
</div>

Les lingots sont reconnus par leur tag (`forge:ingots/<métal>`) : un lingot de cuivre, d'étain, de fer ou d'un autre mod qui porte le même tag convient. Fiches : [bronze]({{ '/v0.4.0/bronze/' | relative_url }}), [duralumin]({{ '/v0.4.0/duralumin/' | relative_url }}), [acier]({{ '/v0.4.0/acier/' | relative_url }}).

## Outils et armes

À l'établi. Les formes sont celles de Minecraft : un outil de fer se fabrique de la même manière. Les lingots et le résultat alternent entre bronze, duralumin et acier : **la forme est la même pour les trois**.

<div class="cards">
{% for r in site.data.recipes.tools %}{% include craft.html r=r %}{% endfor %}
</div>

## Armures

À l'établi, même principe.

<div class="cards">
{% for r in site.data.recipes.armor %}{% include craft.html r=r %}{% endfor %}
</div>

## Les blocs {#blocs}

À l'établi : **neuf lingots** (ou **neuf objets bruts**) remplissent la grille et font un bloc. Dans l'autre sens, **un bloc seul** dans la grille rend ses neuf objets.

<div class="cards">
{% for b in site.data.recipes.blocks %}{% include block.html b=b %}{% endfor %}
</div>

## Réparer

À l'enclume, avec un lingot de l'alliage. Les outils et les armures se réparent aussi en les combinant entre eux.
