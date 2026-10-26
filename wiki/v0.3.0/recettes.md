---
layout: default
title: Recettes
permalink: /v0.3.0/recettes/
alt: /en/v0.3.0/recipes/
lang: fr
version: v0.3.0
key: recipes
---

# Les recettes

Toutes les recettes du mod, comme à l'écran du jeu. Les recettes d'établi se débloquent dès qu'on possède un lingot du métal. Les objets des cases alternent quand plusieurs conviennent.

## La fonderie {#fonderie}

À l'établi. Cette recette se débloque dès qu'on possède des briques de pierre. Fonctionnement : [la fonderie]({{ '/v0.3.0/fonderie/' | relative_url }}).

<div class="cards">
{% for r in site.data.recipes.bench %}{% include craft.html r=r %}{% endfor %}
</div>

## Fondre les minerais

L'objet brut et les deux blocs de minerai (pierre et abîmes) se fondent en lingot : **10 s au four, 5 s au haut fourneau.**

<div class="cards cards-small">
{% for r in site.data.recipes.smelt %}{% include smelt.html r=r %}{% endfor %}
</div>

## Les alliages {#alliages}

Dans [la fonderie]({{ '/v0.3.0/fonderie/' | relative_url }}). Chaque recette dure **10 secondes** et demande un **niveau de chaleur** : voir [la chaleur]({{ '/v0.3.0/fonderie/' | relative_url }}#la-chaleur). La case du bas est le combustible.

<div class="cards">
{% for r in site.data.recipes.foundry %}{% include foundry.html r=r %}{% endfor %}
</div>

Les lingots sont reconnus par leur tag (`forge:ingots/<métal>`) : un lingot de cuivre, d'étain, de fer ou d'un autre mod qui porte le même tag convient. Fiches : [bronze]({{ '/v0.3.0/bronze/' | relative_url }}), [duralumin]({{ '/v0.3.0/duralumin/' | relative_url }}), [acier]({{ '/v0.3.0/acier/' | relative_url }}).

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

## Réparer

À l'enclume, avec un lingot de l'alliage. Les outils et les armures se réparent aussi en les combinant entre eux.
