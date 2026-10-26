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

La fonderie est le bloc qui **fond plusieurs lingots en un alliage**. On y met les ingrédients et un combustible ; plus l'alliage est avancé, plus le combustible doit être chaud. Sa recette d'établi et celles des alliages sont dans [les recettes]({{ '/v0.3.0/recettes/' | relative_url }}).

## Comment s'en servir

- Elle se pose face au joueur et se mine à la pioche (pierre ou mieux) ; elle lâche son contenu quand on la casse.
- Un clic droit ouvre son interface : **4 emplacements d'entrée**, **1 combustible** (il n'accepte que ce qui brûle) et **1 sortie** (on ne peut rien y mettre).
- Majuscule + clic déplace rapidement les objets. Les entonnoirs peuvent la remplir et la vider, par le dessus, les côtés et le dessous.
- Elle ne brûle du combustible que quand elle a quelque chose à fondre. Elle s'allume et éclaire (niveau 13). La flèche montre la progression de la fonte, la flamme ce qu'il reste de combustible.

## La chaleur {#la-chaleur}

Chaque recette demande un **niveau de chaleur**. Un combustible plus chaud convient aussi aux recettes plus froides. La chaleur se lit sur la flamme de la fonderie : orange, orange-rouge ou bleue selon le niveau.

| Niveau | Il faut | Exemples |
|---|---|---|
| 1 | n'importe quel combustible | planches, bâtons, bûches... |
| 2 | un combustible qui brûle au moins **1 600 ticks** | charbon, charbon de bois, bâton de blaze |
| 3 | un combustible qui brûle au moins **10 000 ticks** | bloc de charbon, seau de lave |

Un combustible trop froid pour la recette **n'est pas consommé**. Un seau de lave laisse un seau vide.
