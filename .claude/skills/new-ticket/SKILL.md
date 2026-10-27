---
name: new-ticket
description: Create a GitHub issue for this repo with the right label, milestone and bilingual body, add it to the project board in "Backlog" (or in "Idées" for an idea that is not decided yet), and optionally attach it as a sub-issue of a bigger parent issue. Use when the user wants to track a task, bug, idea or learning topic, when a change has no ticket yet, or when a big issue must be split into smaller ones.
---

# Create a ticket

Every change in this repo has a ticket. If the work you are about to do has none, create one (you may do it on your own initiative), then follow `start-ticket`.

Project: number `6`, owner `KerdanetYvan`, id `PVT_kwHOCjZVkc4BnnU1`, Status field id `PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg`. Repo `KerdanetYvan/age-of-alloys`.

## Decide: create now or propose first

- **Everything is clear** (title, type, step, scope): create directly.
- **Something is vague** (scope, type, step, size): propose a draft (title, label, milestone, body, parent if any) and wait for approval. Do not guess.

## Content

- **Title: French**, short, imperative or noun phrase (`Ajouter le lingot de bronze`, `Corriger le décalage de texture d'armure`).
- **Body: French first, then English**, so the user reads fast and Claude has an English copy:

  ```markdown
  ## 🇫🇷 Français
  **Résumé** : ...
  **Détails** : ...
  **Critères de fin** :
  - [ ] ...

  ## 🇬🇧 English
  **Summary**: ...
  **Details**: ...
  **Definition of done**:
  - [ ] ...
  ```
  Adapt the section names to the type: bug (version, environment, steps, logs), learning (concepts with links to `docs/NOTIONS.md` sections, understanding check). See `.github/ISSUE_TEMPLATE/`.
- **Label**: `type: feature`, `type: bug`, `type: docs`, `type: chore`, `type: assets`, `type: learning`, and `type: idea` for an idea (see "Ideas").
- **Milestone**: `Step 0 - Foundations`, `Step 1 - Items`, `Step 2 - Mechanics`, `Step 3 - World`, `Step 4 - Compatibility`. An idea has none.

Commits, branch names and PR titles stay in English (see `commit`, `start-ticket`, `open-pr`).

## Create

```bash
url=$(gh issue create -R KerdanetYvan/age-of-alloys --title "<titre>" --milestone "<milestone>" --label "<label>" --body-file <file> | tail -1)
ITEM=$(gh project item-add 6 --owner KerdanetYvan --url "$url" --format json --jq .id)
OPT=$(gh api graphql -f query='query{node(id:"PVT_kwHOCjZVkc4BnnU1"){... on ProjectV2{field(name:"Status"){... on ProjectV2SingleSelectField{options{id name}}}}}}' --jq '.data.node.field.options[]|select(.name=="Backlog")|.id')
gh project item-edit --id "$ITEM" --project-id PVT_kwHOCjZVkc4BnnU1 --field-id PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg --single-select-option-id "$OPT"
```

Write the body to a file in the session scratchpad and use `--body-file`. Report the number and a markdown link.

## Ideas

An idea is something the owner would like but has not decided to build (a tester's suggestion, a thought for later). It does not go to "Backlog": it waits in the "Idées" column until it is picked, for example when the backlog is empty or after feedback.

- Same creation commands as above, with three differences: label `type: idea`, **no `--milestone`**, and the status option `Idées` instead of `Backlog` (`select(.name=="Idées")`).
- Title in French, short. Body in French then English, lighter than a normal ticket: `Résumé` / `Pourquoi c'est intéressant` / `Questions ouvertes` (and the English equivalents). No definition of done: it is written when the idea is picked.
- No sub-issues while it is an idea, even a big one: it is split when it is picked.
- **Promoting an idea** (the owner picks it): ask for the type and the milestone, then `gh issue edit <n> --add-label "<type label>" --remove-label "type: idea" --milestone "<milestone>"`, complete the body with a definition of done, and move it to "Backlog". `start-ticket` does not start an idea that was not promoted.

## Sub-issues (splitting a big issue)

Use native GitHub sub-issues when an issue is too big for one PR (typically the epics for steps 2, 3, 4, or any ticket with several independent deliverables).

1. Propose the breakdown to the user first: parent number, list of sub-issue titles with label and one line each. Wait for approval.
2. Create each sub-issue as above, **with its own label and the same milestone as the parent**.
3. Attach it to the parent. The API needs the sub-issue's numeric `id`, not its number:
   ```bash
   CHILD_ID=$(gh api repos/KerdanetYvan/age-of-alloys/issues/<child_n> --jq .id)
   gh api -X POST repos/KerdanetYvan/age-of-alloys/issues/<parent_n>/sub_issues -F sub_issue_id=$CHILD_ID
   ```
4. The board automation "Auto-add sub-issues to project" adds them to the board. Verify they appear, and set "Backlog" if they have no status.
5. The parent stays open as a tracking ticket until every sub-issue is done; it is never worked on directly (no branch, no PR of its own). Close it once its sub-issues are closed, and tell the user.

## Rules

- Check for a duplicate first: `gh issue list --search "<keywords>" --state all`.
- A (sub-)ticket describes one deliverable that fits one PR. One issue = one branch = one PR.
- Do not start working on the ticket. Starting is the job of `start-ticket`.
