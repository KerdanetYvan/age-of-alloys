---
name: start-ticket
description: Start work on one or more GitHub issues of this repo. Moves them to "En cours" on the project board, then creates the working branch. Use BEFORE editing any code or docs for a ticket, whenever the user says "let's do #N", "start #N" or picks the next task.
---

# Start a ticket

The user's rule: **the board is updated before any file is modified.** Never edit, create or delete project files before steps 1-3 are done.

Project: GitHub Projects v2 number `6`, owner `KerdanetYvan`, id `PVT_kwHOCjZVkc4BnnU1`. Status field id `PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg`. Repo `KerdanetYvan/age-of-alloys`.

## Steps

1. **Read the ticket(s)**: `gh issue view <n> --json number,title,labels,milestone,state,body`. If an issue is closed or unknown, stop and tell the user.
2. **Move each ticket to "En cours"**:
   ```bash
   OPT=$(gh api graphql -f query='query{node(id:"PVT_kwHOCjZVkc4BnnU1"){... on ProjectV2{field(name:"Status"){... on ProjectV2SingleSelectField{options{id name}}}}}}' --jq '.data.node.field.options[]|select(.name=="En cours")|.id')
   ITEM=$(gh project item-list 6 --owner KerdanetYvan --limit 100 --format json --jq '.items[]|select(.content.number==<n>)|.id')
   gh project item-edit --id "$ITEM" --project-id PVT_kwHOCjZVkc4BnnU1 --field-id PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg --single-select-option-id "$OPT"
   ```
   If the ticket is not on the board yet, add it first with `gh project item-add 6 --owner KerdanetYvan --url <issue url> --format json --jq .id`.
3. **Create the branch from an up-to-date `main`**: `git switch main && git pull && git switch -c <type>/<n>-<slug>`.
   - `type` comes from the label: `type: feature` -> `feat`, `type: bug` -> `fix`, `type: docs` -> `docs`, `type: chore` -> `chore`, `type: assets` -> `assets`, `type: learning` -> `docs`.
   - `slug`: 2-4 lowercase words from the title, joined by `-`.
   - Several tickets in one branch: allowed only when they are tightly related; use `<type>/<n>-<m>-<slug>` and move every ticket to "En cours".
   - Uncommitted changes on the current branch: do not carry them over silently. Tell the user and ask.
4. **Confirm** in one short line: tickets moved, branch name. Then begin the work.

## Rules

- One ticket = one branch = one PR. Do not mix unrelated changes.
- Never work on `main` directly.
- "Review" and "Fait" are set by the project's automations (PR linked, PR merged). Do not set them by hand unless the user reports they did not fire.
