# La création des alliages

> Document de conception de l'étape 2. Il fixe comment on fabrique les alliages en jeu : le mécanisme, les recettes, la progression, et ce qu'il faut apprendre pour l'implémenter. Toutes les valeurs sont **provisoires** et à équilibrer en jeu. Les alliages eux-mêmes (noms, statistiques, couleurs) sont décrits dans [starter-alloys.md](starter-alloys.md).

## 1. Principes et mécanisme retenu

**Le mécanisme est un bloc dédié : la fonderie.** On y place les métaux et un combustible, et elle fond les lingots d'alliage. C'est le choix le plus riche pour le jeu, à la hauteur de l'ambition du mod : mélanger des métaux se voit, se prépare et se mérite, au lieu de tenir dans une recette d'établi.

**Options écartées**, et pourquoi :

| Option | Pourquoi elle est écartée |
|---|---|
| Recette d'établi seule | Trop pauvre : aucune machine, aucun combustible, aucune progression visible. Elle ne rend pas la fonte d'un alliage différente de la fabrication d'un outil. |
| Four ou haut fourneau de Minecraft | Une recette de four n'accepte **qu'un seul** ingrédient : impossible de mélanger deux métaux. |
| Recette d'établi d'abord, bloc ensuite | Deux systèmes à faire, dont le premier serait jeté. On préfère faire le bon directement, en petits morceaux (voir la section 7). |

**Conséquence assumée.** Le mod ne sera jouable en survie qu'après la fonderie **et** la génération des matières premières (étape 3). C'est plus tard qu'avec des recettes d'établi, mais sans système jetable.

## 2. La fonderie

- **Identifiant** : `ageofalloys:alloy_foundry`. Nom français « Fonderie d'alliages », nom anglais « Alloy Foundry ».
- **Emplacements** : 4 d'entrée (le duralumin a quatre composants), 1 de combustible, 1 de sortie.
- **Interface** : du même genre que le four : une flamme qui montre le combustible restant et une barre de progression de la fonte.
- **Fonctionnement** : quand les entrées correspondent à une recette, que le combustible est assez chaud (section 4) et qu'il y a de la place en sortie, la fonderie brûle son combustible et avance la barre. Au bout du temps de fonte, elle consomme les entrées et produit les lingots.
- **Fabrication du bloc** : une recette d'établi en forme, qui se lit comme un creuset (valeurs à équilibrer en jouant) :

  ```
  X S X      X = brique de pierre
  X   X      S = seau (vide)
  P P P      P = pierre lisse
  ```
  4 briques, 3 pierres lisses et 1 seau (3 lingots de fer) donnent 1 fonderie. La recette se débloque dès qu'on possède des briques de pierre.
- **Ingrédients par tags** : les entrées sont des **tags** (`forge:ingots/<métal>`), comme pour les outils (#15 et #80). Les lingots d'un autre mod qui rejoignent le même tag sont acceptés, ce qui prépare l'étape 4.

## 3. Recettes et ratios

**Règle de conservation du métal** : le nombre de lingots obtenus égale le nombre de lingots de métal consommés. Le carbone de l'acier est le charbon, qui s'incorpore à l'alliage : il n'est pas compté comme un métal.

| Alliage | Entrées | Résultat | Remarque |
|---|---|---|---|
| Bronze | 3 cuivre + 1 étain | 4 lingots | La proportion déjà prévue dans [starter-alloys.md](starter-alloys.md) |
| Duralumin | 5 aluminium + 1 cuivre + 1 magnésium + 1 manganèse | 8 lingots | Les quatre composants sont requis. L'aluminium domine ; les proportions réelles (94 / 4 / 1 / 0,5 %) sont simplifiées pour que les petites quantités restent jouables |
| Acier | 4 fer + 1 charbon | 4 lingots | Le charbon est le tag `ageofalloys:coal` (le charbon seul, pas le charbon de bois) |

- **Temps de fonte** : 200 ticks (10 secondes) par lot au départ.
- Une recette consomme un lot entier d'un coup : les entrées doivent donc contenir au moins les quantités indiquées (la fonderie travaille par lots).
- Le cuivre est celui de Minecraft (`forge:ingots/copper`, un lingot de cuivre vanilla).

## 4. Chaleur et progression

**Ordre de progression** : bronze, puis duralumin, puis acier.

**Le problème à résoudre.** Les ingrédients de l'acier (fer et charbon) sont les plus faciles à trouver, alors que l'acier est le meilleur matériau de la première version. Sans autre frein, on pourrait faire de l'acier avant le bronze. Les métaux rares, eux, freinent déjà le duralumin (aluminium, magnésium et manganèse sont à chercher dans le monde, étape 3).

**Piste retenue : un niveau de chaleur.**
- Chaque recette demande un niveau de chaleur de 1, 2 ou 3.
- Le niveau d'un combustible dépend de sa durée de combustion (la valeur que le jeu connaît déjà pour le four) :

| Niveau | Seuil provisoire | Combustibles (exemples) | Alliages |
|---|---|---|---|
| 1 | n'importe quel combustible | planches, bâtons | Bronze |
| 2 | au moins 1 600 ticks | charbon, charbon de bois, bâton de blaze | Duralumin |
| 3 | au moins 10 000 ticks | bloc de charbon, seau de lave | Acier |

- Un combustible d'un niveau supérieur convient aussi aux recettes d'un niveau inférieur.

**Risque.** Le niveau de chaleur est la partie la plus incertaine de ce document : il ajoute une règle à implémenter et à expliquer au joueur. Il est isolé dans le ticket des recettes avancées (section 7, ticket 5) : s'il complique trop, on le retire sans toucher à la fonderie ni au bronze. Deux points à régler à l'équilibrage : le seau de lave laisse un seau vide à rendre, et la valeur exacte des seuils.

**Décisions prises à l'implémentation (#89).**
- Le niveau du feu en cours se lit dans la durée du combustible qui brûle (`burnDuration`, déjà sauvegardée) : aucun champ de plus.
- La règle s'applique à deux endroits : au **rallumage**, un combustible trop froid pour la recette n'est **pas consommé** ; pendant la **fonte**, la progression n'avance que si le feu en cours est assez chaud (sinon elle redescend, comme sans recette).
- Le seau de lave laisse un **seau vide** dans l'emplacement du combustible, comme un four ; un entonnoir sur un côté peut le retirer.
- Aucun retour n'est donné au joueur quand un combustible est trop froid : à surveiller en jouant (info-bulle ou succès, hors de ce ticket).

## 5. Format de la recette

La fonderie a son propre **type de recette**, `ageofalloys:alloying`, parce qu'aucun type de Minecraft n'accepte plusieurs ingrédients en quantités. Exemple complet (le bronze) :

```json
{
  "type": "ageofalloys:alloying",
  "ingredients": [
    { "tag": "forge:ingots/copper", "count": 3 },
    { "tag": "forge:ingots/tin", "count": 1 }
  ],
  "result": { "item": "ageofalloys:bronze_ingot", "count": 4 },
  "heat": 1,
  "time": 200
}
```

- `ingredients` : jusqu'à 4 entrées, chacune un tag et une quantité, placées dans n'importe quel emplacement d'entrée.
- `result` : l'objet obtenu et sa quantité.
- `heat` : le niveau de chaleur requis (section 4).
- `time` : la durée en ticks (section 3).
- Les recettes sont **générées par datagen**, avec un générateur de recettes à nous.

## 6. Notions à apprendre

C'est le prix du choix de la fonderie : une dizaine de notions, toutes dans [NOTIONS.md](../NOTIONS.md), section 6 « Blocs et block entities » et section 7 « Recettes et mécaniques d'alliage ».

| Notion | Où |
|---|---|
| `Block`, `BlockState`, `BlockItem` | section 6 |
| Propriétés d'un bloc (dureté, son, outil requis) | section 6 |
| Table de butin du bloc (ce qu'il lâche) | section 6 |
| *Block entity* : un bloc qui porte des données et une logique | section 6 |
| *Ticking* : de la logique à chaque tick | section 6 |
| Inventaires : `Container`, emplacements, *capabilities* d'objets | section 7 |
| Menus et écrans : l'interface d'un bloc | section 7 |
| Synchronisation client et serveur : la barre de progression et la flamme passent par les **données du menu**, sans paquet réseau personnalisé | section 7 |
| Types et sérialiseurs de recette personnalisés | section 7 |
| Concevoir un système d'alliages : ratios, chaleur, règles | section 7 |

Chaque ticket du découpage (section 7) ne s'occupe que de quelques-unes de ces notions, avec une leçon avant le code, comme pour les tags et les recettes.

## 7. Découpage proposé

Un ticket, une branche, une PR chacun. L'ordre compte.

| # | Ticket | Contenu | Notions |
|---|---|---|---|
| 1 | Le bloc seul | Bloc, objet, état, modèle, texture, nom, table de butin. Sans logique. | `Block`, propriétés, table de butin |
| 2 | La logique | *Block entity*, emplacements, combustible, *ticking*. Sans recettes. | *Block entity*, *ticking*, inventaires |
| 3 | L'interface | Menu et écran, flamme et barre de progression. | Menus et écrans, données du menu |
| 4 | Le type de recette « alliage » | Type, sérialiseur, générateur de recettes. Le bronze est la première recette. | Types et sérialiseurs de recette |
| 5 | Les recettes avancées | Duralumin, acier et niveau de chaleur. | Système d'alliages |
| 6 | La recette de la fonderie | La recette d'établi du bloc lui-même. | (aucune nouvelle) |

**Dépendances** : 1, puis 2, puis 3. Le 4 vient après le 2, le 5 après le 4, le 6 après le 1.

## 8. Hors périmètre et à décider plus tard

- Les **valeurs exactes d'équilibrage** (temps de fonte, seuils de chaleur, recette du bloc) : à régler en jouant.
- L'**affichage des recettes dans JEI** : plus tard, avec les autres intégrations (étape 4).
- L'**intégration avec Create** (ses propres recettes de mélange) : étape 4.
- La **génération des matières premières** dans le monde : étape 3. Elle n'est pas dans ce document.
