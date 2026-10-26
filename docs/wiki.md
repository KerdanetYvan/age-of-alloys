# Le wiki : organisation et nouvelle version

Le wiki (GitHub Pages) est dans `wiki/` et se construit avec Jekyll (`.github/workflows/pages.yml`). Il est organisé **par version du mod** : un jeu de pages par version, en français et en anglais.

| | Français | Anglais |
|---|---|---|
| Accueil du site | `/` | `/en/` |
| Une version | `/v0.3.0/...` | `/en/v0.3.0/...` |

`/` et `/en/` redirigent vers la version marquée `latest`.

## Fichiers

- `wiki/v0.3.0/*.md` et `wiki/en/v0.3.0/*.md` : les pages d'une version.
- `wiki/_data/versions.yml` : la liste des versions et celle qui est `latest`.
- `wiki/_data/nav.yml` : le menu (une fois par langue). Les adresses y sont relatives à la version.
- `wiki/_layouts/default.html` : la mise en page, le sélecteur de version, le bandeau « ancienne version ».

Chaque page porte dans son en-tête : `version` (ex. `v0.3.0`), `key` (identifiant de la page, le même dans toutes les versions et les deux langues), `permalink`, `alt` (la même page dans l'autre langue), `lang`. Le sélecteur utilise `key` pour garder la même page en changeant de version ; si la page n'existe pas dans l'autre version, il mène à l'accueil de cette version.

## Ajouter une version (exemple : v0.4.0)

1. Copier `wiki/v0.3.0/` vers `wiki/v0.4.0/` et `wiki/en/v0.3.0/` vers `wiki/en/v0.4.0/`.
2. Dans les pages copiées, remplacer `v0.3.0` par `v0.4.0` (`version`, `permalink`, `alt` et les liens internes).
3. Mettre à jour le contenu qui a changé, ajouter les nouvelles pages (avec leur `key`) et la ligne correspondante dans `wiki/_data/nav.yml`.
4. Dans `wiki/_data/versions.yml`, ajouter `v0.4.0` et déplacer `latest: true` dessus.
5. Vérifier le build, comme pour toute PR (`Build wiki`).
