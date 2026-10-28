---
layout: default
title: Bronze
permalink: /v0.4.0/bronze/
alt: /en/v0.4.0/bronze/
lang: fr
version: v0.4.0
key: bronze
nav: alloys
---

# Le bronze

Le bronze est l'alliage **robuste de milieu de partie** : il a la puissance du fer mais dure plus longtemps. Dans la réalité, c'est du cuivre et de l'étain, déjà utilisés à l'âge du bronze.

{% include alloy-hero.html alloy="bronze" %}

> **En bref :** 3 lingots de cuivre + 1 lingot d'étain donnent 4 lingots de bronze, avec n'importe quel combustible (chaleur 1). Le plus simple des trois alliages, et le premier à faire. Retour à [tous les alliages]({{ '/v0.4.0/alliages/' | relative_url }}).

## Le fabriquer

Il se fond dans [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}), avec n'importe quel combustible (voir [la chaleur]({{ '/v0.4.0/fonderie/' | relative_url }}#la-chaleur)). Le cuivre vient de Minecraft, l'étain se trouve dans [les minerais]({{ '/v0.4.0/minerais/' | relative_url }}). Toutes les recettes (outils, armes, armures, blocs) : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "bronze" | first %}{% include foundry.html r=r %}
</div>

## Son caractère

{% include traits.html alloy="bronze" %}

## Outils et armes

Tous minent au niveau du fer. Formes et quantités : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

{% include stats-tools.html alloy="bronze" %}

## Armure

Elle protège comme l'armure de fer, mais dure plus longtemps. On la répare à l'enclume avec un lingot de bronze.

{% include armor-row.html alloy="bronze" %}

## À essayer ensuite

Avec du bronze, vous pouvez chercher du magnésium et du manganèse (une pioche de niveau fer, donc en bronze, suffit) pour faire du [duralumin]({{ '/v0.4.0/duralumin/' | relative_url }}).
