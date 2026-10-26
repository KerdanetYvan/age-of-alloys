<p align="center"><img src="docs/assets/banner.png" alt="Age of Alloys" width="100%"></p>

🇬🇧 [English](README.md) · 🇫🇷 **Français**

> **Le meilleur de chaque métal.** Un mod Minecraft qui ajoute des **alliages**, et avec eux de nouveaux outils et armes.

[![Build](https://github.com/KerdanetYvan/age-of-alloys/actions/workflows/build.yml/badge.svg)](https://github.com/KerdanetYvan/age-of-alloys/actions/workflows/build.yml)
[![Version](https://img.shields.io/github/v/release/KerdanetYvan/age-of-alloys?include_prereleases&label=version)](https://github.com/KerdanetYvan/age-of-alloys/releases)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-62b47a)
![Forge](https://img.shields.io/badge/Forge-47.x-d9672b)
![Statut](https://img.shields.io/badge/statut-b%C3%AAta-yellow)
![Licence](https://img.shields.io/badge/licence-All%20Rights%20Reserved-lightgrey)
<!-- TODO: badges de téléchargements CurseForge / Modrinth -->

## Concept

Age of Alloys enrichit la progression de Minecraft avec des métaux composites : on ne se contente plus de fer, d'or et de diamant, on va **fondre, mélanger et forger** de nouveaux matériaux. La première bêta, la v0.1.0, apporte les métaux et leurs équipements ; la fabrication des alliages en survie vient ensuite.

## Ce que contient la v0.1.0

Tout se trouve dans l'onglet créatif **Age of Alloys** :

- **7 lingots** : trois alliages (bronze, duralumin et acier) et les quatre métaux bruts dont ils sont faits (étain, aluminium, magnésium et manganèse).
- **15 outils et armes** : une pioche, une hache, une pelle, une houe et une épée pour chaque alliage. Tous minent au niveau du fer.
- Des noms en anglais et en français. Les lingots portent les tags `forge:ingots/<métal>`, ce qui permet aux autres mods et aux recettes de les reconnaître comme des lingots de leur métal.

| Alliage | Rôle | Durabilité | Vitesse de minage |
|---|---|---:|---:|
| Bronze | Un outil robuste de milieu de partie, plus durable que le fer | 400 | 6 |
| Duralumin | Le mineur pressé : rapide mais fragile, avec une épée qui frappe 2,0 fois par seconde | 200 | 7,5 |
| Acier | Le meilleur matériau de cette version, juste en dessous du diamant | 850 | 7 |
| _Fer (vanilla, pour repère)_ | | _250_ | _6_ |

> **Bêta :** les alliages se fabriquent désormais en jeu, dans la fonderie d'alliages, mais leurs matières premières (étain, aluminium, magnésium, manganèse) ne se trouvent pas encore dans le monde : pour l'instant, elles s'obtiennent en créatif ou par commande.

## Fonctionnalités prévues

- [x] **Alliages** et leurs équipements (outils et armes)
- [x] **Création des alliages** en jeu
- [ ] **Matières premières** générées dans le monde
- [ ] **Armures** pour chaque alliage
- [ ] **Compatibilité** avec d'autres mods populaires
- [ ] **Wiki** complet

Le détail et l'ordre sont dans la [roadmap](ROADMAP.md) (en français).

## Installation

Prérequis : Minecraft 1.20.1 et Forge 47.x (Java 17).

1. Installer Forge pour Minecraft 1.20.1.
2. Télécharger `ageofalloys-0.1.0.jar` depuis la page des [releases](https://github.com/KerdanetYvan/age-of-alloys/releases).
3. Déposer le jar dans le dossier `mods` du jeu.

La v0.1.0 est une pré-version (bêta) : attendez-vous à quelques aspérités.

## Compatibilité

| Minecraft | Loader | Statut |
|-----------|--------|--------|
| 1.20.1 | Forge 47.x (Java 17) | 🧪 Bêta (v0.1.0) |

Un portage vers NeoForge et des versions plus récentes est envisagé plus tard.

## Documentation

- [Roadmap](ROADMAP.md)
- [Changelog](CHANGELOG.md)
- [Contribuer](CONTRIBUTING.md)
- [Identité visuelle](docs/identity.md)
- [Fiche de notions (apprentissage)](docs/NOTIONS.md)

## Licence

© 2026 Kerdanet Yvan, **All Rights Reserved**. Voir [LICENSE](LICENSE).
Certaines textures dérivent des assets de Minecraft (propriété de Mojang/Microsoft).
