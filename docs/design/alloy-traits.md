# Les traits d'alliage

> Document de conception (tickets #168 pour l'armure et #178 pour les outils et les armes). Il donne à chaque alliage une identité propre, au-delà de ses chiffres : un ou deux **traits** (un attribut ou un bonus quand on porte les quatre pièces d'armure, un effet de minage ou de combat pour les outils et les armes), sans enchantement collé à l'objet. Toutes les valeurs sont **provisoires** et à équilibrer en jeu. Les alliages et leurs outils sont décrits dans [starter-alloys.md](starter-alloys.md), leurs armures dans [armor.md](armor.md), leur création dans [alloy-creation.md](alloy-creation.md).

## 1. Principes

**Le problème.** Après l'équilibrage (#148), les trois alliages ont des chiffres cohérents, mais ils ne se distinguent que par ces chiffres : durabilité, vitesse, enchantabilité. Un testeur de la v0.3.0 n'avait pas compris l'intérêt du duralumin. Un alliage doit se reconnaître à ce qu'il **fait**, pas seulement à ce qu'il vaut.

**Pas d'enchantement collé à l'objet.** On aurait pu donner à l'armure ou à l'outil un enchantement de base. C'est écarté pour trois raisons : la lueur d'enchantement permanente, le retrait possible à la meule, et les interactions avec l'enclume. Les traits passent par des mécanismes du jeu qui n'ont aucun de ces défauts (section 2).

**Un trait par alliage, lisible.** Chaque trait tient en une phrase et s'affiche dans l'info-bulle de l'armure, de l'outil ou de l'arme. Il suit le rôle de l'alliage déjà posé dans [starter-alloys.md](starter-alloys.md) : le duralumin est léger et rapide, le bronze est durable, l'acier est puissant.

**Un trait ne remplace pas un chiffre.** La protection, la résistance et la durabilité de [armor.md](armor.md), la vitesse et la durabilité des outils de [starter-alloys.md](starter-alloys.md) ne bougent pas : les traits s'y ajoutent.

## 2. Les moyens techniques

| Moyen | Ce que c'est | Retenu ? |
|---|---|---|
| **Attributs de l'objet** | Des modificateurs que l'objet ajoute à celui qui le porte ou le tient (vitesse de déplacement, résistance au recul, portée d'attaque…). Ils s'affichent dans l'info-bulle comme les statistiques normales, sans lueur. | Oui |
| **Bonus de set** | Un effet qui s'active quand on porte les quatre pièces du même alliage. On détecte l'ensemble, puis on applique l'effet par un événement du jeu. | Oui (armure) |
| **Comportements d'outil** | Un effet qui dépend de l'objet tenu : minage plus rapide sur certains blocs, recul plus fort. Il passe par des événements du jeu qui regardent l'objet tenu, sans une classe d'outil par type d'outil. | Oui (section 4) |
| Réparation dans l'inventaire | Un outil qui se répare seul dans l'inventaire. | Reporté (section 10) |
| Enchantement collé | Un enchantement posé sur l'objet à sa création. | Écarté (section 1) |

## 3. Les traits de l'armure

| Alliage | Rôle | Attribut par pièce | Bonus de set (4 pièces) |
|---|---|---|---|
| **Duralumin** | léger, rapide | +3 % de vitesse de déplacement (+12 % le jeu complet) | dégâts de chute −30 % |
| **Bronze** | durable, polyvalent | aucun | chaque pièce regagne 1 de durabilité toutes les 3 minutes |
| **Acier** | puissant, lourd | +0,05 de résistance au recul, −1 % de vitesse (soit 0,2 de recul et −4 % le jeu complet) | dégâts de feu −50 % |

**Pourquoi ces choix :**
- Le **duralumin** est l'alliage de la vitesse : ses outils minent plus vite, son armure fait courir plus vite. La réduction des dégâts de chute rappelle l'aviation (l'alliage vient de là) et donne un vrai avantage aux mineurs pressés qui sautent dans les grottes.
- Le **bronze** est l'alliage qui dure. Il n'a pas d'attribut : son trait est qu'il s'entretient seul, un peu, comme un Réparation d'enchantement mais sans enchantement. La durabilité totale du jeu complet est de 1 100 : réparer 1 point par pièce toutes les 3 minutes (3 600 ticks) fait environ 7 points par pièce pendant un jour de Minecraft (20 minutes), soit 20 par heure de jeu. C'est volontairement lent : l'armure s'entretient, elle ne se répare pas toute seule en une journée, et une armure mal traitée doit toujours finir par casser.
- L'**acier** est lourd et puissant : un peu de résistance au recul (0,05 par pièce, 0,2 au total ; la netherite vanilla donne 0,1 par pièce, soit 0,4), compensée par une légère lenteur. Le bonus de set (le feu fait deux fois moins mal) vient de sa fabrication : l'acier est forgé par le feu le plus féroce (voir le succès « Presque du diamant »).

**Ce que les valeurs veulent dire** : 0,03 de vitesse est un pourcentage de la vitesse de base du joueur (0,1), qui s'additionne d'une pièce à l'autre : 4 pièces donnent +12 %. Les dégâts de chute et de feu sont multipliés (×0,7 et ×0,5) avant l'armure et les enchantements, qui s'y ajoutent ensuite (section 7).

## 4. Les traits des outils et des armes

| Alliage | Outils (pioche, hache, pelle, houe) | Armes (épée, hache) |
|---|---|---|
| **Duralumin** | minage ×2 sur la **roche et les minerais** (pas sur l'ardoise elle-même) | +0,5 de **portée d'attaque** |
| **Bronze** | aucun | aucun |
| **Acier** | minage ×1,25 sur la **pierre et l'ardoise** | +20 % de **recul infligé** et 15 % de chance d'**étourdir** la cible (Lenteur IV, 2,5 s) |

La hache est à la fois un outil et une arme : elle reçoit les deux traits de son alliage.

**Pourquoi ces choix :**
- Le **duralumin** est l'alliage du mineur pressé. Sa vitesse de base (9) est déjà la plus haute ; le bonus (×2) la pousse à 18 sur la roche et sur tous les minerais, là où l'on creuse le plus, donc la pioche en duralumin est faite pour chercher. L'ardoise des abîmes n'est pas accélérée : c'est le petit domaine propre de l'acier. La portée d'attaque (+0,5, pour 3 de base) donne à son épée, qui frappe déjà 2 fois par seconde, un petit avantage de placement, sans toucher à ses dégâts. Elle est à surveiller avec sa cadence.
- Le **bronze** n'a pas de trait d'outil : sa durabilité déjà élevée est son trait, et son armure a déjà la réparation lente. Si on lui en trouve un plus tard, ce sera une décision à part.
- L'**acier** est l'alliage de la force. Sa pioche (7,5 de base) monte à environ 9,4 sur la pierre et l'ardoise, au-dessus du diamant (8) sur ce seul type de bloc, ce qui reste cohérent avec « juste en dessous du diamant » partout ailleurs. Le recul supplémentaire de l'épée et de la hache (+20 %) donne un coup lourd, et l'étourdissement (une chance sur sept environ à chaque coup) le prolonge : la cible est ralentie assez longtemps pour fuir ou enchaîner un coup de plus.

**Ordres de grandeur** : pioche en duralumin 9 → 18 sur la roche et sur un minerai (l'or, le plus rapide du jeu de base, est à 12) ; pioche en acier 7,5 → 9,4 sur la pierre ; épée en duralumin 3 + 0,5 de portée (3,5 blocs, hors Rapidité) ; étourdissement de l'acier : Lenteur IV, soit −60 % de vitesse pendant 50 ticks (2,5 secondes), avec une chance de 15 % par coup.

**Textes des info-bulles** (une ligne par objet concerné) : « Mineur pressé : roche et minerais +100 % » (outils en duralumin), « Tailleur de pierre : pierre et ardoise +25 % » (outils en acier), « Coup lourd : +20 % de recul » et « Coup étourdissant : 15 % de chance de ralentir la cible (2,5 s) » (épée et hache en acier).

## 5. Détail technique prévu

À confirmer au moment du code. Les nombres (valeurs des traits) vont dans `content`, code neutre sans Forge (comme `ArmorStats` ou `ToolStats`), et tout ce qui touche au jeu va dans un paquet propre à Forge (voir [../architecture.md](../architecture.md)).

**Attributs.**
- La sous-classe de l'armure `AlloyArmorItem` (faite en #172) remplace l'`ArmorItem` simple dans `ModItems.armor(...)` et ajoute les modificateurs en plus de ceux du matériau (protection, résistance). Elle vit dans `content` : tout ce qu'elle utilise est vanilla, sans Forge.
- La **résistance au recul de l'acier n'a pas besoin de code** : `ArmorStats` la porte déjà (`knockbackResistance`), passée à 0,05 pour l'acier (#172). La vitesse est un nouveau champ de `ArmorStats`, `movementSpeedBonus`.
- La vitesse est un modificateur de l'attribut de vitesse de déplacement, en pourcentage de la valeur de base. Chaque modificateur demande un **identifiant unique par pièce et par attribut**, sans quoi les pièces s'écraseraient entre elles.

**Détection du set** (faite en #173, classe `AlloySet` dans `content`). Une règle unique, utilisée par les trois effets, et un emplacement ne compte que si la pièce est à sa place (un plastron posé dans l'emplacement du casque par une commande ne compte pas) : l'entité porte quatre pièces dont le matériau est celui du même alliage (on lit les emplacements d'armure). Cette règle s'applique à toute créature qui porte l'armure, pas seulement au joueur, comme la protection vanilla.

**Effets de set** (faits en #174, classe `AlloySetEffects` du paquet `event`, abonnée aux deux côtés pour tourner aussi sur un serveur dédié), par événements du jeu (Forge) :

| Effet | Événement | Ce qu'on fait |
|---|---|---|
| Chute −30 % (duralumin) | l'entité retombe (`LivingFallEvent`) | on multiplie les dégâts de chute par 0,7 |
| Feu −50 % (acier) | l'entité subit un coup (`LivingHurtEvent`) | si la source est du feu (étiquette de dégâts de feu de Minecraft), on divise le montant par 2 |
| Réparation lente (bronze) | une tick du joueur, côté serveur | toutes les 3 600 ticks, chaque pièce de bronze abîmée regagne 1 de durabilité |

- La réparation se compte avec le temps de jeu du joueur (aucune donnée à sauvegarder en plus). Se reconnecter la remet à zéro : à l'échelle de 3 minutes, c'est sans conséquence.
- La liste exacte des sources de dégâts de feu (feu, lave, flammes, sol brûlant) vient de l'étiquette de Minecraft et sera vérifiée en jeu.

**Outils et armes** (#178, à faire), tous par événements, sans une classe d'outil par type d'outil :

| Trait | Moyen | Ce qu'on fait |
|---|---|---|
| Minage ×2 sur la roche et les minerais (duralumin) | l'événement de vitesse de minage du joueur (`PlayerEvent.BreakSpeed`), classe `AlloyToolTraits` du paquet `event` (faite en #180) | si l'objet tenu est un outil en duralumin, si c'est le bon outil pour ce bloc, et si le bloc est dans l'étiquette `forge:ores` (nos minerais y sont déjà) ou dans `BASE_STONE_OVERWORLD` sans être de l'ardoise, on multiplie la vitesse par 2 |
| Minage ×1,25 sur la pierre et l'ardoise (acier) | le même événement (#180) | si l'objet tenu est un outil en acier et si le bloc est dans l'étiquette vanilla `BASE_STONE_OVERWORLD` (pierre, granite, diorite, andésite, tuf, ardoise), on multiplie la vitesse par 1,25 |
| Portée d'attaque +0,5 (duralumin) | l'événement des modificateurs d'attribut d'objet (Forge, `ItemAttributeModifierEvent`), avec l'attribut de portée d'attaque que Forge ajoute (`ForgeMod.ENTITY_REACH`), même classe `AlloyToolTraits` (faite en #181) | on ajoute un modificateur en addition de +0,5, avec un identifiant unique, à l'épée et à la hache en duralumin, dans la main principale. La ligne d'info-bulle est celle que le jeu affiche d'office pour un attribut : pas de ligne bleue pour ce trait |
| Recul +20 % (acier) | à trancher au moment du code (voir ci-dessous) | l'épée et la hache en acier repoussent 20 % plus fort |
| Étourdissement 15 % (acier) | l'événement de coup reçu (`LivingHurtEvent`), qui donne l'attaquant et la cible | si l'attaquant tient une épée ou une hache en acier et si un tirage au sort à 15 % réussit, on ajoute à la cible l'effet Lenteur IV (`MobEffects.MOVEMENT_SLOWDOWN`, amplificateur 3) pendant 50 ticks |

- **Le recul est le trait le plus incertain techniquement.** Deux pistes : l'attribut vanilla de recul d'attaque, ajouté comme les autres modificateurs, mais il n'est peut-être pas porté par le joueur ; ou, sinon, un recul supplémentaire appliqué dans l'événement de coup reçu (`LivingHurtEvent`) quand l'attaquant tient une arme en acier. Le ticket de code tranche en essayant.
- **L'étourdissement est simple techniquement** : il n'y a ni attribut ni piste à trancher, seulement un tirage au sort et un effet de potion du jeu, donc le jeu l'affiche sur la cible. Il se fait côté serveur seulement.
- Les nombres vont dans `content`, dans une classe voisine de `SetBonus` (par exemple `ToolTrait`). Les gestionnaires vont dans le paquet `event`, abonnés aux deux côtés.
- Le minage est calculé côté joueur et côté serveur : la vitesse modifiée doit être la même des deux côtés, ce que l'événement garantit.

## 6. Info-bulles

- Chaque pièce d'armure affiche son trait : l'attribut (ligne ordinaire du jeu) puis, sous les attributs, un titre « Ensemble de duralumin (n/4) » et la ligne du bonus de set (« Dégâts de chute réduits de 30 % »). Le chiffre est calculé à partir des nombres de la classe `SetBonus` (`content`), qui porte aussi les nombres des effets.
- Le bonus de set s'affiche **activé ou grisé** selon ce que porte le joueur. Cela demande de lire l'équipement du joueur côté client : on le fait dans un gestionnaire d'info-bulle du paquet `client` (l'événement d'info-bulle donne le joueur), pas dans l'objet lui-même, pour ne pas faire planter un serveur sans client.
- Les outils et armes concernés affichent une ligne de trait, toujours active (un outil n'a pas de set), en bleu comme les lignes de bonus d'attributs du jeu (section 4 pour les textes). Le chiffre vient de la classe des nombres.
- Les textes sont des clés de traduction, en anglais et en français, comme tous les noms du mod.

## 7. Interactions

- **Protection et enchantements** : les traits d'armure s'ajoutent, ils ne remplacent rien. Le feu −50 % de l'acier se multiplie avec Protection contre le feu (on réduit le montant avant l'armure et les enchantements). Les dégâts de chute −30 % se multiplient avec Chute amortie.
- **Vitesse** : la vitesse de base du joueur est de 0,1. Le jeu complet en duralumin (+12 %) reste modeste devant une potion de Rapidité (+20 % par niveau) et l'acier (−4 %) reste à peine perceptible : l'effet doit être senti sans être pénalisant.
- **Résistance au recul** : l'acier à 0,2 reste loin de la netherite (0,4). Le diamant n'en a pas.
- **Vitesse de minage** : le bonus (×2 pour le duralumin, ×1,25 pour l'acier) multiplie la vitesse déjà calculée, donc il s'ajoute à Efficacité et à Rapidité (qui augmentent la vitesse de base) sans les remplacer. Il ne change pas ce qu'un outil peut casser : le niveau de minage reste celui du fer.
- **Portée d'attaque** : +0,5 sur 3 blocs reste modeste, et ne remplace pas la portée en création (5 blocs).
- **Recul infligé** : il s'additionne à l'enchantement Recul.
- **Étourdissement** : la Lenteur IV s'applique aux créatures comme aux joueurs. Une cible déjà sous Lenteur garde l'effet le plus fort ou le plus long (le jeu ne cumule pas deux effets du même type) ; une créature qui résiste à la lenteur y résiste toujours. Contre les joueurs, −60 % pendant 2,5 secondes reste un fort ralentissement et non une immobilisation, ce qui garde le combat jouable.
- **Autres mods** : les traits s'appuient sur les mécanismes standard (attributs, événements), donc ils cohabitent avec les autres mods.

## 8. Notions à apprendre

| Notion | Ticket de code |
|---|---|
| Attributs et modificateurs : ajouter un attribut à un objet, identifiants uniques, opérations (addition, pourcentage) | attributs de l'armure, portée d'attaque |
| Détecter un ensemble d'armure (lire les emplacements d'armure d'une entité) | mécanisme de bonus de set |
| Événements du jeu : chute, coup reçu, tick du joueur | effets de set |
| Événement de vitesse de minage : l'objet tenu et le bloc visé | vitesse de minage |
| Le recul : comment Minecraft le calcule | recul de l'acier |
| Appliquer un effet de potion à une créature, tirer au sort | étourdissement de l'acier |
| Info-bulles côté client | attributs de l'armure, info-bulles d'outil |

La notion d'**event bus** est déjà dans [../NOTIONS.md](../NOTIONS.md).

## 9. Découpage proposé

Un ticket, une branche, une PR chacun.

**Armure (#168, fait)** : #172 les attributs, #173 le mécanisme de bonus de set, #174 les trois effets de set.

**Outils et armes (#178)**, à créer après la validation de ce document :

| Ticket (à créer) | Contenu |
|---|---|
| Ajouter la vitesse de minage des traits d'outil | Les nombres dans `content`, la première classe d'événements d'outil, le minage ×2 du duralumin (roche et minerais) et ×1,25 de l'acier (pierre et ardoise), les lignes d'info-bulle de ces deux traits. Il pose la technique (fait en #180). |
| Ajouter la portée d'attaque du duralumin | Le modificateur d'attribut de portée d'attaque sur l'épée et la hache (fait en #181 ; sa ligne d'info-bulle est la ligne d'attribut du jeu). |
| Ajouter le recul de l'acier | Le recul supplémentaire de l'épée et de la hache en acier, la piste à trancher en codant, et sa ligne d'info-bulle. |
| Ajouter l'étourdissement de l'acier | Le tirage au sort à 15 % et la Lenteur IV pendant 2,5 secondes à chaque coup d'une épée ou d'une hache en acier, et sa ligne d'info-bulle. |

**Dépendances** : le ticket de la vitesse de minage d'abord (il pose les nombres, la classe d'événements et le gestionnaire d'info-bulle d'outil), puis la portée d'attaque, le recul et l'étourdissement dans l'ordre qu'on veut.

## 10. Hors périmètre et à décider plus tard

- Un **bonus de set pour les outils** (par exemple si on porte aussi l'armure de l'alliage).
- Un **trait d'outil pour le bronze**, si on lui en trouve un.
- La **réparation dans l'inventaire** d'un outil ou d'une arme.
- D'autres **attributs** : points de vie maximum, chance, portée de minage.
- L'**intégration** avec d'autres mods (étape 4).
- Les **valeurs exactes** de tous les traits : à régler en jouant, avec les retours des testeurs.
