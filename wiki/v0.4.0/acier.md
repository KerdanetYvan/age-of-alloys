---
layout: default
title: Acier
permalink: /v0.4.0/acier/
alt: /en/v0.4.0/steel/
lang: fr
version: v0.4.0
key: steel
nav: alloys
---

# L'acier

L'acier est le **meilleur alliage** du mod : un cran sous le diamant sur chaque critère. Dans la réalité, c'est du fer avec un peu de carbone ; ici, le carbone est le **charbon purifié**.

{% include alloy-hero.html alloy="steel" %}

> **En bref :** 4 lingots de fer + 1 charbon purifié donnent 1 lingot d'acier, avec un combustible de chaleur 3 (un bloc de charbon ou un seau de lave). Le plus exigeant des trois alliages. Retour à [tous les alliages]({{ '/v0.4.0/alliages/' | relative_url }}).

## Le fabriquer

Il se fond dans [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}) avec un combustible de **chaleur 3** (voir [la chaleur]({{ '/v0.4.0/fonderie/' | relative_url }}#la-chaleur)). Le **charbon purifié** est du charbon cuit au four (20 s) ou au haut fourneau (10 s), pas du charbon de bois : voir [la recette]({{ '/v0.4.0/recettes/' | relative_url }}#charbon-purifie).

<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "steel" | first %}{% include foundry.html r=r %}
</div>

Un lingot coûte donc 4 fer et 1 charbon purifié : une armure complète en acier demande 24 lingots, soit 96 lingots de fer. Un bloc de charbon donne 26 fontes (26 lingots) dans la fonderie. Toutes les recettes : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

## Son caractère

{% include traits.html alloy="steel" %}

## Outils et armes

Tous minent au niveau du fer. Formes et quantités : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

{% include stats-tools.html alloy="steel" %}

## Armure

Elle protège presque comme le diamant et a de la robustesse. On la répare à l'enclume avec un lingot d'acier.

{% include armor-row.html alloy="steel" %}
