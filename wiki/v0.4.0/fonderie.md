---
layout: default
title: Fonderie
permalink: /v0.4.0/fonderie/
alt: /en/v0.4.0/foundry/
lang: fr
version: v0.4.0
key: foundry
---

# La fonderie d'alliages

La fonderie est le bloc qui **fond plusieurs lingots en un alliage**. On y met les ingrédients et un combustible ; plus l'alliage est avancé, plus le combustible doit être chaud. Sa recette d'établi et celles des alliages sont dans [les recettes]({{ '/v0.4.0/recettes/' | relative_url }}) ; les alliages eux-mêmes sont présentés dans [la page Alliages]({{ '/v0.4.0/alliages/' | relative_url }}).

## Comment s'en servir

- Elle se pose face au joueur et se mine à la pioche (pierre ou mieux) ; elle lâche son contenu quand on la casse.
- Un clic droit ouvre son interface : **4 emplacements d'entrée**, **1 combustible** (il n'accepte que ce qui brûle) et **1 sortie** (on ne peut rien y mettre).
- Majuscule + clic déplace rapidement les objets. Les entonnoirs peuvent la remplir et la vider, par le dessus, les côtés et le dessous.
- Elle ne brûle du combustible que quand elle a quelque chose à fondre. Elle s'allume et éclaire (niveau 13). La flèche montre la progression de la fonte, la flamme ce qu'il reste de combustible.
- **Elle ne fond que ce que la recette demande.** Un objet qui ne fait pas partie de la recette, posé dans les entrées, bloque la fonte tant qu'on ne l'a pas retiré : du minerai de fer posé avec l'étain et le cuivre empêche le bronze.

## Le livre de recettes {#le-livre-de-recettes}

Un bouton à gauche de l'interface, sous les entrées, ouvre le **livre de recettes** à côté de la fenêtre, comme celui du four.

- Il montre les recettes **débloquées**. Une recette se débloque dès qu'on possède l'un de ses ingrédients : un lingot de cuivre ou d'étain pour le bronze, par exemple.
- En survolant une recette, on voit ses ingrédients avec leurs quantités, la **chaleur** requise et la **durée de fonte**.
- Il sert à **consulter** : cliquer sur une recette ne place rien dans les emplacements, c'est à vous de remplir la fonderie.

## La chaleur {#la-chaleur}

Chaque recette demande un **niveau de chaleur**. Un combustible plus chaud convient aussi aux recettes plus froides. La chaleur se lit sur la flamme de la fonderie : orange, orange-rouge ou bleue selon le niveau.

| Niveau | Il faut | Exemples |
|---|---|---|
| 1 | n'importe quel combustible | {% include icon.html id="stick" s=2 %} planches, bâtons, bûches... |
| 2 | un combustible qui brûle au moins **1 600 ticks** | {% include icon.html id="coal" s=2 %} {% include icon.html id="charcoal" s=2 %} {% include icon.html id="blaze_rod" s=2 %} charbon, charbon de bois, bâton de blaze |
| 3 | un combustible qui brûle au moins **10 000 ticks** | {% include icon.html id="coal_block" s=2 %} {% include icon.html id="lava_bucket" s=2 %} bloc de charbon, seau de lave |

Un combustible trop froid pour la recette **n'est pas consommé**. Un seau de lave laisse un seau vide.

### Un feu chaud brûle vite

Dans la fonderie, la réserve d'un combustible baisse **plus vite quand il est chaud** : le niveau 1 brûle normalement, le niveau 2 **deux fois plus vite**, le niveau 3 **trois fois plus vite**. Le four ne change pas. Une fonte dure 10 secondes (200 ticks) :

| Combustible | Niveau | Durée dans le four | Durée dans la fonderie | Fontes |
|---|---|---:|---:|---:|
| {% include icon.html id="stick" s=2 %} Planches, bâtons | 1 | inchangée | inchangée | selon l'objet |
| {% include icon.html id="coal" s=2 %} {% include icon.html id="charcoal" s=2 %} Charbon, charbon de bois | 2 | 80 s | 40 s | 4 |
| {% include icon.html id="blaze_rod" s=2 %} Bâton de blaze | 2 | 120 s | 60 s | 6 |
| {% include icon.html id="coal_block" s=2 %} Bloc de charbon | 3 | 800 s | 267 s (4 min 27 s) | 26 |
| {% include icon.html id="lava_bucket" s=2 %} Seau de lave | 3 | 1 000 s | 333 s (5 min 33 s) | 33 |

Un bloc de charbon fait donc 26 fontes : avec l'acier à un lingot par fonte, cela fait 26 lingots d'acier.
