---
name: new-ticket
description: Create a GitHub issue for this repo with the right label, milestone and body, and add it to the project board in "Backlog". Use when the user wants to track a new task, bug, idea or learning topic, or when a change has no ticket yet.
---

# Create a ticket

Every change in this repo has a ticket. Create one before any work if none exists.

Project: number `6`, owner `KerdanetYvan`, id `PVT_kwHOCjZVkc4BnnU1`, Status field id `PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg`. Repo `KerdanetYvan/age-of-alloys`.

## Gather (ask only what you cannot infer)

- **Title**: English, imperative, short (`Add bronze ingot`, `Fix armor texture offset`).
- **Type label**: `type: feature`, `type: bug`, `type: docs`, `type: chore`, `type: assets`, `type: learning`.
- **Milestone**, one per roadmap step:
  `Step 0 - Foundations`, `Step 1 - Items`, `Step 2 - Mechanics`, `Step 3 - World`, `Step 4 - Compatibility`.
- **Body**, following the matching template in `.github/ISSUE_TEMPLATE/`:
  - feature: Summary, Details, Definition of done (checklist);
  - bug: version, environment, description, steps, logs;
  - learning: concepts (link the sections of `docs/NOTIONS.md`) and an understanding check.

## Create

```bash
url=$(gh issue create -R KerdanetYvan/age-of-alloys --title "<title>" --milestone "<milestone>" --label "<label>" --body "<body>" | tail -1)
ITEM=$(gh project item-add 6 --owner KerdanetYvan --url "$url" --format json --jq .id)
OPT=$(gh api graphql -f query='query{node(id:"PVT_kwHOCjZVkc4BnnU1"){... on ProjectV2{field(name:"Status"){... on ProjectV2SingleSelectField{options{id name}}}}}}' --jq '.data.node.field.options[]|select(.name=="Backlog")|.id')
gh project item-edit --id "$ITEM" --project-id PVT_kwHOCjZVkc4BnnU1 --field-id PVTSSF_lAHOCjZVkc4BnnU1zhmLFhg --single-select-option-id "$OPT"
```

Report the issue number and link as a markdown link.

## Rules

- Check for a duplicate first: `gh issue list --search "<keywords>" --state all`.
- A ticket describes one deliverable that fits one PR. If it is bigger, create an epic and split it into sub-tickets.
- Do not start working on the ticket. Starting is the job of the `start-ticket` skill.
