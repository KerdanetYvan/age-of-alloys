---
layout: default
title: Minerais
permalink: /v0.3.0/minerais/
alt: /en/v0.3.0/ores/
lang: fr
version: v0.3.0
key: ores
---

# Les minerais

Quatre métaux se trouvent dans le monde : l'**étain**, l'**aluminium**, le **magnésium** et le **manganèse**. Chacun existe en minerai de **pierre** et en minerai d'**ardoise des abîmes**, et apparaît dans **tous les biomes du monde normal**.

> **Important :** les minerais ne se génèrent que dans les chunks créés avec le mod. Créez un nouveau monde, ou allez dans une zone jamais explorée.

## Où creuser

La hauteur (Y) donne la zone ; au milieu de la zone, les filons sont plus nombreux, vers les bords ils sont plus rares.

| Minerai | Hauteur (Y) | Filons par chunk | Taille d'un filon | Pioche minimale |
|---|---:|---:|---:|---|
| Étain | -24 à 80 | 16 | 9 | pierre |
| Aluminium | 0 à 128 | 14 | 9 | pierre |
| Magnésium | -48 à 48 | 4 | 6 | fer |
| Manganèse | -64 à 16 | 6 | 6 | fer |

- **Aluminium** : le plus courant, de la surface aux grottes.
- **Étain** : presque aussi fréquent que le cuivre.
- **Magnésium** : rare, autour de Y = 0.
- **Manganèse** : rare et profond, surtout sous Y = 0.

« Pioche de niveau fer » veut dire fer, bronze, duralumin ou acier : tous minent au niveau du fer.

## Ce que lâche un minerai

Chaque minerai lâche son **objet brut** (étain brut, aluminium brut, etc.), avec plus d'objets en Fortune. Avec Silk Touch, on récupère le **bloc** lui-même.

## Fondre en lingot

L'objet brut et les deux blocs de minerai se fondent en lingot :

| Où | Durée |
|---|---:|
| Four | 10 s |
| Haut fourneau | 5 s |

Les lingots servent ensuite à fabriquer les alliages dans [la fonderie]({{ '/v0.3.0/fonderie/' | relative_url }}).
