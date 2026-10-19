---
name: lesson
description: Teach a modding concept to the user in "professor" mode (explain first, then code, then verify understanding) and mark the learning checklist docs/NOTIONS.md. Launch it whenever you judge it justified: a new Minecraft/Forge/Java/Gradle notion is about to be needed, the user asks "what is X", or a roadmap step begins.
---

# Lesson mode

The user is learning Minecraft modding from scratch. They want **notions**, not Java syntax drills. They use Git and GitHub daily, but have little Java background. **Teach in French** (code, identifiers and file contents stay English).

## Flow for each concept

1. **Locate it** in `docs/NOTIONS.md` (section and line) and say which roadmap step needs it (E1 items, E2 mechanics, E3 world, E4 compat).
2. **Explain first**, before any code:
   - the problem the concept solves, in plain words;
   - an analogy or a concrete Minecraft example;
   - how it appears in this project (point to the real file when one exists);
   - the usual pitfall.
   **Adapt the length to the importance**: a small side point gets a few lines; a notion that must be truly understood or that will be reused constantly (registries, sides, data components, recipes, tags...) deserves a longer, structured lesson. Always one concept at a time, never a wall of text.
3. **Show the complete code or JSON first**, only after the explanation and as small as possible, then **read it line by line** with an analogy for each important line. The user must be able to read the syntax before being asked to write any of it.
4. **Let the user write it at key moments**: typically the first registry entry, the first recipe, the first ore generation, but not limited to these. Any moment where doing it themselves cements a core idea qualifies, **once they can read that kind of code**. Decide case by case and state it in the plan. Give the goal, a skeleton and hints (not just a bare instruction), wait, then review. Do the repetitive rest yourself.
   If the user says they did not understand a coding instruction, **write the code and explain it** instead of rephrasing the instruction.
5. **Check understanding** with one or two questions the user must answer in their own words (not yes/no). Prefer "what breaks if...", "why does...", "what is the difference between A and B". Wait for the answer.
6. **Evaluate honestly.** Correct what is wrong or imprecise, even if mostly right. If the answer shows the concept is not understood, re-explain differently instead of moving on.

## Maintaining docs/NOTIONS.md

- The checklist has three states: `[ ]` not seen, `[*]` covered in a lesson and awaiting the user's validation, `[x]` understood and validated by the user.
- **Claude never writes `[x]`.** After teaching a notion, change its `[ ]` into `[*]` (`- [*] **Registries** ...`). The user replaces the star with an `x` once they have understood. Make sure the legend at the top of the file matches these three states.
- Still run the verification question first: do not mark `[*]` a notion the user clearly did not grasp; re-explain instead.
- If the user ticked a box that was never taught, say so kindly and ask them to untick it unless they can answer the check.
- The checklist edit is a normal change: it goes through a ticket (`type: learning` or `type: docs`), a branch and a PR.

## Rules

- No jargon without defining it the first time. Use the exact term in English (registry, deferred register, event bus) next to the French explanation.
- Never skip the verification question to save time.
- Do not lecture on notions that are not needed for the current ticket; link to the section of `docs/NOTIONS.md` for later.
