# Le wiki : organisation et nouvelle version

Le wiki (GitHub Pages) est dans `wiki/` et se construit avec Jekyll (`.github/workflows/pages.yml`). Il est organisé **par version du mod** : un jeu de pages par version, en français et en anglais.

| | Français | Anglais |
|---|---|---|
| Accueil du site | `/` | `/en/` |
| Une version | `/v0.3.0/...` | `/en/v0.3.0/...` |
| Historique (toutes les versions) | `/historique/` | `/en/history/` |

`/` et `/en/` redirigent vers la version marquée `latest`.

## Les pages d'une version

Menu : Accueil, Comment jouer, Minerais, Fonderie, Objets, Recettes, Historique.

| `key` | FR | EN | Contenu |
|---|---|---|---|
| `home` | `index.md` | `index.md` | Introduction, état du mod, **news de la version**, installation, plan du wiki |
| `howto` | `comment-jouer.md` | `how-to-play.md` | Premiers pas en survie |
| `ores` | `minerais.md` | `ores.md` | Où creuser |
| `foundry` | `fonderie.md` | `foundry.md` | Fonctionnement et chaleur |
| `items` | `objets.md` | `items.md` | Catalogue et tableaux comparatifs |
| `recipes` | `recettes.md` | `recipes.md` | Toutes les recettes |
| `bronze`, `duralumin`, `steel` | `bronze.md`... | `bronze.md`... | Fiches des alliages, rattachées au menu « Objets » (`nav: items`) |

L'**Historique** n'a pas de version : une seule page par langue, à la racine (`wiki/historique.md`, `wiki/en/history.md`).

## Fichiers

- `wiki/_data/versions.yml` : la liste des versions et celle qui est `latest`.
- `wiki/_data/news.yml` : les news de chaque version (FR et EN), lignes courtes écrites pour les joueurs, rangées en `added`, `changed`, `fixed`. Elles s'affichent sur l'accueil de la version et dans l'historique. Le détail reste dans `CHANGELOG.md`.
- `wiki/_data/nav.yml` : le menu (une fois par langue). Les adresses y sont relatives à la version.
- `wiki/_layouts/default.html` : la mise en page, le sélecteur de version, le bandeau « ancienne version ».
- `wiki/_includes/news.html` : la liste de news d'une version.

Chaque page porte dans son en-tête : `version` (ex. `v0.3.0`, absent pour l'historique), `key` (identifiant de la page, le même dans toutes les versions et les deux langues), `permalink`, `alt` (la même page dans l'autre langue), `lang`, et `nav` si la page doit surligner un autre élément du menu. Le sélecteur utilise `key` pour garder la même page en changeant de version ; si la page n'existe pas dans l'autre version, il mène à l'accueil de cette version.

## Ajouter une version (exemple : v0.4.0)

1. Copier `wiki/v0.3.0/` vers `wiki/v0.4.0/` et `wiki/en/v0.3.0/` vers `wiki/en/v0.4.0/`.
2. Dans les pages copiées, remplacer `v0.3.0` par `v0.4.0` (`version`, `permalink`, `alt` et les liens internes).
3. Mettre à jour le contenu qui a changé, ajouter les nouvelles pages (avec leur `key`) et la ligne correspondante dans `wiki/_data/nav.yml`.
4. Dans `wiki/_data/news.yml`, ajouter `v0.4.0` **en haut** (date, textes FR et EN), d'après la section du `CHANGELOG.md`.
5. Dans `wiki/_data/versions.yml`, ajouter `v0.4.0` et déplacer `latest: true` dessus.
6. Vérifier le build, comme pour toute PR (`Build wiki`).
