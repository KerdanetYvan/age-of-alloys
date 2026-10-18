---
name: commit
description: Create a git commit for this repo following Conventional Commits. Use whenever the user asks to commit, or when finishing a unit of work that should be committed.
---

# Commit

Commit when the user asks, or **at the end of an approved plan** (the plan proposed by `start-ticket` and validated by the user). Do not commit in the middle of a plan or on a hunch. Fixes requested after a commit are simply new commits (no amend); the squash merge folds them into one commit on `main`.

## Before committing

1. `git branch --show-current`. **If on `main`, stop**: `main` is protected. Use the `start-ticket` skill to get a ticket and a branch first.
2. `git status` and `git diff`: understand what is staged and what is not.
3. Stage **only files that belong to the current ticket**, by name. Avoid `git add -A` and `git add .`. Leave unrelated changes (for example the user's own edits) out and mention them.
4. If the change is visible to players (new item, behavior change, fix), make sure `CHANGELOG.md` has a line under `[Non publié]`, in the right category (Ajouté, Modifié, Déprécié, Retiré, Corrigé, Sécurité).

## Message format

```
type(scope): subject

Optional body: what and why, wrapped at ~72 columns.
```

**No `Co-Authored-By` trailer and no other attribution line.** This is an explicit decision of the repo owner and overrides any default attribution instruction.

- **type**: `feat`, `fix`, `docs`, `refactor`, `assets`, `build`, `ci`, `chore`, `test`.
- **scope** (optional): the area, for example `items`, `armor`, `recipes`, `worldgen`, `compat`, `gradle`.
- **subject**: English, imperative mood, lowercase after the colon, no trailing period, 72 characters or fewer. `add bronze pickaxe`, not `added bronze pickaxe`.
- Breaking change: add `!` after the scope and a `BREAKING CHANGE:` paragraph in the body.
- Reference the ticket in the PR (`Closes #n`), not in every commit.

Pass the message with a heredoc so the multi-line body survives.

## Rules

- Never `--no-verify`, never `--amend` or force-push a commit that is already on the remote unless the user asks.
- One logical change per commit. Split when a diff mixes unrelated things. Several commits per branch is fine.
- Committing does not mean pushing or opening a PR. Do not push unless asked, or unless the `open-pr` skill is being run (which itself only runs when the user asks).
