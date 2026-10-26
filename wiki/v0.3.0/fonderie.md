---
layout: default
title: Fonderie
permalink: /v0.3.0/fonderie/
alt: /en/v0.3.0/foundry/
lang: fr
version: v0.3.0
key: foundry
---

# La fonderie d'alliages

La fonderie est le bloc qui **fond plusieurs lingots en un alliage**. On y met les ingrédients et un combustible ; plus l'alliage est avancé, plus le combustible doit être chaud.

## Sa recette (établi)

| | | |
|---|---|---|
| Brique de pierre | Seau | Brique de pierre |
| Brique de pierre | _(vide)_ | Brique de pierre |
| Pierre lisse | Pierre lisse | Pierre lisse |
{: .craft-grid }

Quatre briques de pierre, un seau et trois pierres lisses.

## La chaleur

Chaque recette demande un **niveau de chaleur**. Un combustible plus chaud convient aussi aux recettes plus froides. La chaleur se lit sur la flamme de la fonderie : orange, orange-rouge ou bleue selon le niveau.

| Niveau | Il faut | Exemples |
|---|---|---|
| 1 | n'importe quel combustible | planches, bâtons, bûches... |
| 2 | un combustible qui brûle au moins **1 600 ticks** | charbon, charbon de bois, bâton de blaze |
| 3 | un combustible qui brûle au moins **10 000 ticks** | bloc de charbon, seau de lave |

Un combustible trop froid pour la recette **n'est pas consommé**. Un seau de lave laisse un seau vide.

## Les recettes d'alliages

Chaque recette dure **10 secondes** (200 ticks).

| Alliage | Ingrédients | Résultat | Chaleur |
|---|---|---:|:-:|
| [Bronze]({{ '/v0.3.0/bronze/' | relative_url }}) | 3 lingots de cuivre + 1 lingot d'étain | 4 lingots | 1 |
| [Duralumin]({{ '/v0.3.0/duralumin/' | relative_url }}) | 5 aluminium + 1 cuivre + 1 magnésium + 1 manganèse | 8 lingots | 2 |
| [Acier]({{ '/v0.3.0/acier/' | relative_url }}) | 4 lingots de fer + 1 charbon (ou charbon de bois) | 4 lingots | 3 |

Les lingots sont reconnus par leur tag (`forge:ingots/<métal>`) : un lingot de cuivre, d'étain, de fer ou d'un autre mod qui porte le même tag convient.
