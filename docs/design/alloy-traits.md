# Les traits d'alliage

> Document de conception (ticket #168). Il donne à chaque alliage une identité propre, au-delà de ses chiffres : un ou deux **traits** (un attribut sur l'armure, un bonus quand on porte les quatre pièces), sans enchantement collé à l'objet. Toutes les valeurs sont **provisoires** et à équilibrer en jeu. Les alliages et leurs outils sont décrits dans [starter-alloys.md](starter-alloys.md), leurs armures dans [armor.md](armor.md), leur création dans [alloy-creation.md](alloy-creation.md).

## 1. Principes

**Le problème.** Après l'équilibrage (#148), les trois alliages ont des chiffres cohérents, mais ils ne se distinguent que par ces chiffres : durabilité, vitesse, enchantabilité. Un testeur de la v0.3.0 n'avait pas compris l'intérêt du duralumin. Un alliage doit se reconnaître à ce qu'il **fait**, pas seulement à ce qu'il vaut.

**Pas d'enchantement collé à l'objet.** On aurait pu donner à l'armure un enchantement de base. C'est écarté pour trois raisons : la lueur d'enchantement permanente, le retrait possible à la meule, et les interactions avec l'enclume. Les traits passent par des mécanismes du jeu qui n'ont aucun de ces défauts (section 2).

**Un trait par alliage, lisible.** Chaque trait tient en une phrase et s'affiche dans l'info-bulle de l'armure. Il suit le rôle de l'alliage déjà posé dans [starter-alloys.md](starter-alloys.md) : le duralumin est léger et rapide, le bronze est durable, l'acier est puissant.

**Un trait ne remplace pas un chiffre.** La protection, la résistance et la durabilité de [armor.md](armor.md) ne bougent pas : les traits s'y ajoutent.

## 2. Les moyens techniques

| Moyen | Ce que c'est | Retenu ? |
|---|---|---|
| **Attributs de l'objet** | Des modificateurs que l'armure ajoute à celui qui la porte (vitesse de déplacement, résistance au recul…). Ils s'affichent dans l'info-bulle comme les statistiques normales, sans lueur. | Oui |
| **Bonus de set** | Un effet qui s'active quand on porte les quatre pièces du même alliage. On détecte l'ensemble, puis on applique l'effet par un événement du jeu. | Oui |
| Comportements d'outil | Du code dans l'outil : minage plus rapide sur certains blocs, effet à chaque coup, réparation dans l'inventaire. | Reporté (section 9) |
| Enchantement collé | Un enchantement posé sur l'objet à sa création. | Écarté (section 1) |

## 3. Les traits

| Alliage | Rôle | Attribut par pièce | Bonus de set (4 pièces) |
|---|---|---|---|
| **Duralumin** | léger, rapide | +3 % de vitesse de déplacement (+12 % le jeu complet) | dégâts de chute −30 % |
| **Bronze** | durable, polyvalent | aucun | chaque pièce regagne 1 de durabilité toutes les 3 minutes |
| **Acier** | puissant, lourd | +0,05 de résistance au recul, −1 % de vitesse (soit 0,2 de recul et −4 % le jeu complet) | dégâts de feu −50 % |

**Pourquoi ces choix :**
- Le **duralumin** est l'alliage de la vitesse : ses outils minent plus vite, son armure fait courir plus vite. La réduction des dégâts de chute rappelle l'aviation (l'alliage vient de là) et donne un vrai avantage aux mineurs pressés qui sautent dans les grottes.
- Le **bronze** est l'alliage qui dure. Il n'a pas d'attribut : son trait est qu'il s'entretient seul, un peu, comme un Réparation d'enchantement mais sans enchantement. La durabilité totale du jeu complet est de 1 100 : réparer 1 point par pièce toutes les 3 minutes (3 600 ticks) fait environ 7 points par pièce pendant un jour de Minecraft (20 minutes), soit 20 par heure de jeu. C'est volontairement lent : l'armure s'entretient, elle ne se répare pas toute seule en une journée, et une armure mal traitée doit toujours finir par casser.
- L'**acier** est lourd et puissant : un peu de résistance au recul (0,05 par pièce, 0,2 au total ; la netherite vanilla donne 0,1 par pièce, soit 0,4), compensée par une légère lenteur. Le bonus de set (le feu fait deux fois moins mal) vient de sa fabrication : l'acier est forgé par le feu le plus féroce (voir le succès « Presque du diamant »).

**Ce que les valeurs veulent dire** : 0,03 de vitesse est un pourcentage de la vitesse de base du joueur (0,1), qui s'additionne d'une pièce à l'autre : 4 pièces donnent +12 %. Les dégâts de chute et de feu sont multipliés (×0,7 et ×0,5) avant l'armure et les enchantements, qui s'y ajoutent ensuite (section 6).

## 4. Détail technique prévu

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

## 5. Info-bulles

- Chaque pièce affiche son trait : l'attribut (ligne ordinaire du jeu) puis, sous les attributs, un titre « Ensemble de duralumin (n/4) » et la ligne du bonus de set (« Dégâts de chute réduits de 30 % »). Le chiffre est calculé à partir des nombres de la classe `SetBonus` (`content`), qui porte aussi les nombres des effets.
- Le bonus de set s'affiche **activé ou grisé** selon ce que porte le joueur. Cela demande de lire l'équipement du joueur côté client : on le fait dans un gestionnaire d'info-bulle du paquet `client` (l'événement d'info-bulle donne le joueur), pas dans l'objet lui-même, pour ne pas faire planter un serveur sans client.
- Les textes sont des clés de traduction, en anglais et en français, comme tous les noms du mod.

## 6. Interactions

- **Protection et enchantements** : les traits s'ajoutent, ils ne remplacent rien. Le feu −50 % de l'acier se multiplie avec Protection contre le feu (on réduit le montant avant l'armure et les enchantements). Les dégâts de chute −30 % se multiplient avec Chute amortie.
- **Vitesse** : la vitesse de base du joueur est de 0,1. Le jeu complet en duralumin (+12 %) reste modeste devant une potion de Rapidité (+20 % par niveau) et l'acier (−4 %) reste à peine perceptible : l'effet doit être senti sans être pénalisant.
- **Résistance au recul** : l'acier à 0,2 reste loin de la netherite (0,4). Le diamant n'en a pas.
- **Autres mods** : les traits s'appuient sur les mécanismes standard (attributs, événements), donc ils cohabitent avec les autres mods.

## 7. Notions à apprendre

| Notion | Ticket de code (à créer) |
|---|---|
| Attributs et modificateurs : ajouter un attribut à un objet, identifiants uniques, opérations (addition, pourcentage) | attributs de l'armure |
| Détecter un ensemble d'armure (lire les emplacements d'armure d'une entité) | mécanisme de bonus de set |
| Événements du jeu : chute, coup reçu, tick du joueur | effets de set |
| Info-bulles côté client | attributs de l'armure |

La notion d'**event bus** est déjà dans [../NOTIONS.md](../NOTIONS.md).

## 8. Découpage proposé

Un ticket, une branche, une PR chacun. À créer après la validation de ce document, avec les valeurs ci-dessus.

| Ticket (à créer) | Contenu |
|---|---|
| Ajouter les attributs des armures d'alliage | La sous-classe de l'armure, la vitesse du duralumin et de l'acier, la résistance au recul de l'acier, les info-bulles des attributs. Il pose la technique. |
| Ajouter le mécanisme de bonus de set | La règle de détection du set complet, ses nombres dans `content`, l'info-bulle activée ou grisée. |
| Ajouter les trois effets de set | Chute (duralumin), feu (acier), réparation lente (bronze), chacun par son événement. |

**Dépendances** : le ticket des attributs d'abord (il pose la sous-classe), puis le mécanisme de set, puis les trois effets.

## 9. Hors périmètre et à décider plus tard

- **Attributs d'outils et d'armes** : la portée d'attaque (le duralumin pourrait en gagner un peu sur l'épée et la hache, à équilibrer avec sa cadence déjà élevée), la portée de minage… On y reviendra après avoir vu les traits d'armure en jeu.
- Des **comportements d'outil** : minage plus rapide sur certains blocs, effet à chaque coup, réparation dans l'inventaire.
- D'autres **attributs** : points de vie maximum, chance.
- Un **bonus de set pour les outils** (par exemple si on porte l'armure de l'alliage).
- L'**intégration** avec d'autres mods (étape 4).
- Les **valeurs exactes** de tous les traits : à régler en jouant, avec les retours des testeurs.
