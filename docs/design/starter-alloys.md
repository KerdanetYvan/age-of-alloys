# Les 3 alliages de départ

> Document de conception de l'étape 1. Il fixe une fois pour toutes les noms, les statistiques et les couleurs que suivront les tickets d'implémentation. Toutes les valeurs sont **provisoires** et à équilibrer en jeu.

## 1. Principes

**Progression.** Les alliages sont *étalés* sur la courbe de puissance du jeu de base (bois < pierre < or < fer < diamant < netherite). Chacun a son créneau et **aucun n'invalide un matériau vanilla**. En particulier, aucun n'atteint le niveau de minage du diamant (l'obsidienne reste réservée au diamant).

**Rôles.**

| Alliage | Rôle |
|---|---|
| Duralumin | Matériau **léger et rapide** : mine très vite, mais s'use plus vite que le fer |
| Bronze | Matériau **durable et polyvalent**, légèrement au-dessus du fer |
| Acier | Matériau **le plus puissant** de l'étape 1, entre le fer et le diamant |

Le laiton a été écarté : Create l'apporte déjà, ainsi que le zinc, et on ne veut pas faire doublon.

**Nommage.** Tous les objets suivent `ageofalloys:<alliage>_<objet>` (`ageofalloys:bronze_ingot`, `ageofalloys:steel_sword`). Le namespace est le mod id et ne changera jamais.

**Contenu de l'étape 1.** Par alliage : un lingot, une pioche, une hache, une pelle, une houe et une épée, soit 18 objets. Plus **4 lingots de matières premières** (étain, aluminium, magnésium, manganèse), nécessaires pour tester les recettes de l'étape 2, obtenus en créatif tant que l'étape 3 n'existe pas. Les armures sont conçues plus tard. Les pépites sont ajoutées le jour où une recette en a besoin.

## 2. Fiches d'alliage

### Bronze

- **Réel** : cuivre + étain (environ 88 % / 12 %). Dur, résistant à la corrosion, utilisé dès l'âge du bronze.
- **En jeu** : l'outil robuste de milieu de partie, plus durable que le fer.
- **Objets** : `bronze_ingot`, `bronze_pickaxe`, `bronze_axe`, `bronze_shovel`, `bronze_hoe`, `bronze_sword`.
- **Recette prévue (étape 2, non implémentée ici)** : 3 cuivre + 1 étain, pour 4 lingots.
- **Palette** (sombre à clair) : `#5C3A1B` · `#8A5A2B` · `#CD7F32` · `#E8A55F`.

### Duralumin

- **Réel** : aluminium (environ 94 %) + cuivre (4 %) + magnésium (1 %) + manganèse (0,5 %). Un alliage de l'aviation : léger, mais presque aussi résistant qu'un acier doux.
- **En jeu** : l'alliage des **mineurs pressés** : vitesse de minage supérieure à celle de l'acier, mais durabilité et dégâts faibles. Il casse moins de blocs au total, plus vite.
- **Objets** : `duralumin_ingot`, `duralumin_pickaxe`, `duralumin_axe`, `duralumin_shovel`, `duralumin_hoe`, `duralumin_sword`.
- **Recette prévue (étape 2, non implémentée ici)** : alliage à 4 composants (aluminium, cuivre, magnésium, manganèse). Les proportions seront simplifiées pour rester jouables, à définir à l'étape 2.
- **Palette** (6 teintes, sombre à clair) : `#4A545C` · `#7C868F` · `#9AA3AB` · `#B9C1C8` · `#D5DBE0` · `#EEF2F5`. Argent clair, plus lumineux que l'acier. Les deux teintes sombres servent aux contours : le lingot de fer vanilla en a, sans quoi la texture paraîtrait délavée.

### Acier

- **Réel** : fer + un peu de carbone (de 0,2 % à 2 %). Plus dur et plus résistant que le fer.
- **En jeu** : le meilleur matériau de l'étape 1, à la frontière du diamant, qu'il n'égale pas.
- **Objets** : `steel_ingot`, `steel_pickaxe`, `steel_axe`, `steel_shovel`, `steel_hoe`, `steel_sword`.
- **Recette prévue (étape 2)** : fer + charbon (source de carbone). Ratio à définir.
- **Palette** : `#3B4652` · `#5F6F7F` · `#8A9AA8` · `#BCC7D1`.

## 3. Statistiques des outils

Les lignes « vanilla » sont lues dans le code de Minecraft 1.20.1 (`net.minecraft.world.item.Tiers`).

| Matériau | Durabilité | Vitesse | Bonus d'attaque | Niveau | Enchantabilité |
|---|---:|---:|---:|---:|---:|
| Pierre (vanilla) | 131 | 4 | 1 | 1 | 5 |
| **Duralumin** | 200 | 7,5 | 0,5 | 2 | 15 |
| Fer (vanilla) | 250 | 6 | 2 | 2 | 14 |
| **Bronze** | 400 | 6 | 2 | 2 | 12 |
| **Acier** | 850 | 7 | 2,5 | 2 | 9 |
| Diamant (vanilla) | 1561 | 8 | 3 | 3 | 10 |

Lecture : la **durabilité** est le nombre d'utilisations, la **vitesse** celle de minage, le **niveau** décide des blocs qu'on peut casser (1 = pierre, 2 = fer, 3 = diamant), l'**enchantabilité** la qualité des enchantements proposés (plus c'est haut, mieux c'est).

### Vitesse d'attaque (épées et haches)

La **vitesse d'attaque** n'appartient pas au matériau : elle se règle **objet par objet**. C'est ce qui permet au duralumin d'avoir une épée qui se recharge plus vite sans changer le reste de la gamme. Les valeurs vanilla sont lues dans `net.minecraft.world.item.Items`.

| Objet | Fer (vanilla) | Duralumin (proposé) |
|---|---|---|
| Épée | modificateur −2,4 → 1,6 coup/s, 6 dégâts par coup | −2,0 → **2,0 coups/s**, 4,5 dégâts par coup |
| Hache | −3,1 → 0,9 coup/s, 9 dégâts par coup | −2,8 → **1,2 coup/s**, 7,5 dégâts par coup |
| Pioche, pelle, houe | valeurs vanilla | valeurs vanilla |

Dégâts par coup = base de l'objet + bonus du matériau + 1 (la main). Dégâts par seconde = dégâts par coup × coups par seconde : épée 9,0 contre 9,6 pour le fer, hache 9,0 contre 8,1. Le duralumin frappe **nettement plus souvent et un peu moins fort**.

**Plafond utile : 2,0 coups/s.** Après un coup, la cible reste invulnérable 10 ticks (0,5 s) : un coup plus rapproché n'inflige que l'éventuel surplus de dégâts. Aller au-delà de 2,0 coups/s serait donc inutile contre les créatures. L'épée est réglée sur ce maximum. Le bronze et l'acier gardent les vitesses vanilla, à confirmer au ticket des outils (#11).

**Pas d'enchantement « Tranchant » intégré.** Un Tranchant de base se comporterait comme +1 de dégâts, et il irait à l'encontre du rôle du duralumin (coups faibles). Un enchantement collé à l'objet aurait en plus des défauts : lueur d'enchantement permanente, retrait possible à la meule, interactions avec l'enclume. Les dégâts se règlent donc par les statistiques.

## 4. Identifiants et tags

- Objets : voir les fiches ci-dessus.
- Tags de ressources partagés avec les autres mods, **espace de noms `forge:`** en 1.20.1 : `forge:ingots/bronze`, `forge:ingots/duralumin`, `forge:ingots/steel`.
- **Matières premières** (étape 1) : `ageofalloys:tin_ingot`, `aluminum_ingot`, `magnesium_ingot`, `manganese_ingot`, avec les tags `forge:ingots/tin`, `forge:ingots/aluminum`, `forge:ingots/magnesium`, `forge:ingots/manganese`. Ce sont les noms usuels partagés avec les autres mods (orthographe américaine pour l'aluminium). Les palettes de ces lingots seront fixées au ticket des textures (#13).
- **Autres loaders (préparation)** : NeoForge (1.21+) et Fabric utilisent l'espace `c:` (`c:ingots/bronze`) au lieu de `forge:`. Le chemin (`ingots/bronze`) reste le même, seul l'espace change : à garder en tête pour un futur portage.
- Tags d'outils : les outils rejoignent `minecraft:pickaxes`, `minecraft:axes`, `minecraft:shovels`, `minecraft:hoes`, `minecraft:swords`.
- Les niveaux de minage des tiers personnalisés passent par le système de tiers de Forge : à traiter dans le ticket des outils (#11).

## 5. Hors périmètre

- **Armures** : stats à définir au ticket correspondant (#12).
- **Blocs de minerai**, drops (minerais bruts) et **génération dans le monde** des matières premières : étape 3. Les *lingots* de ces métaux, eux, sont créés à l'étape 1. Le carbone de l'acier vient du charbon vanilla.
- **Blocs de stockage** et **pépites** : quand une recette en a besoin.
- Plusieurs de ces métaux existent déjà dans d'autres mods : l'utilisation de tags communs évitera les doublons à l'étape 4.
- **Machines et mécanique de fonte** : étape 2.
