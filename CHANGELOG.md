# Changelog

Toutes les modifications notables de ce projet sont documentées dans ce fichier.

Le format s'appuie sur [Keep a Changelog](https://keepachangelog.com/fr/1.1.0/)
et le projet suit le [Versionnement Sémantique](https://semver.org/lang/fr/).

## [Non publié]

### Ajouté
- Le minerai d'étain : un bloc dans la pierre et un dans l'ardoise des profondeurs, qui se minent à la pioche en pierre ou mieux et lâchent de l'étain brut (plus avec Fortune, ou le bloc lui-même avec Silk Touch). L'étain brut et les deux minerais se fondent en lingot d'étain au four et au haut fourneau. Ils sont dans l'onglet créatif.
- L'étain se trouve dans le monde : des filons de minerai d'étain apparaissent dans tous les biomes du monde normal, dans la pierre et dans l'ardoise des profondeurs, de -24 à 80 de hauteur, un peu plus rares que le cuivre. Ils ne se génèrent que dans les chunks créés après cette version : dans un monde déjà exploré, il faut aller plus loin. Avec le cuivre de Minecraft, le bronze devient faisable en survie.

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
