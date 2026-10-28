---
layout: default
title: Duralumin
permalink: /v0.4.0/duralumin/
alt: /en/v0.4.0/duralumin/
lang: fr
version: v0.4.0
key: duralumin
nav: alloys
---

# Le duralumin

Le duralumin est l'alliage des **mineurs pressés** : léger, il mine bien plus vite que les autres, et il prend les meilleurs enchantements. Dans la réalité, c'est un alliage de l'aviation : surtout de l'aluminium, avec un peu de cuivre, de magnésium et de manganèse.

{% include alloy-hero.html alloy="duralumin" %}

> **En bref :** 5 aluminium + 1 cuivre + 1 magnésium + 1 manganèse donnent 8 lingots de duralumin, avec un combustible de chaleur 2 (au moins un charbon). Ses outils minent la roche et les minerais **deux fois plus vite**. Retour à [tous les alliages]({{ '/v0.4.0/alliages/' | relative_url }}).

## Le fabriquer

Il se fond dans [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}) avec un combustible de **chaleur 2** (voir [la chaleur]({{ '/v0.4.0/fonderie/' | relative_url }}#la-chaleur)). Il faut donc trouver les quatre métaux : [l'aluminium et l'étain avec une pioche en pierre, le magnésium et le manganèse avec une pioche de niveau fer]({{ '/v0.4.0/minerais/' | relative_url }}). Le cuivre vient de Minecraft. Toutes les recettes : [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}).

<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "duralumin" | first %}{% include foundry.html r=r %}
</div>

## Son caractère

{% include traits.html alloy="duralumin" %}

## Outils et armes

Tous minent au niveau du fer. L'épée frappe plus souvent (2,0 fois par seconde) mais un peu moins fort par coup : par seconde, elle fait un peu plus de dégâts que le fer.

{% include stats-tools.html alloy="duralumin" %}

## Armure

Elle protège comme l'armure de fer, mais dure un peu moins, et c'est la plus facile à bien enchanter. On la répare à l'enclume avec un lingot de duralumin.

{% include armor-row.html alloy="duralumin" %}

## À essayer ensuite

Le duralumin permet de creuser plus vite pour trouver ce qu'il faut à l'[acier]({{ '/v0.4.0/acier/' | relative_url }}).
