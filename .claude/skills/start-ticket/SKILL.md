---
name: start-ticket
description: Start work on a GitHub issue of this repo. Moves it to "En cours" on the project board, creates the working branch, then proposes a plan in plan mode. Use BEFORE editing any code or docs for a ticket, whenever the user says "let's do #N", "start #N" or picks the next task.
---

# Start a ticket

The user's rule: **the board is updated before any file is modified.** Never edit, create or delete project files before steps 1-3 are done, and never before the plan of step 5 is approved.

Project: GitHub Projects v2 number `6`, owner `KerdanetYvan`, id `PVT_kwHOCjZVkc4BnnU1`. Status field id `PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg`. Repo `KerdanetYvan/age-of-alloys`.

## Steps

1. **Read the ticket**: `gh issue view <n> --json number,title,labels,milestone,state,body`. If the issue is closed or unknown, stop and tell the user. If it has the label `type: idea` (it is still in the "Idées" column), stop too: promote it with the user first (type, milestone, definition of done; see "Ideas" in `new-ticket`), then continue.
2. **Move the ticket to "En cours"**:
   ```bash
   OPT=$(gh api graphql -f query='query{node(id:"PVT_kwHOCjZVkc4BnnU1"){... on ProjectV2{field(name:"Status"){... on ProjectV2SingleSelectField{options{id name}}}}}}' --jq '.data.node.field.options[]|select(.name=="En cours")|.id')
   ITEM=$(gh project item-list 6 --owner KerdanetYvan --limit 200 --format json --jq '.items[]|select(.content.number==<n>)|.id')
   gh project item-edit --id "$ITEM" --project-id PVT_kwHOCjZVkc4BnnU1 --field-id PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg --single-select-option-id "$OPT"
   ```
   If the ticket is not on the board yet, add it first with `gh project item-add 6 --owner KerdanetYvan --url <issue url> --format json --jq .id`.

   **Parent tickets.** If the ticket is a sub-issue, **all its ancestors** must be "En cours" too (a parent is a tracking ticket: it has no branch and no PR of its own, and a parent can itself be a sub-issue of another parent). Walk up the whole chain and reuse `$OPT` from above:
   ```bash
   CUR=<n>
   while PARENT=$(gh api graphql -f query='query($n:Int!){repository(owner:"KerdanetYvan",name:"age-of-alloys"){issue(number:$n){parent{number}}}}' -F n=$CUR --jq '.data.repository.issue.parent.number // empty'); [ -n "$PARENT" ]; do
     PITEM=$(gh project item-list 6 --owner KerdanetYvan --limit 200 --format json --jq ".items[]|select(.content.number==$PARENT)|select(.status!=\"En cours\")|.id")
     [ -n "$PITEM" ] && gh project item-edit --id "$PITEM" --project-id PVT_kwHOCjZVkc4BnnU1 --field-id PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg --single-select-option-id "$OPT"
     CUR=$PARENT
   done
   ```
   The query prints nothing when there is no parent, which ends the loop (do not use `gh api .../issues/<n>/parent`: on a 404 it prints the error body on stdout). The loop handles any depth.
3. **Create the branch from an up-to-date `main`**: `git switch main && git pull && git switch -c <type>/<n>-<slug>`.
   - `type` comes from the label: `type: feature` -> `feat`, `type: bug` -> `fix`, `type: docs` -> `docs`, `type: chore` -> `chore`, `type: assets` -> `assets`, `type: learning` -> `docs`.
   - `slug`: 2-4 lowercase words from the title, joined by `-`.
   - Uncommitted changes on the current branch: do not carry them over silently. Tell the user and ask.
4. **Confirm** in one short line: ticket moved (and its parents, level by level, if it had any and they were moved), branch name.
5. **Plan before coding.** Enter plan mode (EnterPlanMode) and propose a plan for the ticket: files to create or change, steps in order, what the user will code themselves if it is a crucial learning moment, how it will be verified. **Write the plan in French** (the plan file, the approval text and any question). Write no code and touch no project file until the user approves the plan.

## Rules

- **One ticket = one branch = one PR. Always.** If the user names several tickets, start them one at a time, each with its own branch; never group them.
- Do not mix unrelated changes in a branch.
- Never work on `main` directly.
- A parent ticket is never started like a ticket: no branch, no plan, no PR. It moves to "En cours" with its first started sub-issue, at any depth, and is closed by `open-pr` when its last sub-issue is merged.
- "Review" and "Fait" are set by the project's automations (PR linked, PR merged). Do not set them by hand unless the user reports they did not fire.
- Language rules are defined in `CLAUDE.md`: everything the user reads, including the plan, is in French.
