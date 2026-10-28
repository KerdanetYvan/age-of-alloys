# Changelog

Toutes les modifications notables de ce projet sont documentées dans ce fichier.

Le format s'appuie sur [Keep a Changelog](https://keepachangelog.com/fr/1.1.0/)
et le projet suit le [Versionnement Sémantique](https://semver.org/lang/fr/).

## [Non publié]

### Ajouté
- Chaque coup de l'épée et de la hache en acier a 15 % de chance (environ un coup sur sept) d'étourdir la cible : elle est ralentie de 60 % pendant 2,5 secondes. Seuls les coups portés à la main comptent, pas les flèches. Une ligne bleue de leur info-bulle l'annonce.
- L'épée et la hache en acier repoussent un peu plus fort : un bonus de recul d'attaque de 0,2 quand on les tient en main (environ un quart de recul en plus pour un coup simple). Le jeu l'affiche dans leur info-bulle.
- L'épée et la hache en duralumin portent un demi-bloc plus loin (3,5 blocs au lieu de 3) quand on les tient en main. Le jeu l'affiche dans leur info-bulle comme un bonus de portée.
- Les outils d'alliage minent plus vite certains blocs, avec une ligne bleue dans leur info-bulle : les outils en duralumin sont deux fois plus rapides sur la roche (pierre, granite, diorite, andésite, tuf) et sur tous les minerais, sauf sur l'ardoise des abîmes elle-même ; les outils en acier sont 25 % plus rapides sur la pierre et sur l'ardoise. Le bronze ne change pas.
- Les bonus des ensembles d'armure d'alliage, avec les quatre pièces portées : le duralumin réduit de 30 % les dégâts de chute, l'acier réduit de moitié les dégâts de feu, et le bronze répare lentement ses pièces abîmées (1 de durabilité par pièce toutes les 3 minutes).
- Les pièces d'armure d'alliage annoncent dans leur info-bulle le bonus de l'ensemble complet : « Ensemble de duralumin (2/4) » avec la ligne du bonus, en vert quand on porte les quatre pièces et en gris sinon.
- Les armures d'alliage ont chacune leur caractère : chaque pièce en duralumin accélère de 3 % (12 % avec le jeu complet), et chaque pièce en acier résiste un peu au recul (0,2 avec le jeu complet) mais ralentit de 1 % (4 % avec le jeu complet). Le bronze ne change pas. Ces effets s'affichent dans l'info-bulle de la pièce.
- Les blocs de métal : un bloc de 9 lingots pour chacun des sept métaux (étain, aluminium, magnésium, manganèse, bronze, duralumin et acier) et un bloc de 9 objets bruts pour l'étain, l'aluminium, le magnésium et le manganèse. À l'établi, 9 objets font un bloc et un bloc rend 9 objets. Ils se minent à la pioche, comme les blocs de fer (en pierre, ou en fer pour le magnésium et le manganèse), et sont dans l'onglet créatif.
- Le charbon purifié : un charbon cuit longtemps au four (20 secondes) ou au haut fourneau (10 secondes), qui apporte le carbone de l'acier. Il est dans l'onglet créatif. Le charbon de bois n'en donne pas.
- Les succès d'Age of Alloys : une page de succès qui apparaît quand on ramasse son premier métal brut, et guide le joueur du minerai aux alliages, aux outils et aux armures en 9 étapes (le tour des métaux, la fonderie, le bronze, le duralumin, l'acier, les outils, l'armure et un défi final). Leurs textes sont en anglais et en français et donnent des indices sans révéler les recettes, qu'on trouve dans le livre de recettes de la fonderie.
- Le livre de recettes de la fonderie d'alliages : un bouton à gauche de l'interface, sous les entrées, l'ouvre à côté de la fenêtre, comme celui du four. Il montre les recettes débloquées, une recette se débloquant dès qu'on possède un de ses ingrédients (un lingot de cuivre ou d'étain pour le bronze, par exemple). En survolant une recette, on voit ses ingrédients avec leurs quantités, la chaleur requise et la durée de fonte. Il sert à consulter : cliquer sur une recette ne place rien dans les emplacements.

### Modifié
- Le minerai d'aluminium dans la pierre est plus contrasté : ses veines, d'un bleu-gris plus sombre, se détachent mieux de la pierre et ne se confondent plus avec le magnésium.

### Modifié
- Le duralumin est un vrai outil de vitesse : ses outils sont plus durables (300 au lieu de 200), minent plus vite (9 au lieu de 7,5), frappent un peu plus fort (+1 de dégâts) et s'enchantent bien mieux (22 au lieu de 15, comme l'or). Son armure dure plus longtemps (715 au total au lieu de 550) et s'enchante aussi mieux (22 au lieu de 15).
- Les outils en acier se rapprochent du diamant : 1 000 de durabilité au lieu de 850 et une vitesse de minage de 7,5 au lieu de 7.
- L'acier est plus exigeant : il se fabrique avec 4 lingots de fer et 1 charbon purifié pour 1 lingot, au lieu de 4 lingots de fer et 1 charbon pour 4 lingots. Une armure complète en acier demande ainsi 96 lingots de fer.
- Dans la fonderie d'alliages, les combustibles chauds brûlent plus vite : le charbon et le charbon de bois deux fois plus vite, le bloc de charbon et le seau de lave trois fois plus vite. Un bloc de charbon fait ainsi 26 lots de fonte au lieu de 80. Les combustibles les moins chauds (planches, bâtons) et le four ne changent pas.

### Corrigé
- La fonderie ne fond plus quand un objet étranger à la recette se trouve dans ses entrées : du minerai de fer posé avec l'étain et le cuivre bloque désormais le bronze, jusqu'à ce qu'on le retire. Le livre de recettes tient compte de la même règle.
- L'acier se fabrique avec du charbon, plus avec du charbon de bois.
- La recette de la fonderie d'alliages se débloque dès qu'on possède des briques de pierre, un seau ou de la pierre lisse, et plus seulement des briques de pierre.

## [0.3.0] - 2026-10-26

### Ajouté
- L'armure en bronze : un casque, un plastron, des jambières et des bottes en bronze, qui se fabriquent à l'établi avec des lingots de bronze (5, 8, 7 et 4). Elle protège comme l'armure de fer (15 points pour le jeu complet) mais dure plus longtemps (1 100 de durabilité au total contre 825), et se répare avec un lingot de bronze. Elle a ses icônes et ses textures portées en bronze.
- Les armures en duralumin et en acier : un casque, un plastron, des jambières et des bottes pour chacune, fabriqués à l'établi avec des lingots de l'alliage (5, 8, 7 et 4). Le duralumin protège comme le fer (15 points) mais s'use vite (550 de durabilité au total) et se laisse bien enchanter ; l'acier protège presque comme le diamant (18 points, 1 de robustesse par pièce, 1 485 de durabilité au total). Elles se réparent avec un lingot de leur alliage. Elles ont leurs icônes et leurs textures portées : bleu-gris pour le duralumin, gris chaud pour l'acier.
- Le minerai d'étain : un bloc dans la pierre et un dans l'ardoise des profondeurs, qui se minent à la pioche en pierre ou mieux et lâchent de l'étain brut (plus avec Fortune, ou le bloc lui-même avec Silk Touch). L'étain brut et les deux minerais se fondent en lingot d'étain au four et au haut fourneau. Ils sont dans l'onglet créatif.
- Les minerais d'aluminium, de magnésium et de manganèse : chacun a son bloc dans la pierre et dans l'ardoise des profondeurs, et son objet brut. L'aluminium se mine à la pioche en pierre ; le magnésium et le manganèse demandent une pioche de niveau fer (fer, bronze, duralumin ou acier). Comme l'étain, ils lâchent l'objet brut (plus avec Fortune, ou le bloc avec Silk Touch), et l'objet brut et les blocs se fondent en lingot au four et au haut fourneau. Ils sont dans l'onglet créatif.
- L'aluminium, le magnésium et le manganèse se trouvent dans le monde, dans tous les biomes du monde normal, dans la pierre et dans l'ardoise des profondeurs : l'aluminium est le plus courant et se trouve de la surface aux grottes (jusqu'à 128 de hauteur), le magnésium est rare et se trouve autour de 0, le manganèse est rare et profond (surtout sous 0). Comme l'étain, ils ne se génèrent que dans les chunks créés après cette version. Le duralumin devient faisable en survie.
- L'étain se trouve dans le monde : des filons de minerai d'étain apparaissent dans tous les biomes du monde normal, dans la pierre et dans l'ardoise des profondeurs, de -24 à 80 de hauteur, un peu plus rares que le cuivre. Ils ne se génèrent que dans les chunks créés après cette version : dans un monde déjà exploré, il faut aller plus loin. Avec le cuivre de Minecraft, le bronze devient faisable en survie.

### Modifié
- L'étain et le manganèse sont plus courants dans le monde : l'étain se trouve presque aussi souvent que le cuivre, et le manganèse, toujours rare et profond, se trouve un peu plus facilement. Seuls les chunks créés après cette version en profitent.

## [0.2.0] - 2026-10-26

### Ajouté
- La fonderie d'alliages : un bloc avec sa texture, son nom en anglais et en français et sa place dans l'onglet créatif. Elle se pose face au joueur, se mine à la pioche et lâche son objet avec une pioche en pierre ou mieux. Elle garde 6 emplacements (4 entrées, un combustible, une sortie) accessibles aux entonnoirs par le dessus, les côtés et le dessous, brûle son combustible quand elle a quelque chose à fondre, s'allume et éclaire (niveau 13). Elle lâche son contenu quand on la casse.
- L'interface de la fonderie d'alliages : un clic droit l'ouvre, avec ses 6 emplacements (le combustible n'accepte que ce qui brûle, la sortie rien), le déplacement rapide par majuscule + clic, une flamme qui se vide avec le combustible et une flèche qui montre la progression de la fonte.
- La fonderie d'alliages fond : elle cherche une recette qui correspond à ses entrées (le type de recette « alliage », avec des ingrédients en quantités), avance sa flèche pendant la durée de fonte, puis consomme les entrées et produit les lingots. Première recette : le bronze, 3 lingots de cuivre et 1 lingot d'étain pour 4 lingots, avec n'importe quel combustible. Elle ne consomme plus de combustible quand rien ne correspond ou que la sortie est pleine, et la progression est conservée à la fermeture du monde.
- Le duralumin et l'acier se fondent dans la fonderie : 5 aluminium, 1 cuivre, 1 magnésium et 1 manganèse donnent 8 lingots de duralumin ; 4 fer et 1 charbon donnent 4 lingots d'acier. Chaque recette demande un niveau de chaleur : n'importe quel combustible pour le bronze, au moins un charbon (1 600 ticks) pour le duralumin, au moins un bloc de charbon ou un seau de lave (10 000 ticks) pour l'acier. Un combustible trop froid n'est pas consommé, et un seau de lave laisse un seau vide.
- La fonderie d'alliages se fabrique à l'établi : 4 briques de pierre, 3 pierres lisses et 1 seau, disposés en creuset (les briques sur les côtés, le seau en haut au milieu, la pierre lisse en bas). La recette se débloque dès qu'on possède des briques de pierre.
- Recettes d'établi des 15 outils et armes (pioche, hache, pelle, houe et épée en bronze, duralumin et acier) : les lingots et des bâtons suffisent, avec les mêmes formes que les outils de Minecraft. Les recettes se débloquent dès qu'on possède un lingot du métal.

### Modifié
- La flamme et la flèche de l'interface de la fonderie sont redessinées dans le style de Minecraft. La flamme change de couleur selon la chaleur du combustible en cours (orange, rouge orangé, bleue) et garde une ligne tant que le feu brûle, au lieu de disparaître avant la fin du combustible.

## [0.1.0] - 2026-10-24

### Ajouté
- Structure initiale du dépôt : README, roadmap, fiche de notions, conventions de contribution.
- Squelette du mod pour Forge 1.20.1 (47.4.10) : build Gradle, identifiant `ageofalloys` et point d'entrée qui écrit un message au chargement.
- 7 lingots : bronze, duralumin, acier, étain, aluminium, magnésium et manganèse, regroupés dans l'onglet créatif « Age of Alloys ». Chaque lingot a sa propre texture, obtenus en créatif.
- Identité visuelle : logo du mod (affiché dans la liste des mods), bannière et image sociale, README alignés avec le slogan « Le meilleur de chaque métal ».
- Noms des 7 lingots et de l'onglet créatif traduits en anglais (`en_us`) et en français (`fr_fr`).
- Famille d'outils en bronze : pioche, hache, pelle, houe et épée (durabilité 400, vitesse 6, niveau du fer), dans l'onglet créatif.
- Famille d'outils en duralumin : pioche, hache, pelle, houe et épée (durabilité 200, vitesse 7,5, niveau du fer), avec une épée à 2,0 coups/s et une hache à 1,2 coup/s, dans l'onglet créatif.
- Famille d'outils en acier : pioche, hache, pelle, houe et épée (durabilité 850, vitesse 7, niveau du fer), dans l'onglet créatif.
- Noms des 15 outils et armes (bronze, duralumin, acier) traduits en anglais (`en_us`) et en français (`fr_fr`).
- Textures et modèles des 5 outils en bronze (pioche, hache, pelle, houe, épée).
- Textures et modèles des 5 outils en duralumin (pioche, hache, pelle, houe, épée).
- Textures et modèles des 5 outils en acier (pioche, hache, pelle, houe, épée).
- Tags `forge:ingots/<métal>` pour les 7 lingots (et `forge:ingots`), pour que les autres mods reconnaissent les lingots d'Age of Alloys. Les tags des outils et des blocs sont désormais générés par datagen (`./gradlew runData`).
