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

Age of Alloys enrichit la progression de Minecraft avec des métaux composites : on ne se contente plus de fer, d'or et de diamant, on va **fondre, mélanger et forger** de nouveaux matériaux. Les métaux bruts se trouvent dans le monde sous forme de minerais, et on en fait les alliages dans une fonderie d'alliages.

## Ce que contient le mod

Tout se trouve dans l'onglet créatif **Age of Alloys**, et tout s'obtient aussi en survie :

- **4 minerais** : l'étain, l'aluminium, le magnésium et le manganèse, dans la pierre et l'ardoise des profondeurs de tous les biomes du monde normal. Chacun lâche un objet brut qui se fond en lingot au four ou au haut fourneau. L'aluminium et l'étain se minent à la pioche en pierre, le magnésium et le manganèse demandent une pioche de niveau fer. Les minerais ne se génèrent que dans les chunks créés après cette version : il faut explorer une zone neuve ou créer un nouveau monde.
- **7 lingots** : trois alliages (bronze, duralumin et acier) et les quatre métaux bruts dont ils sont faits (étain, aluminium, magnésium et manganèse).
- **15 outils et armes** : une pioche, une hache, une pelle, une houe et une épée pour chaque alliage. Tous minent au niveau du fer, et se fabriquent avec des lingots et des bâtons.
- **La fonderie d'alliages** : un bloc qui se fabrique avec quatre briques de pierre, trois pierres lisses et un seau. Elle fond le bronze, le duralumin et l'acier à partir des métaux bruts, et le combustible doit être assez chaud pour l'alliage : n'importe lequel pour le bronze, un charbon ou mieux pour le duralumin, un bloc de charbon ou un seau de lave pour l'acier.
- **12 pièces d'armure** : un casque, un plastron, des jambières et des bottes pour chaque alliage, qui se fabriquent avec des lingots selon les formes de l'armure de Minecraft et se réparent avec le lingot de leur alliage.
- Des noms en anglais et en français. Les lingots portent les tags `forge:ingots/<métal>`, ce qui permet aux autres mods et aux recettes de les reconnaître comme des lingots de leur métal.

| Alliage | Rôle | Durabilité | Vitesse de minage |
|---|---|---:|---:|
| Bronze | Un outil robuste de milieu de partie, plus durable que le fer | 400 | 6 |
| Duralumin | Le mineur pressé : rapide mais fragile, avec une épée qui frappe 2,0 fois par seconde | 200 | 7,5 |
| Acier | Le meilleur matériau de cette version, juste en dessous du diamant | 850 | 7 |
| _Fer (vanilla, pour repère)_ | | _250_ | _6_ |

| Armure | Rôle | Protection (jeu complet) | Durabilité (jeu complet) | Robustesse (par pièce) |
|---|---|---:|---:|---:|
| Bronze | Comme le fer, mais elle dure plus longtemps | 15 | 1 100 | 0 |
| Duralumin | Comme le fer, mais fragile ; la meilleure enchantabilité | 15 | 550 | 0 |
| Acier | Juste en dessous du diamant | 18 | 1 485 | 1 |
| _Fer (vanilla, pour repère)_ | | _15_ | _825_ | _0_ |

> **Bêta :** toute la boucle fonctionne en survie (trouver les minerais, fondre les lingots, fabriquer les alliages, façonner outils et armures), mais le mod est encore en test : attendez-vous à quelques aspérités. La compatibilité avec d'autres mods, les succès et un livre de recettes pour la fonderie viendront plus tard.

## Fonctionnalités prévues

- [x] **Alliages** et leurs équipements (outils et armes)
- [x] **Création des alliages** en jeu
- [x] **Matières premières** générées dans le monde
- [x] **Armures** pour chaque alliage
- [ ] **Compatibilité** avec d'autres mods populaires
- [ ] **Wiki** complet

Le détail et l'ordre sont dans la [roadmap](ROADMAP.md) (en français).

## Installation

Prérequis : Minecraft 1.20.1 et Forge 47.x (Java 17).

1. Installer Forge pour Minecraft 1.20.1.
2. Télécharger `ageofalloys-0.3.0.jar` depuis la page des [releases](https://github.com/KerdanetYvan/age-of-alloys/releases).
3. Déposer le jar dans le dossier `mods` du jeu.

La v0.3.0 est une pré-version (bêta) : attendez-vous à quelques aspérités.

## Compatibilité

| Minecraft | Loader | Statut |
|-----------|--------|--------|
| 1.20.1 | Forge 47.x (Java 17) | 🧪 Bêta (v0.3.0) |

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
