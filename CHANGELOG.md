# Changelog

Toutes les modifications notables de ce projet sont documentées dans ce fichier.

Le format s'appuie sur [Keep a Changelog](https://keepachangelog.com/fr/1.1.0/)
et le projet suit le [Versionnement Sémantique](https://semver.org/lang/fr/).

## [Non publié]

### Ajouté
- La fonderie d'alliages : un bloc avec sa texture, son nom en anglais et en français et sa place dans l'onglet créatif. Elle se pose face au joueur, se mine à la pioche et lâche son objet avec une pioche en pierre ou mieux. Elle ne fond rien pour l'instant.
- Recettes d'établi des 15 outils et armes (pioche, hache, pelle, houe et épée en bronze, duralumin et acier) : les lingots et des bâtons suffisent, avec les mêmes formes que les outils de Minecraft. Les recettes se débloquent dès qu'on possède un lingot du métal.

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
