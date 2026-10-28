---
layout: default
title: Alliages
permalink: /v0.4.0/alliages/
alt: /en/v0.4.0/alloys/
lang: fr
version: v0.4.0
key: alloys
---

# Les alliages

Le mod ajoute trois alliages : le **bronze**, le **duralumin** et l'**acier**. Chacun a son rôle, sa recette dans [la fonderie]({{ '/v0.4.0/fonderie/' | relative_url }}) et son **caractère** : un bonus pour l'armure, un autre pour les outils et les armes. Cliquez sur une carte pour ouvrir la fiche de l'alliage, avec tous ses chiffres.

<div class="alloy-cards">
{% include alloy-card.html alloy="bronze" %}
{% include alloy-card.html alloy="duralumin" %}
{% include alloy-card.html alloy="steel" %}
</div>

## Du minerai à l'armure

<div class="chain">
<span class="chain-step">{% include icon.html id="tin_ore" s=4 %}Minerai</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="tin_ingot" s=4 %}Lingot</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="alloy_foundry" s=4 %}Fonderie</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="bronze_ingot" s=4 %}Alliage</span><span class="arrow">&rarr;</span>
<span class="chain-step">{% include icon.html id="steel_pickaxe" s=4 %}Équipement</span>
</div>

On creuse les [minerais]({{ '/v0.4.0/minerais/' | relative_url }}), on fond l'objet brut en lingot au four, on mélange les lingots dans la fonderie avec un combustible assez chaud, puis on fabrique outils, armes et armures à l'établi ([les recettes]({{ '/v0.4.0/recettes/' | relative_url }})). [Comment jouer]({{ '/v0.4.0/comment-jouer/' | relative_url }}) reprend ces étapes une à une.

## Le bronze {#bronze}

**Robuste et simple à obtenir** : la puissance du fer, mais plus de deux fois plus de durabilité. C'est le premier alliage à faire. Il demande du cuivre de Minecraft et de l'[étain]({{ '/v0.4.0/minerais/' | relative_url }}), avec n'importe quel combustible (chaleur 1).

<div class="alloy-section">
<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "bronze" | first %}{% include foundry.html r=r %}
</div>
{% include traits.html alloy="bronze" %}
</div>

[La fiche du bronze]({{ '/v0.4.0/bronze/' | relative_url }})

## Le duralumin {#duralumin}

**Léger, rapide, très enchantable** : l'alliage des mineurs pressés. Il demande de l'aluminium, du cuivre, du magnésium et du manganèse, donc les [quatre métaux]({{ '/v0.4.0/minerais/' | relative_url }}) (le magnésium et le manganèse veulent une pioche de niveau fer), avec au moins un charbon (chaleur 2).

<div class="alloy-section">
<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "duralumin" | first %}{% include foundry.html r=r %}
</div>
{% include traits.html alloy="duralumin" %}
</div>

[La fiche du duralumin]({{ '/v0.4.0/duralumin/' | relative_url }})

## L'acier {#acier}

**Le meilleur alliage, juste sous le diamant**, et le plus exigeant : 4 lingots de fer et 1 [charbon purifié]({{ '/v0.4.0/recettes/' | relative_url }}#charbon-purifie) (du charbon cuit au four) pour **un seul** lingot, avec un bloc de charbon ou un seau de lave (chaleur 3). Une armure complète demande 96 lingots de fer.

<div class="alloy-section">
<div class="cards">
{% assign r = site.data.recipes.foundry | where: "id", "steel" | first %}{% include foundry.html r=r %}
</div>
{% include traits.html alloy="steel" %}
</div>

[La fiche de l'acier]({{ '/v0.4.0/acier/' | relative_url }})

## Les comparer

| | {% include icon.html id="bronze_ingot" s=2 %} Bronze | {% include icon.html id="duralumin_ingot" s=2 %} Duralumin | {% include icon.html id="steel_ingot" s=2 %} Acier |
|---|---|---|---|
| Rôle | durable | rapide, enchantable | puissant |
| Chaleur | 1 | 2 | 3 |
| Lingots par fonte | 4 | 8 | 1 |
| Durabilité des outils | 400 | 300 | 1 000 |
| Vitesse de minage | 6 | 9 | 7,5 |
| Armure (jeu complet) | 15 | 15 | 18 |
| Durabilité de l'armure | 1 100 | 715 | 1 485 |

Pour les repères de Minecraft (fer, diamant) et le détail des pièces, voir [les objets]({{ '/v0.4.0/objets/' | relative_url }}).
