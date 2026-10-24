# Contribuer à Age of Alloys

## Branches

- `main` : toujours stable, chaque commit publiable.
- `feat/<sujet>`, `fix/<sujet>`, `docs/<sujet>` : branches de travail, fusionnées via Pull Request.

## Commits : Conventional Commits

Format : `type(portée): description à l'impératif`

| Type | Usage |
|------|-------|
| `feat` | nouvelle fonctionnalité (item, mécanique…) |
| `fix` | correction de bug |
| `docs` | documentation, wiki, README |
| `refactor` | réécriture sans changement de comportement |
| `assets` | textures, modèles, sons, traductions |
| `build` | Gradle, dépendances, CI |
| `chore` | maintenance diverse |

Exemple : `feat(items): add bronze pickaxe`

## Versionnement

[SemVer](https://semver.org/lang/fr/) : `MAJOR.MINOR.PATCH`.
Chaque version est un **tag Git** `vX.Y.Z` et une **GitHub Release**.

## Changelog

Toute PR qui modifie le comportement du mod ajoute une ligne dans la section `[Non publié]` de [CHANGELOG.md](CHANGELOG.md)
(catégories : Ajouté, Modifié, Déprécié, Retiré, Corrigé, Sécurité).

## Processus de release

1. Déplacer `[Non publié]` vers une nouvelle section `[X.Y.Z] - AAAA-MM-JJ`.
2. Mettre à jour la version dans la config de build.
3. Générer la bannière de la version, puis commit `chore(release): vX.Y.Z` (changelog, version et bannière), puis tag `vX.Y.Z`.
4. Publier la GitHub Release (notes de présentation en anglais, construites à partir du changelog, avec la bannière de la version).
5. Publier sur CurseForge / Modrinth, seulement quand le mod est jouable en survie (voir la [roadmap](ROADMAP.md)).

Les étapes 1 à 4 sont automatisées par le skill `release` (`/release X.Y.Z`) : il prépare le commit de release sur la branche du ticket, puis, une fois la PR mergée, crée le tag et la GitHub Release après confirmation. `/release X.Y.Z --dry-run` prévisualise sans rien écrire. L'étape 5 reste manuelle.
