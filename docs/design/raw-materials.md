# La génération des matières premières

> Document de conception de l'étape 3. Il fixe comment les quatre métaux bruts (étain, aluminium, magnésium, manganèse) apparaissent dans le monde : leur forme, leur minage, leur fonte et leur génération. Toutes les valeurs sont **provisoires** et à équilibrer en jeu (ticket #113). Les alliages et leur création sont décrits dans [starter-alloys.md](starter-alloys.md) et [alloy-creation.md](alloy-creation.md).

## 1. Principes

**Un minerai se mérite sans bloquer.** Les recettes de la fonderie ([alloy-creation.md](alloy-creation.md)) imposent déjà la progression : bronze, puis duralumin, puis acier. La rareté des minerais doit la suivre sans jamais enfermer le joueur : on trouve toujours de quoi avancer, mais le duralumin demande d'avoir exploré.

**Ce qui est de Minecraft et ce qui est à nous.** Le cuivre, le fer et le charbon sont ceux du jeu et ne changent pas. Seuls l'étain, l'aluminium, le magnésium et le manganèse sont ajoutés.

**Les mêmes habitudes que les minerais de Minecraft.** Un minerai lâche un **objet brut** qu'on fond, il a une version dans l'ardoise des profondeurs, Fortune augmente son butin et Silk Touch rend le bloc. Un joueur de Minecraft n'a rien à réapprendre.

**Choix arrêtés** (avec l'utilisateur) :

| Question | Choix |
|---|---|
| Que lâche un minerai ? | Un objet brut à fondre, comme le fer et le cuivre |
| Version dans les profondeurs ? | Oui, pour chaque minerai (8 blocs au total) |
| Quelle pioche ? | Étain et aluminium : pierre. Magnésium et manganèse : fer |
| Où ? | Partout dans le monde normal (l'Overworld), sans règle par biome |

## 2. Les quatre minerais

| Métal | Minerai (pierre) | Minerai (ardoise) | Objet brut | Pioche | Lingot (déjà existant) |
|---|---|---|---|---|---|
| Étain | `tin_ore` | `deepslate_tin_ore` | `raw_tin` | pierre | `tin_ingot` |
| Aluminium | `aluminum_ore` | `deepslate_aluminum_ore` | `raw_aluminum` | pierre | `aluminum_ingot` |
| Magnésium | `magnesium_ore` | `deepslate_magnesium_ore` | `raw_magnesium` | fer | `magnesium_ingot` |
| Manganèse | `manganese_ore` | `deepslate_manganese_ore` | `raw_manganese` | fer | `manganese_ingot` |

Tous les identifiants suivent `ageofalloys:<nom>`, le namespace étant le mod id.

**Butin.** Chaque minerai lâche **1 objet brut**, et Fortune en donne plus, avec la même règle que les minerais vanilla. Avec Silk Touch, il lâche le bloc de minerai. Le minerai ne donne pas d'expérience à la casse, comme le fer et le cuivre : c'est la fonte qui en donne.

**Fonte.** L'objet brut **et** le bloc de minerai se fondent, au four et au haut fourneau, vers le lingot du métal. Le haut fourneau est deux fois plus rapide, comme pour le fer. Le lingot est celui de #10, qui porte déjà son tag `forge:ingots/<métal>`.

**Pas de blocs de stockage** dans cette version : ni bloc de lingots, ni bloc d'objets bruts. C'est volontairement hors périmètre (section 9).

## 3. La génération

Un minerai est généré par un **filon** : un petit amas de blocs, placé plusieurs fois par chunk (16 × 16 blocs) à une hauteur choisie. Pour la hauteur, on utilise un **trapèze** : la probabilité est maximale au milieu de la plage et décroît vers les bords, comme pour le cuivre de Minecraft.

**Repères de Minecraft 1.20.1**, lus dans les fichiers du jeu (`data/minecraft/worldgen/placed_feature/` et `configured_feature/`) :

| Minerai vanilla | Taille d'un filon | Filons par chunk | Hauteur |
|---|---:|---:|---|
| Cuivre | 10 | 16 | trapèze de -16 à 112 |
| Fer (au milieu) | 9 | 10 | trapèze de -24 à 56 |
| Charbon (enterré) | 17 | 20 | trapèze de 0 à 192 |

**Nos valeurs, après un premier réglage en jeu (#113) :**

| Minerai | Pioche | Taille d'un filon | Filons par chunk | Hauteur (trapèze) | Pourquoi |
|---|---|---:|---:|---|---|
| Étain | pierre | 9 | 16 | de -24 à 80 | Un peu moins courant que le cuivre, dont il suit à peu près la plage. Il en faut peu pour le bronze, mais la première version (8 × 12) était trop rare en jeu. |
| Aluminium | pierre | 9 | 14 | de 0 à 128 | La base du duralumin : le plus courant, et assez haut pour se trouver sans creuser. |
| Magnésium | fer | 6 | 4 | de -48 à 48 | Rare, en petits filons : il n'en faut que deux lingots par jeu d'outils. |
| Manganèse | fer | 6 | 6 | de -64 à 16 | Rare et profond, pour que le duralumin vienne après avoir exploré. La première version (4 filons) était trop rare en jeu. |

Le réglage a porté sur **deux minerais** seulement, et sur le nombre de filons surtout : l'étain passe de 96 à 144 blocs par chunk (16 × 9, contre 160 pour le cuivre) et le manganèse de 24 à 36 (6 × 6). L'aluminium et le magnésium, trouvés « bien » en jeu, n'ont pas bougé. Ces valeurs restent révisables jusqu'à la v1.0.0.

Chaque filon est placé à la fois dans la pierre et dans l'ardoise : un même filon remplace la pierre par le minerai de pierre et l'ardoise par le minerai d'ardoise, comme le cuivre de Minecraft, et il n'y a donc **qu'une seule règle par minerai**, pas deux.

## 4. Quantités et progression

Un jeu d'outils complet (pioche 3, hache 3, pelle 1, houe 2, épée 2) coûte **11 lingots**. Avec les recettes de la fonderie, on arrondit au lot supérieur :

| Alliage | Lots | Lingots obtenus | Matières premières |
|---|---:|---:|---|
| Bronze | 3 | 12 | 9 cuivre et 3 étain |
| Duralumin | 2 | 16 | 10 aluminium, 2 cuivre, 2 magnésium et 2 manganèse |
| Acier | 3 | 12 | 12 fer et 3 charbon |

On en tire trois choses :
- l'**acier est déjà faisable en survie** : le fer et le charbon sont ceux de Minecraft, avec un bloc de charbon ou un seau de lave pour la chaleur ;
- le **bronze le deviendra avec l'étain** (#109 et #111), puisque le cuivre est celui de Minecraft ;
- le **duralumin** attend les trois autres minerais (#110 et #112). Il demande beaucoup d'aluminium et très peu de magnésium et de manganèse, d'où les raretés de la section 3.

## 5. Technique de génération

Pour chaque minerai, trois ensembles de données, tous écrits **en JSON par datagen** (comme les recettes et les tags) :
- une ***configured feature*** : la forme d'un filon (sa taille) et ses deux versions, pierre et ardoise, choisies avec les mêmes familles de blocs que Minecraft (`stone_ore_replaceables` et `deepslate_ore_replaceables`) ;
- une ***placed feature*** : combien de filons par chunk, et à quelle hauteur ;
- un ***biome modifier*** : l'ajout de cette *placed feature* aux biomes de l'Overworld, à l'étape de génération des minerais souterrains.

**Conséquence à connaître** : un minerai ajouté ne se génère que dans les **nouveaux chunks**. Dans un monde déjà exploré, les anciens chunks restent sans minerai ; il faut aller explorer plus loin, ou créer un nouveau monde pour tester.

## 6. Minage, outils et niveaux

Chaque minerai se mine avec une pioche (le tag `mineable/pickaxe`). Le niveau requis se règle par les tags de blocs de Minecraft : `needs_stone_tool` pour l'étain et l'aluminium, `needs_iron_tool` pour le magnésium et le manganèse. Vanilla y range déjà le cuivre et le fer à la pierre, l'or et le diamant au fer.

Nos trois alliages minent au **niveau du fer**. Le bronze, le duralumin et l'acier doivent donc extraire le magnésium et le manganèse. **Vérifié en jeu avec #110** : une pioche de fer, de bronze, de duralumin ou d'acier lâche l'objet brut du magnésium et du manganèse, alors qu'une pioche en bois ou en pierre ne lâche rien. Nos alliages ont pourtant leur propre tag de niveau (`needs_bronze_tool`…), mais ils sont rangés au niveau du fer, donc ils ouvrent aussi `needs_iron_tool`.

## 7. Notions à apprendre

Toutes sont dans [NOTIONS.md](../NOTIONS.md), section 8 « Génération du monde ». Chaque ticket ne s'occupe que de quelques-unes, avec une leçon avant le code, comme pour la fonderie.

| Notion | Ticket |
|---|---|
| Pipeline de génération : biomes, relief, *features* | #111 |
| *Configured feature* et *placed feature* | #111 |
| Génération de minerais : taille de filon, nombre d'essais, hauteur, distribution | #111 |
| *Biome modifiers* : ajouter ses minerais aux biomes existants | #111 |
| Mondes existants : un mod ajouté à une sauvegarde | #111 |
| Équilibrage : rareté, profondeur, progression | #113 |

Le minerai lui-même (#109, #110) ne demande aucune notion nouvelle : un bloc, un objet, un butin et une fonte, comme la fonderie et les tags.

## 8. Découpage proposé

Un ticket, une branche, une PR chacun. L'ordre compte.

| # | Ticket | Contenu |
|---|---|---|
| 108 | Concevoir la génération des matières premières | Ce document |
| 109 | Ajouter le minerai d'étain | Les deux blocs, l'objet brut, le butin, la fonte, les tags, les textures, les modèles et les noms. Il pose la technique. |
| 110 | Ajouter les minerais d'aluminium, de magnésium et de manganèse | La même technique, sans notion nouvelle |
| 111 | Générer l'étain dans le monde | Le pipeline de génération, écrit par datagen. Il pose la technique du monde. |
| 112 | Générer l'aluminium, le magnésium et le manganèse dans le monde | La même technique, avec la rareté de chacun |
| 113 | Équilibrer la rareté des minerais en jouant | Régler les valeurs de la section 3 en explorant |

**Dépendances** : 108, puis 109, puis 110 et 111 (dans l'ordre qu'on veut), puis 112 (après les deux), puis 113.

## 9. Hors périmètre et à décider plus tard

- Les **blocs de stockage** (bloc de lingots, bloc d'objets bruts) et leurs recettes.
- Les minerais dans le **Nether** ou l'**End**.
- Des **biomes thématiques** (par exemple l'étain en montagne) : on part sur « partout », on pourra affiner.
- Des **structures** qui contiennent des minerais, et des biomes ou structures propres au mod.
- L'**intégration** avec d'autres mods (étape 4).
- Les **valeurs exactes** de rareté : à régler en jouant (#113).
