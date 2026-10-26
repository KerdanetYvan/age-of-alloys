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

Toutes les recettes du mod au même endroit : la fonderie, la fonte des minerais, les alliages, puis les outils, les armes et les armures. Les recettes d'établi se débloquent dès qu'on possède un lingot du métal.

## La fonderie {#fonderie}

À l'établi :

| | | |
|---|---|---|
| Brique de pierre | Seau | Brique de pierre |
| Brique de pierre | _(vide)_ | Brique de pierre |
| Pierre lisse | Pierre lisse | Pierre lisse |
{: .craft-grid }

Quatre briques de pierre, un seau et trois pierres lisses. Cette recette se débloque dès qu'on possède des briques de pierre. Fonctionnement : [la fonderie]({{ '/v0.3.0/fonderie/' | relative_url }}).

## Fondre les minerais

L'objet brut et les deux blocs de minerai (pierre et abîmes) se fondent en lingot :

| Où | Durée |
|---|---:|
| Four | 10 s |
| Haut fourneau | 5 s |

## Les alliages {#alliages}

Dans [la fonderie]({{ '/v0.3.0/fonderie/' | relative_url }}). Chaque recette dure **10 secondes** (200 ticks) et demande un **niveau de chaleur** : voir [la chaleur]({{ '/v0.3.0/fonderie/' | relative_url }}#la-chaleur).

| Alliage | Ingrédients | Résultat | Chaleur |
|---|---|---:|:-:|
| [Bronze]({{ '/v0.3.0/bronze/' | relative_url }}) | 3 lingots de cuivre + 1 lingot d'étain | 4 lingots | 1 |
| [Duralumin]({{ '/v0.3.0/duralumin/' | relative_url }}) | 5 aluminium + 1 cuivre + 1 magnésium + 1 manganèse | 8 lingots | 2 |
| [Acier]({{ '/v0.3.0/acier/' | relative_url }}) | 4 lingots de fer + 1 charbon (ou charbon de bois) | 4 lingots | 3 |

Les lingots sont reconnus par leur tag (`forge:ingots/<métal>`) : un lingot de cuivre, d'étain, de fer ou d'un autre mod qui porte le même tag convient.

## Outils et armes

À l'établi, avec des **lingots de l'alliage** (bronze, duralumin ou acier) et des **bâtons**. Les formes sont celles de Minecraft : un outil de fer se fabrique de la même manière, avec le lingot de l'alliage à la place.

| Objet | Forme | Lingots | Bâtons |
|---|---|---:|---:|
| Pioche | 3 lingots en haut, 2 bâtons au centre | 3 | 2 |
| Hache | 2 lingots en haut, 1 lingot et 1 bâton au milieu, 1 bâton en bas | 3 | 2 |
| Pelle | 1 lingot, 2 bâtons dessous | 1 | 2 |
| Houe | 2 lingots en haut, 2 bâtons dessous | 2 | 2 |
| Épée | 2 lingots l'un sur l'autre, 1 bâton dessous | 2 | 1 |

## Armures

À l'établi, avec des lingots de l'alliage.

| Pièce | Forme | Lingots |
|---|---|---:|
| Casque | 5 lingots : une rangée de 3, puis les deux côtés | 5 |
| Plastron | 8 lingots : tout sauf le milieu de la rangée du haut | 8 |
| Jambières | 7 lingots : une rangée de 3, puis deux colonnes | 7 |
| Bottes | 4 lingots : deux colonnes de 2 | 4 |

## Réparer

À l'enclume, avec un lingot de l'alliage. Les outils et les armures se réparent aussi en les combinant entre eux.
