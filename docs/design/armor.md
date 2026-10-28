# Les armures

> Document de conception de l'étape 3. Il fixe les statistiques des armures des trois alliages (bronze, duralumin, acier), leurs recettes, ce qu'elles demandent en matières premières et la technique pour les ajouter. Toutes les valeurs sont **provisoires** et à équilibrer en jeu. Les alliages et leurs outils sont décrits dans [starter-alloys.md](starter-alloys.md), leur création dans [alloy-creation.md](alloy-creation.md) et leurs matières premières dans [raw-materials.md](raw-materials.md).

## 1. Principes

**Un créneau par armure.** Comme pour les outils, chaque armure prend sa place sur la courbe de puissance du jeu de base (cuir, mailles, or, fer, diamant, netherite), et **aucune n'invalide une armure de Minecraft**. En particulier, l'acier reste nettement sous le diamant.

**Les rôles suivent ceux des outils** ([starter-alloys.md](starter-alloys.md)) : le bronze est durable, le duralumin est rapide et très enchantable, l'acier est le plus puissant.

**Choix arrêtés** (avec l'utilisateur) :

| Alliage | Rôle de l'armure |
|---|---|
| Bronze | Comme le fer en protection, mais plus durable |
| Duralumin | Comme le fer en protection, un peu moins durable, très enchantable |
| Acier | Proche du diamant, nettement dessous |

## 2. Repères de Minecraft

Les valeurs ci-dessous sont lues dans le code de Minecraft 1.20.1 (`net.minecraft.world.item.ArmorMaterials`). La **protection** est donnée pour les bottes, les jambières, le plastron et le casque, dans cet ordre.

| Matériau | Protection | Total | Multiplicateur | Enchantabilité | Résistance (*toughness*) |
|---|---|---:|---:|---:|---:|
| Cuir | 1, 2, 3, 1 | 7 | 5 | 15 | 0 |
| Mailles | 1, 4, 5, 2 | 12 | 15 | 12 | 0 |
| Fer | 2, 5, 6, 2 | 15 | 15 | 9 | 0 |
| Or | 1, 3, 5, 2 | 11 | 7 | 25 | 0 |
| Diamant | 3, 6, 8, 3 | 20 | 33 | 10 | 2 |
| Netherite | 3, 6, 8, 3 | 20 | 37 | 15 | 3 (et 0,1 de résistance au recul) |

Lecture :
- la **protection** est le nombre de demi-plastrons que la pièce ajoute à la barre d'armure ;
- la **durabilité** d'une pièce est le **multiplicateur** du matériau fois un nombre propre à la pièce : **13** pour les bottes, **15** pour les jambières, **16** pour le plastron et **11** pour le casque. Le fer (×15) donne ainsi 195, 225, 240 et 165, soit 825 pour le jeu complet ;
- la **résistance** (*toughness*) réduit les gros dégâts d'un seul coup ;
- l'**enchantabilité** décide de la qualité des enchantements obtenus à la table.

## 3. Nos valeurs

| Alliage | Protection | Total | Multiplicateur | Durabilité par pièce | Enchantabilité | Résistance |
|---|---|---:|---:|---|---:|---:|
| **Bronze** | 2, 5, 6, 2 | 15 | 20 | 260, 300, 320, 220 | 10 | 0 |
| **Duralumin** | 2, 5, 6, 2 | 15 | 13 | 169, 195, 208, 143 | 22 | 0 |
| **Acier** | 3, 5, 7, 3 | 18 | 27 | 351, 405, 432, 297 | 9 | 1 |

La durabilité par pièce est donnée dans l'ordre bottes, jambières, plastron, casque. Aucune de nos armures n'a de résistance au recul.

**Durabilité d'un jeu complet** : fer 825, **bronze 1 100**, **duralumin 715**, **acier 1 485**, diamant 1 815.

**Pourquoi ces choix :**
- Le **bronze** a la protection du fer (15 points) avec une durabilité de ×20 au lieu de ×15 : l'armure de milieu de partie qui dure, comme ses outils (400 de durabilité contre 250).
- Le **duralumin** a aussi la protection du fer, mais une durabilité de ×13, un peu sous celle du fer (×15) : elle protège bien sur le moment et s'use un peu plus vite, dans l'esprit de ses outils (rapides mais moins durables, 300 contre 250 pour le fer, qui reste sa référence). L'enchantabilité de 22 est la plus haute des trois, comme pour ses outils.
- L'**acier** monte à 18 points (contre 20 pour le diamant), avec une résistance de 1 (le diamant a 2) et une durabilité de ×27 (le diamant a ×33) : un cran sous le diamant sur chaque critère. Son enchantabilité de 9 est la plus basse des trois, comme pour ses outils.

**Objet de réparation** : le lingot de l'alliage (le tag `forge:ingots/<alliage>`, comme pour les outils).

**Son d'équipement** (proposé, à confirmer au ticket du code) : celui du fer pour le bronze, celui des mailles pour le duralumin, celui du diamant pour l'acier.

## 4. Recettes et quantités

Les recettes sont celles de Minecraft, avec le lingot de l'alliage :

| Pièce | Lingots |
|---|---:|
| Casque | 5 |
| Plastron | 8 |
| Jambières | 7 |
| Bottes | 4 |
| **Jeu complet** | **24** |

Avec les recettes de la fonderie ([alloy-creation.md](alloy-creation.md)), 24 lingots demandent :

| Alliage | Lots de la fonderie | Lingots obtenus | Matières premières |
|---|---:|---:|---|
| Bronze | 6 | 24 | 18 cuivre et 6 étain |
| Duralumin | 3 | 24 | 15 aluminium, 3 cuivre, 3 magnésium et 3 manganèse |
| Acier | 24 | 24 | 96 fer et 24 charbon purifié |

À titre de comparaison, un jeu d'outils complet demande 11 lingots.

## 5. Technique

Pour chaque alliage :
- un **matériau d'armure** sur le même modèle que les matériaux d'outils : les statistiques de la section 3 dans le paquet `content` (du Java neutre, sans Forge), et un matériau enregistré avec l'objet de réparation ;
- **quatre pièces** enregistrées comme objets, nommées `ageofalloys:<alliage>_helmet`, `ageofalloys:<alliage>_chestplate`, `ageofalloys:<alliage>_leggings` et `ageofalloys:<alliage>_boots`, avec leurs recettes en forme générées par datagen ;
- **deux calques** portés sur le joueur : `textures/models/armor/<alliage>_layer_1.png` (casque, plastron et bottes) et `<alliage>_layer_2.png` (jambières), obtenus comme les textures des outils en recolorant celles de l'armure de fer de Minecraft avec la palette de l'alliage ;
- les **icônes d'objet** (une par pièce) et leurs modèles.

Le code (le matériau et les pièces) et les textures sont dans des tickets séparés, comme pour les outils.

## 6. Notions à apprendre

Toutes sont dans [NOTIONS.md](../NOTIONS.md), section 4 « Items » (le matériau d'armure), section 5 « Assets visuels » (les calques d'armure) et section 9 « Équilibrage et game design » (la cohérence des chiffres).

| Notion | Ticket |
|---|---|
| Armures : matériau d'armure, emplacements, protection, résistance, résistance au recul | #121 |
| Modèles et calques d'armure : le rendu sur le joueur | #122 |
| Chiffrer durabilité, dégâts, armure de façon cohérente avec le jeu de base | #120 (ce document) |

Les tickets pour les deux autres alliages (#123 et #124) ne demandent aucune notion nouvelle.

## 7. Découpage proposé

Un ticket, une branche, une PR chacun. L'ordre compte.

| # | Ticket | Contenu |
|---|---|---|
| 120 | Concevoir les armures | Ce document |
| 121 | Ajouter l'armure en bronze | Le matériau d'armure, les quatre pièces, les recettes, les noms. Il pose la technique du code. |
| 122 | Créer les textures et modèles de l'armure en bronze | Les quatre icônes et les deux calques. Il pose la technique du rendu. |
| 123 | Ajouter les armures en duralumin et en acier | La même technique que le bronze |
| 124 | Créer les textures et modèles des armures en duralumin et en acier | La même technique que le bronze |

**Dépendances** : 120, puis 121, puis 122 et 123 (dans l'ordre qu'on veut), puis 124 (après les deux).

## 8. Hors périmètre et à décider plus tard

- Les **finitions d'armure** (*armor trims*) de Minecraft, qui se posent sur une armure à la table de forge.
- Les **bonus d'ensemble** (un effet si on porte les quatre pièces) : décrits dans [alloy-traits.md](alloy-traits.md) (#168).
- Des **enchantements** propres au mod.
- Des armures pour d'autres créatures (chevaux, loups).
- L'**intégration** avec d'autres mods (étape 4).
- Les **valeurs exactes** de protection, de durabilité et d'enchantabilité : à régler en jouant.
