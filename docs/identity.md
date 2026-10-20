# Identité visuelle

Référence pour garder le mod cohérent (README, page de téléchargement, captures, futurs assets). Ticket #46.

## Slogan

- FR : **Le meilleur de chaque métal.**
- EN : **The best of every metal.**

Un alliage prend le meilleur de chaque métal : le slogan décrit donc le mod sans promettre ce qu'il ne fait pas encore (pas de génération de minerais avant l'étape 3).

## Univers

Pixel art, forge sombre : une enclume, un lingot incandescent marqué de coups de marteau, des étincelles, des flammes en bas des visuels larges, le tout sur un fond de charbon presque noir.

## Palette

| Rôle | Couleurs |
|---|---|
| Fond charbon (neutre, presque noir) | `#0B0B0C` · `#131315` · `#212124` · `#343438` |
| Contour et cadre | `#080809` · `#2C2C2F` |
| Acier de l'enclume | `#47403D` · `#6B625D` · `#948A83` · `#BDB3AB` |
| Métal incandescent (bronze, voir `docs/design/starter-alloys.md`) | `#8A5A2B` · `#CD7F32` · `#E8A55F` · `#F3BC82` · `#F8DABC` |
| Flammes | `#78240A` · `#BE4612` · `#E87824` · `#F5B250` · `#FCE2A0` |
| Halo et braises | `#58240C` |

## Fichiers

| Fichier | Taille | Usage |
|---|---|---|
| `src/main/resources/logo.png` | 128×128 | Logo du mod : liste des mods en jeu (`logoFile` dans `mods.toml`) et page officielle |
| `docs/assets/logo-32.png` | 32×32 | Version de référence du logo, à partir de laquelle le 128×128 est obtenu |
| `docs/assets/banner.png` | 1280×320 | Bannière des README |
| `docs/assets/banner-notext.png` | 1280×320 | Bannière sans texte, pour tester d'autres typographies |
| `docs/assets/social.png` | 1280×640 | Image sociale du dépôt GitHub (Settings > General > Social preview) |
| `docs/assets/social-notext.png` | 1280×640 | Image sociale sans texte |

## Règles

- Tout est dessiné en pixel art à petite taille, puis agrandi par un **entier** (×4) en « plus proche voisin », jamais lissé : 32×32 pour le logo, 320×80 pour la bannière, 320×160 pour l'image sociale.
- Le logo doit rester lisible à 32×32 : peu de teintes, pas de motif fin.
- Les textures sont originales : ni le bloc de charbon ni le lingot de Minecraft ne sont recopiés.
- Typographie du titre et du slogan : provisoire (police pixel 5×7 dessinée pour les visuels). À remplacer par la police choisie via les versions « sans texte ».
